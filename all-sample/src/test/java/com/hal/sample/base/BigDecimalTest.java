package com.hal.sample.base;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2019/1/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BigDecimalTest {

  @Test
  public void compareTo() {
    Integer value = BigDecimal.valueOf(200).compareTo(BigDecimal.valueOf(100).setScale(2));

    MatcherAssert.assertThat(value, Matchers.is(1));
  }

  @Test
  public void init() {
    System.out.println(BigDecimal.ZERO.setScale(2));
  }


  @Test
  public void add() {
    BigDecimal zero = BigDecimal.ZERO;
    BigDecimal result = zero.add(new BigDecimal(20));

    System.out.println(zero);
    System.out.println(result);
    System.out.println(new BigDecimal(20).setScale(2));

    System.out.println(new BigDecimal(10).add(new BigDecimal(-5)));
  }

  @Test
  public void rounding() {
    BigDecimal ten = BigDecimal.TEN;
    BigDecimal three = new BigDecimal(3);
    System.out.println(ten.divide(three, 2, RoundingMode.HALF_UP));

    System.out.println(new BigDecimal(-24.3).setScale(0, RoundingMode.HALF_UP));

  }

  private BigDecimal add(BigDecimal zero) {
    return zero.add(new BigDecimal(20));
  }

  @Test
  public void sub() {
    double rate = (double) (100 - 60) / (double) 100;
    BigDecimal bigDecimal = new BigDecimal(1100.89).multiply(new BigDecimal(rate)).setScale(2, RoundingMode.HALF_UP);
    System.out.println(bigDecimal);
    System.out.println(new BigDecimal(100).multiply(new BigDecimal(rate)));

    double amount = (double) 2 / (double) 100;
    System.out.println(amount);
    System.out.println(new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP));
  }


}
