package com.hal.sample.base;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2019/2/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BooleanTest {

  @Test
  public void valueOf(){
    Boolean flag = null;

    Integer value = flag != null && flag ? 1 : 0;

    MatcherAssert.assertThat(value, Matchers.is(0));
  }

}
