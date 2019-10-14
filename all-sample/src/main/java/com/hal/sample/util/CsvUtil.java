package com.hal.sample.util;

import com.hal.sample.exception.BaseException;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/15
 */
@Slf4j
public class CsvUtil {

  public static void writeCSV(String fileName, char separator, Object data) {
    try (Writer writer = Files.newBufferedWriter(Paths.get(fileName))) {
      writeCSV(writer, separator, data);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
      throw new BaseException(e.getMessage(), e);
    }
  }

  public static void writeCSV(Writer writer, char separator, Object data) {
    try {
      new StatefulBeanToCsvBuilder(writer).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(separator)
          .build().write(data);
    } catch (CsvDataTypeMismatchException e) {
      log.error(e.getMessage(), e);
      throw new BaseException(e.getMessage(), e);
    } catch (CsvRequiredFieldEmptyException e) {
      log.error(e.getMessage(), e);
      throw new BaseException(e.getMessage(), e);
    }
  }

  public static <T> List<T> readCSV(String fileName, char separator, Class<T> clazz) {
    try (Reader reader = Files.newBufferedReader(Paths.get(fileName))) {
      return readCSV(reader, separator, clazz);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
      throw new BaseException(e.getMessage(), e);
    }
  }

  public static <T> List<T> readCSV(Reader reader, char separator, Class<T> clazz) {
    return new CsvToBeanBuilder(reader).withType(clazz)
        .withSeparator(separator)
        .withIgnoreLeadingWhiteSpace(true)
        .build().parse();
  }
}
