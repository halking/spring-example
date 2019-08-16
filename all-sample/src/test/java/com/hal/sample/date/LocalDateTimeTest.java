package com.hal.sample.date;

import static java.time.ZoneId.SHORT_IDS;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Steven HUANG
 * @Date: 2019/6/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalDateTimeTest {

  @Test
  public void zone() {
    ZoneId zoneId = TimeZone.getDefault().toZoneId();
    System.out.println(zoneId);
    System.out.println(LocalDateTime.now(ZoneId.of(SHORT_IDS.get("VST"))));
    System.out.println(LocalDateTime.now(ZoneId.of("GMT+08:00")));
  }

  @Test
  public void get() {
    LocalDateTime now = LocalDateTime.now().plusDays(100);

    System.out.println("year : " + now.getYear());
    System.out.println("month : " + now.getMonthValue());
    System.out.println("day : " + now.getDayOfYear());
    System.out.println("hour : " + now.getHour());

    System.out.println(StringUtils.substring(Integer.toString(now.getYear()), 2));
  }

  @Test
  public void getDateTime() {
    LocalDateTime now = LocalDateTime.now(ZoneId.of("GMT"));

    System.out.println(now.minusDays(30));
    System.out.println(now.getNano());
  }

}
