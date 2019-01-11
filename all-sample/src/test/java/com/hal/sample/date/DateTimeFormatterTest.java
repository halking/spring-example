package com.hal.sample.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
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
  public void setup(){
    now = LocalDateTime.now();
  }

  @Test
  public void formatter(){
    String date = now.format(DateTimeFormatter.ofPattern("dd/MM"));

    MatcherAssert.assertThat(date, Matchers.is("10/01"));
  }
}
