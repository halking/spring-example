package com.hal.sample.enums;

import static com.hal.sample.enums.AdjustmentType.Add;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2018/12/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnumsTest {

  @Test
  public void testEnumString(){
    String type = "Add";
    System.out.println(type.equals(Add));

    MatcherAssert.assertThat(type,equals(Add));
  }
}
