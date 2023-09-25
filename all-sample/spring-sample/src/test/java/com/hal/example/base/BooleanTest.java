package com.hal.example.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * Created by Steven.HUANG on 2019/2/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BooleanTest {

  @Test
  public void valueOf() {
    Boolean flag = null;

    Integer value = flag != null && flag ? 1 : 0;

    MatcherAssert.assertThat(value, Matchers.is(0));
  }

  @Test
  public void nullOr() {
    String flag = null;

    if (flag == null || flag.equals("a")) {
      System.out.println("null or");
    }

    if (flag.equals("a") || flag == null) {
      System.out.println("null or");
    }

    MatcherAssert.assertThat(flag, Matchers.is(0));

    System.out.println(BooleanUtils.isTrue(null));
  }

  @Test
  public void booleanNull() {
    Boolean flag = null;

    if (flag != null && flag) {

    }

    LocalDateTime s = null;

    System.out.println(s != null);

    if (s != null) {
    }

    System.out.println(BooleanUtils.isTrue(null));

  }

  @Test
  public void testTernary() {
    Boolean flag1 = null;
    Integer i1 = flag1 != null ? flag1 ? 1 : 2 : 0;
    System.out.println(i1);

    Boolean flag2 = false;
    Integer i2 = flag2 != null ? flag2 ? 1 : 2 : 0;
    System.out.println(i2);

    Boolean flag3 = true;
    Integer i3 = flag3 != null ? flag3 ? 1 : 2 : 0;
    System.out.println(i3);
  }

}
