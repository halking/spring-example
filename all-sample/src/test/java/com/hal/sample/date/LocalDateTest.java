package com.hal.sample.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
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

}
