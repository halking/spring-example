package com.hal.example.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Consumer;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringUtils.*;

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
    Object object = "";
    try {
      if (cell == null || (cell.getCellType() == CellType.STRING &&
          isBlank(cell.getStringCellValue()))) {
        return null;
      }
      CellType cellType = cell.getCellType();
      if (cellType == CellType.BLANK) {
        return null;
      } else if (cellType == CellType.BOOLEAN) {
        object = cell.getBooleanCellValue();
      } else if (cellType == CellType.ERROR) {
        object = cell.getErrorCellValue();
      } else if (cellType == CellType.FORMULA) {
        if (HSSFDateUtil.isCellDateFormatted(cell)) {
          object = cell.getDateCellValue();
        } else {
          object = cell.getNumericCellValue();
        }
      } else if (cellType == CellType.NUMERIC) {
        if (DateUtil.isCellDateFormatted(cell)) {
          object = cell.getDateCellValue();
        } else {
          DecimalFormat df = new DecimalFormat("0");
          object = df.format(cell.getNumericCellValue());
        }
      } else if (cellType == CellType.STRING) {
        object = cell.getStringCellValue();
      } else {
        return null;
      }
    } catch (Exception e) {
      return object;
    }
    return object;
  }

  public static List<List<Object>> readByRow(InputStream in, String fileName) throws IOException {
    Workbook wb = getWorkbook(in, fileName);

    List<List<Object>> resultList = newArrayList();
    //iterate the sheet in Workbook
    for (int i = 0; i < wb.getNumberOfSheets(); i++) {
      Sheet sheet = wb.getSheetAt(i);
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

  public static <E> List<E> parseToObject(InputStream in, String fileName, Class<E> clazz)
      throws Exception {

    Workbook wb = getWorkbook(in, fileName);

    List<E> resultList = newArrayList();

    //iterate the sheet in Workbook
    for (int i = 0; i < wb.getNumberOfSheets(); i++) {
      Sheet sheet = wb.getSheetAt(i);
      if (sheet == null) {
        continue;
      }

      Map<Integer, Field> fieldsMap = getTitle(sheet, clazz);
      //iterate the row in sheet
      for (int j = 1; j <= sheet.getLastRowNum(); j++) {
        Row row = sheet.getRow(j);
        if (checkRowIsEmpty(row)) {
          continue;
        }
        //iterate column in the row
        E entity = clazz.newInstance();
        for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
          Field field = fieldsMap.get(y);
          if (field == null) {
            continue;
          }

          Cell cell = row.getCell(y);
          String value = dealCellType(getCellValue(cell));
          if (isBlank(value)) {
            continue;
          }

          Class<?> fieldType = field.getType();
          if (String.class == fieldType) {
            field.set(entity, String.valueOf(value));
          } else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
            field.set(entity, Integer.valueOf(value));
          } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
            field.set(entity, Long.valueOf(value));
          } else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
            field.set(entity, Float.valueOf(value));
          } else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
            field.set(entity, Short.valueOf(value));
          } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
            field.set(entity, Double.valueOf(value));
          } else if (Character.TYPE == fieldType) {
            if (value.length() > 0) {
              field.set(entity, value.charAt(0));
            }
          } else if (BigDecimal.class == fieldType) {
            field.set(entity, new BigDecimal(value));
          }
        }
        resultList.add(entity);
      }
    }
    return resultList;
  }

  private static boolean checkRowIsEmpty(Row row) {
    if (row == null) {
      return true;
    }
    if (row.getLastCellNum() <= 0) {
      return true;
    }
    for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
      Cell cell = row.getCell(cellNum);
      if (cell != null && cell.getCellType() != CellType.BLANK && isNotBlank(cell.toString())) {
        return false;
      }
    }
    return true;
  }


  public static Map<Integer, Field> getTitle(Sheet sheet, Class<?> clazz) {
    List<Field> fields = getMappedFiled(clazz, null);

    Map<Integer, Field> fieldsMap = Maps.newHashMap();

    Row rowHead = sheet.getRow(0);
    Map<String, Integer> cellMap = new HashMap<>();
    int cellNum = rowHead.getLastCellNum();
    for (int i = 0; i < cellNum; i++) {
      cellMap.put(rowHead.getCell(i).getStringCellValue(), i);
    }

    for (Field field : fields) {
      if (field.isAnnotationPresent(ExcelField.class)) {
        ExcelField attr = field.getAnnotation(ExcelField.class);
        Integer col = cellMap.get(attr.title());
        //validate excel template
 /*       if (col == null) {
          throw new RuntimeException("Excel template is invalid...");
        }*/
        field.setAccessible(true);
        fieldsMap.put(col, field);
      }
    }
    return fieldsMap;
  }

  private static List<Field> getMappedFiled(Class clazz, List<Field> fields) {
    if (fields == null) {
      fields = Lists.newArrayList();
    }

    Field[] allFields = clazz.getDeclaredFields();

    for (Field field : allFields) {
      if (field.isAnnotationPresent(ExcelField.class)) {
        fields.add(field);
      }
    }
    if (clazz.getSuperclass() != null
        && !clazz.getSuperclass().equals(Object.class)) {
      getMappedFiled(clazz.getSuperclass(), fields);
    }

    return fields;
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
      try {
        Iterator<T> iterator = datum.iterator();
        int rowIndex = 0;
        while (iterator.hasNext()) {
          Row row = sheet.createRow(++rowIndex);

          T object = iterator.next();
          for (String key : heads.keySet()) {
            int colIndex = 0;
            Field field = object.getClass().getField(key);
            field.setAccessible(true);
            Object value = field.get(object);
            addCell(row, colIndex++, value);
          }
        }
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        throw new RuntimeException(e.getMessage(), e);
      }
    });


  }

  public static String dealCellType(Object object) {
    if (object == null) {
      return null;
    }
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
