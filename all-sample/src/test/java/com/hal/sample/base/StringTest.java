package com.hal.sample.base;

import com.github.promeg.pinyinhelper.Pinyin;
import com.hal.sample.util.ChineseStringUtil;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
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
  public void stringAndInteger() {
    String suffix = "001";
    Integer value = Integer.valueOf(suffix);

    MatcherAssert.assertThat(String.format("%03d", 20), Matchers.is("020"));
    MatcherAssert.assertThat(String.format("%03d", 2), Matchers.is("002"));
    MatcherAssert.assertThat(String.format("%03d", 200), Matchers.is("200"));

    MatcherAssert.assertThat(value, Matchers.is(1));
  }

  @Test
  public void subString() {
    String value = "testabc";

    MatcherAssert.assertThat(value.substring(value.length() - 3), Matchers.is("abc"));
  }

  @Test
  public void LongToString() {
    Long value = 3002L;

    System.out.println(value.toString());
    MatcherAssert.assertThat(value.toString(), Matchers.is("3002"));
    MatcherAssert.assertThat(Long.toString(value), Matchers.is("3002"));
    System.out.println(Long.toString(value));
  }

  @Test
  public void format() {
    System.out.println(MessageFormat.format("test {0}", 1));
  }

  @Test
  public void test() {
    String str = "FY2019";

    Boolean flag = str.startsWith("FY");

    System.out.println(str.indexOf("FY"));
    String first = str.substring("FY".length());

    MatcherAssert.assertThat(flag, Matchers.is(true));
    MatcherAssert.assertThat(first, Matchers.is("2019"));
  }

  @Test
  public void randomStr() {
    String str1 = RandomStringUtils.randomAlphanumeric(10);
    System.out.println("Alphanumeric :" + str1);

    String str2 = RandomStringUtils.randomAlphabetic(10);
    System.out.println("Alphabetic:"+str2);

    int length = 10;
    boolean useLetters = true;
    boolean useNumbers = false;
    String str3 = RandomStringUtils.random(length, useLetters, useNumbers);
    System.out.println("Bounded Random:"+str3);

  }

  @Test
  public void getRandomChinese() {
    String chineseStr = new String(new char[] { (char) (new Random().nextInt(20902) + 19968) });
    System.out.println(chineseStr);

    System.out.println(ChineseStringUtil.getChinese());
    System.out.println(ChineseStringUtil.getFixedLengthChinese(2));
    System.out.println(ChineseStringUtil.getRandomLengthChiness(1,6));
  }

  @Test
  public void tinyPinYinTest() {
    String chineseStr = "黄胜";

    String str = Pinyin.toPinyin(chineseStr," ").toLowerCase();

    System.out.println(StringUtils.capitalize(str));
  }

  @Test
  public void testNull(){
    String a = null;

    if (StringUtils.isNotBlank(null) && a.indexOf("/") < 0){
      System.out.println("aa");
    }
  }

  @Test
  public void testLine(){
    String value = "雅诗兰黛\n唇膏两件组";

    System.out.println(StringUtils.chomp(value));
  }

  @Test
  public void testEscape(){
    String value = "adad\\";
    System.out.println(value);
    String old = value.replaceAll("\\\\","\\\\\\\\");
    System.out.println(old);
  }

  @Test
  public void testColon(){
    String value = "adad'";
    System.out.println(value);
    value = StringUtils.isBlank(value) ? null : value.replaceAll("'","\\\\'");
    System.out.println(value);

  }

  @Test
  public void split(){
    String str = "SLEEPING,SVI,SLI,SLR,SLS,SLA";

    List<String> list = Arrays.asList(StringUtils.split(str,","));

    for (String s : list) {
      System.out.println(s);
    }
  }

  @Test
  public void newOldTest(){
    String str = "a";

    List<String> list = Arrays.asList(StringUtils.split(str,","));

    for (String s : list) {
      System.out.println(s);
    }
  }

}
