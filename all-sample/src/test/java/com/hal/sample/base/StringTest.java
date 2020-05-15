package com.hal.sample.base;

import com.github.promeg.pinyinhelper.Pinyin;
import com.hal.sample.util.ChineseStringUtil;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
    System.out.println("s".startsWith("S"));
    System.out.println(value.substring(0, 4));
    System.out.println(value.length());
    MatcherAssert.assertThat(value.substring(value.length() - 3), Matchers.is("abc"));
    System.out.println(StringUtils.substring("0070039001277", 2, 8));
    System.out.println(StringUtils.substring("700390771", 0, 6));
    System.out.println(StringUtils.equalsIgnoreCase(StringUtils.substring("0070039001277", 2, 8),
        StringUtils.substring("700390771", 0, 6)));
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
    System.out.println(
        MessageFormat.format("test {0} {1}", 1, "a"));
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
    System.out.println("Alphabetic:" + str2);

    int length = 10;
    boolean useLetters = true;
    boolean useNumbers = false;
    String str3 = RandomStringUtils.random(length, useLetters, useNumbers);
    System.out.println("Bounded Random:" + str3);

  }

  @Test
  public void getRandomChinese() {
    String chineseStr = new String(new char[]{(char) (new Random().nextInt(20902) + 19968)});
    System.out.println(chineseStr);

    System.out.println(ChineseStringUtil.getChinese());
    System.out.println(ChineseStringUtil.getFixedLengthChinese(2));
    System.out.println(ChineseStringUtil.getRandomLengthChiness(1, 6));
  }

  @Test
  public void tinyPinYinTest() {
    String chineseStr = "黄胜";

    String str = Pinyin.toPinyin(chineseStr, " ").toLowerCase();

    System.out.println(StringUtils.capitalize(str));
  }

  @Test
  public void testNull() {
    String a = null;
    String test = new StringBuilder().toString();
    System.out.println("test ->: " + test);
    if (StringUtils.isNotBlank(null) && a.indexOf("/") < 0) {
      System.out.println("aa");
    }

    System.out.println(StringUtils.isEmpty(test));
    System.out.println(StringUtils.isBlank(test));
  }

  @Test
  public void testLine() {
    String value = "雅诗兰黛\n唇膏两件组";

    System.out.println(StringUtils.chomp(value));
  }

  @Test
  public void testEscape() {
    String value = "adad\\";
    System.out.println(value);
    String old = value.replaceAll("\\\\", "\\\\\\\\");
    System.out.println(old);
  }

  @Test
  public void testColon() {
    String value = "adad'";
    System.out.println(value);
    value = StringUtils.isBlank(value) ? null : value.replaceAll("'", "\\\\'");
    System.out.println(value);

  }

  @Test
  public void split() {
    String str = "SLEEPING,SVI,SLI,SLR,SLS,SLA";

    List<String> list = Arrays.asList(StringUtils.split(str, ","));

    for (String s : list) {
      System.out.println(s);
    }

    String t = "PZ0832 1 PZ0360";

    String[] tArr = StringUtils.split(t, '1');

    for (String s : tArr) {
      System.out.println(s);
    }

    String probz = "CRM100801 A CRM100802 A PZ00393 A";
    String probz1 = "CRM100801 1 CRM100802 1 PZ00393 1";
    String probznull = "CRM100801   CRM100802   PZ00393  ";

    System.out.println(getPromotionCode2(probz));
    System.out.println(getPromotionCode2(probz1));
    System.out.println(getPromotionCode2(probznull));
  }

  private Collection<String> getPromotionCode2(String probz) {
    System.out.println(StringUtils.split(probz));
    return Stream.of(StringUtils.split(probz))
        .map(StringUtils::trim)
        .filter(StringUtils::isNotBlank)
        .filter(s -> !StringUtils.equalsAny(s, "A", "1"))
        .collect(Collectors.toSet());
  }

  @Test
  public void newOldTest() {
    String str = "a";

    List<String> list = Arrays.asList(StringUtils.split(str, ","));

    for (String s : list) {
      System.out.println(s);
    }
  }

  @Test
  public void abbreviate() {
    String str = "大海度假会教大家大家";
    System.out.println(StringUtils.abbreviate(str, 9));

    String str1 = "大海度假会; 教大家dada大家";
    System.out.println(StringUtils.abbreviate(str1, 15));

    String str2 = "大海度,达到23huAd哈哈";
    System.out.println(StringUtils.abbreviate(str2, 13));

  }

  @Test
  public void remove() {
    String str = "大海度假\n会教大家\n大家\n";
    System.out.println(str);
    System.out.println(StringUtils.removeEndIgnoreCase(str, "\n"));

    System.out.println(System.currentTimeMillis());
    System.out.println(String.valueOf(System.currentTimeMillis() / 1000L));
  }


}
