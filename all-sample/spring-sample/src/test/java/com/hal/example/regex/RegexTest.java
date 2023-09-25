package com.hal.example.regex;

import com.hal.sample.common.Constant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: Steven HUANG
 * @Date: 2019/9/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RegexTest {

  @Test
  public void StrTest() {
    String regex = "CAR_\\w+_%s";

    String text = "CAR_TEST_20191018";

    String s = LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constant.YYYYMMDD));

    String file = String.format(regex, s);

    System.out.println(text.matches(file));
  }
}
