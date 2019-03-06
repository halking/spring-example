package com.hal.sample.base;

import java.math.BigDecimal;
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
  public void compareTo(){
    Integer value = BigDecimal.valueOf(200).compareTo(BigDecimal.valueOf(100).setScale(2));

    MatcherAssert.assertThat(value, Matchers.is(1));
  }

  @Test
  public void init(){
    System.out.println(BigDecimal.ZERO.setScale(2));
  }
}
