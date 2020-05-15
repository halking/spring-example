package com.hal.sample.date;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2019/1/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DateTimeFormatterTest {

  private LocalDateTime now;

  @Before
  public void setup() {
    now = LocalDateTime.now();
  }

  @Test
  public void formatter() {
    String date = now.format(DateTimeFormatter.ofPattern("dd/MM"));
    System.out.println(now.format(DateTimeFormatter.ofPattern("mmss")));
//    MatcherAssert.assertThat(date, Matchers.is("29/05"));

    System.out.println();
  }

  @Test
  public void multiFormat() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd][dd/MM/yyyy][MM-dd-yyyy]");
    LocalDate.parse("09-23-2018", formatter);
    LocalDate.parse("23/09/2018", formatter);
    LocalDate.parse("2018-09-23", formatter);
  }

  @Test
  public void multiFormatBuild() {
    DateTimeFormatter formattert = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:'0'ss");
    LocalDateTime dateTime = LocalDateTime.parse("2020-04-20 14:01:001", formattert);

    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
        .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:0ss"))
        .optionalStart().appendPattern("yyyy-MM-dd HH:mm:ss").optionalEnd()
//        .optionalStart().appendPattern("yyyy-MM-dd HH:mm:ss.SSS").optionalEnd()
        .optionalStart().appendPattern("yyyy-MM-dd HH:mm").optionalEnd()
        .optionalStart().appendPattern("yyyy/MM/dd HH:mm:0ss").optionalEnd()
        .optionalStart().appendPattern("yyyy/MM/dd HH:mm:ss").optionalEnd()
//        .optionalStart().appendPattern("yyyy/MM/dd HH:mm:ss.SSS").optionalEnd()
        .optionalStart().appendPattern("yyyy/MM/dd HH:mm").optionalEnd()
        .toFormatter();

    LocalDateTime.parse("2020-04-20 14:01:11", formatter);
    LocalDateTime.parse("2020-04-20 14:01:041", formatter);
//    LocalDateTime.parse("2020-04-20 14:01:01.234", formatter);
    LocalDateTime dateTime1 = LocalDateTime.parse("2020-04-20 14:01", formatter);

    LocalDateTime.parse("2020/04/20 14:01:11", formatter);
    LocalDateTime.parse("2020/04/20 14:01:001", formatter);
//    LocalDateTime.parse("2020/04/20 14:01:01.234", formatter);
    LocalDateTime dateTime2 = LocalDateTime.parse("2020/04/20 14:06", formatter);

    Duration duration = Duration.between(dateTime1, dateTime2);

    System.out.println(duration.getSeconds());
  }
}
