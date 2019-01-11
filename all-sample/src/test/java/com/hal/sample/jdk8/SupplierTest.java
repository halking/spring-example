package com.hal.sample.jdk8;

import java.util.function.Supplier;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2018/12/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierTest {

  static int add(Supplier<Integer> supplier) {
    return supplier.get();
  }

  @Test
  public void get(){
    int r = add(()->10);

    MatcherAssert.assertThat(r, Matchers.is(10));
  }

}
