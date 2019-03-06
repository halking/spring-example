package com.hal.sample.util;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.endsWith;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.substringBefore;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ReflectionUtils;

/**
 * Created by Steven.HUANG on 2018/12/25.
 */
@Slf4j
public class ExcelUtil {

  private final static String LESS_THAN_EQUAL_2003V = "xls";
  private final static String GREATER_THAN_EQUAL_2007V = "xlsx";

  /**
   * @param cell
   */
  private static Object getCellValue(Cell cell) {
    if (cell == null ||
      (cell.getCellTypeEnum() == CellType.STRING && isBlank(cell.getStringCellValue()))) {
      return null;
    }
    CellType cellType = cell.getCellTypeEnum();
    if (cellType == CellType.BLANK) {
      return null;
    } else if (cellType == CellType.BOOLEAN) {
      return cell.getBooleanCellValue();
    } else if (cellType == CellType.ERROR) {
      return cell.getErrorCellValue();
    } else if (cellType == CellType.FORMULA) {
      try {
        if (HSSFDateUtil.isCellDateFormatted(cell)) {
          return cell.getDateCellValue();
        } else {
          return cell.getNumericCellValue();
        }
      } catch (IllegalStateException e) {
        return cell.getRichStringCellValue();
      }
    } else if (cellType == CellType.NUMERIC) {
      if (DateUtil.isCellDateFormatted(cell)) {
        return cell.getDateCellValue();
      } else {
        return cell.getNumericCellValue();
      }
    } else if (cellType == CellType.STRING) {
      return cell.getStringCellValue();
    } else {
      return null;
    }
  }

  public static List<List<Object>> readByRow(InputStream in, String fileName) throws IOException {
    List<List<Object>> resultList = newArrayList();

    Workbook work = getWorkbook(in, fileName);
    if (null == work) {
      throw new RuntimeException("create workbook failed...");
    }

    //iterate the sheet in Workbook
    for (int i = 0; i < work.getNumberOfSheets(); i++) {
      Sheet sheet = work.getSheetAt(i);
      if (sheet == null) {
        continue;
      }
      //iterate the row in sheet
      for (int j = 1; j <= sheet.getLastRowNum(); j++) {
        Row row = sheet.getRow(j);
        if (row == null) {
          continue;
        }
        //iterate col in the row
        List<Object> cells = new ArrayList<>();
        for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
          Cell cell = row.getCell(y);
          cells.add(getCellValue(cell));
        }
        resultList.add(cells);
      }
    }
    return resultList;
  }

  private static Workbook getWorkbook(InputStream inputStream, String fileName) throws IOException {
    Workbook wb;
    String fileType = FilenameUtils.getExtension(fileName);
    if (LESS_THAN_EQUAL_2003V.equals(fileType)) {
      wb = new HSSFWorkbook(inputStream);
    } else if (GREATER_THAN_EQUAL_2007V.equals(fileType)) {
      wb = new XSSFWorkbook(inputStream);
    } else {
      throw new RuntimeException("Unsupported excel version...");
    }
    return wb;
  }

  public static void buildContent(OutputStream outputStream, String sheetName,
    Collection<String> heads, Consumer<Sheet> consumer) throws Exception {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet(sheetName);
    int rowIndex = 0;
    Row firstRow = sheet.createRow(rowIndex);
    int colIndex = 0;
    for (String value : heads) {
      firstRow.createCell(colIndex++, CellType.STRING).setCellValue(value);
    }

    consumer.accept(sheet);

    workbook.write(outputStream);
  }

  public static <T> void buildContent(OutputStream outputStream,
    String sheetName, Map<String, String> heads, Collection<T> datum)
    throws Exception {

    buildContent(outputStream, sheetName, heads.values(), sheet -> {
      Iterator<T> iterator = datum.iterator();
      int rowIndex = 0;
      while (iterator.hasNext()) {
        Row row = sheet.createRow(++rowIndex);

        T object = iterator.next();
        for (String key : heads.keySet()) {
          int colIndex = 0;
          String getMethod = "get" + capitalize(key);
          Method method = ReflectionUtils.findMethod(object.getClass(), getMethod);
          Object value = ReflectionUtils.invokeMethod(method, object);
          addCell(row, colIndex++, value);
        }
      }
    });


  }

  public static String dealCellType(Object object) {
    String str = String.valueOf(object);
    if (endsWith(str, ".0")) {
      return substringBefore(str, ".0");
    }
    return str;
  }

  private static Cell addCell(Row row, int column, Object val) {
    Cell cell = row.createCell(column);
    try {
      if (val == null) {
        cell.setCellValue("");
      } else {
        if (val instanceof String) {
          cell.setCellValue((String) val);
        } else if (val instanceof Integer) {
          cell.setCellValue((Integer) val);
        } else if (val instanceof Long) {
          cell.setCellValue((Long) val);
        } else if (val instanceof Double) {
          cell.setCellValue((Double) val);
        } else if (val instanceof Float) {
          cell.setCellValue((Float) val);
        } else if (val instanceof Enum) {
          cell.setCellValue(val.toString());
        }
      }
    } catch (Exception ex) {
      log.error("Set cell value [" + row.getRowNum() + "," + column + "] error: " + ex.toString());
      cell.setCellValue(val.toString());
    }
    return cell;
  }
}
