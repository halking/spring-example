package com.hal.sample.base;

import java.text.MessageFormat;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2019/1/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringTest {


  @Test
  public void stringAndInteger(){
    String suffix = "001";
    Integer value = Integer.valueOf(suffix);

    MatcherAssert.assertThat(String.format("%03d",20), Matchers.is("020"));
    MatcherAssert.assertThat(String.format("%03d",2), Matchers.is("002"));
    MatcherAssert.assertThat(String.format("%03d",200), Matchers.is("200"));

    MatcherAssert.assertThat(value, Matchers.is(1));
  }

  @Test
  public void subString(){
    String value = "testabc";

    MatcherAssert.assertThat(value.substring(value.length()-3), Matchers.is("abc"));
  }

  @Test
  public void LongToString(){
    Long value = 3002L;

    System.out.println(value.toString());
    MatcherAssert.assertThat(value.toString(), Matchers.is("3002"));
    MatcherAssert.assertThat(Long.toString(value), Matchers.is("3002"));
    System.out.println(Long.toString(value));
  }

  @Test
  public void format(){
    System.out.println(MessageFormat.format("test {0}",1));
  }

  @Test
  public void test(){
    String str = "FY2019";

    Boolean flag = str.startsWith("FY");

    System.out.println(str.indexOf("FY"));
    String first = str.substring("FY".length());

    MatcherAssert.assertThat(flag, Matchers.is(true));
    MatcherAssert.assertThat(first, Matchers.is("2019"));
  }

}
