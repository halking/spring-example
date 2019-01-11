package com.hal.sample.jdk8;

import java.util.function.Function;
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
public class FunctionTest {

  public static int add(int num, Function<Integer, Integer> function) {
    return function.apply(num);
  }

  public static int add(int num, Function<Integer, Integer> function,
      Function<Integer, Integer> function2) {
    return function.andThen(function2).apply(num);
  }

  @Test
  public void apply() {
    int num = 10;
    int result = add(num, arg -> arg + 10);

    MatcherAssert.assertThat(result, Matchers.is(20));

    int r2 = add(num, new Function<Integer, Integer>() {
      @Override
      public Integer apply(Integer integer) {
        return integer + 10;
      }
    });

    MatcherAssert.assertThat(r2, Matchers.is(20));
  }

  @Test
  public void andThen() {
    int r = add(10, val -> val + 10, val -> val + 10);
    MatcherAssert.assertThat(r, Matchers.is(30));
  }
}
