package com.hal.sample.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
}
