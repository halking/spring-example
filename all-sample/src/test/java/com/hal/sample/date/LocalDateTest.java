package com.hal.sample.date;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2019/1/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalDateTest {

  private LocalDate now = LocalDate.now();

  @Test
  public void formatter() {
    LocalDateTime dateTime = now.atStartOfDay();
    System.out.println(dateTime);
//    MatcherAssert.assertThat(dateTime, Matchers.is("2019-01"));
  }

  @Test
  public void parseYear() {
    Year year = Year.now();
    System.out.println(year.toString());
  }

  @Test
  public void month() {
    LocalDate date = LocalDate.of(2019, 1, 1);
    String lastMonth = date.minusMonths(1).format(ofPattern("yyyy/MM"));
    System.out.println(lastMonth);
  }

  @Test
  public void parseZone() {
    String dateStr = "2019-01-02";
    String dateTimeStr = "2019-01-02 00:00:00";
    String s1 = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay()
        .toInstant(ZoneOffset.of("+08:00")).toString();
    System.out.println(s1);

    String s2 = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay()
        .atZone(ZoneId.of("Asia/Shanghai")).toString();
    System.out.println(s2);

/*    String s3 = ZonedDateTime.parse("2019-01-02").format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    System.out.println(s3);*/

    System.out.println(ZonedDateTime.now());

    ZoneId zoneId = ZoneId.of("+08:00");

    LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String s4 = ZonedDateTime.of(dateTime, zoneId).toString();
    System.out.println(s4);

    String s5 = LocalDate.parse(dateStr).atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    System.out.println(s5);
    System.out
        .println(LocalDate.now().atStartOfDay(zoneId).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")));

    String s6 = LocalDateTime.of(LocalDate.parse(dateStr), LocalTime.of(0, 0, 0)).atZone(zoneId).toString();
    System.out.println(s6);

  }

  @Test
  public void getTime() {
    System.out.println(new Date().getTime());
  }

}
