package com.hal.sample.base;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2019/1/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DoubleTest {


  @Test
  public void baseTest() {
    Double value = Double.valueOf(20);
    MatcherAssert.assertThat(value, Matchers.is(20.0));

  }
}
