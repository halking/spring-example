package com.hal.sample.jdk8;

import java.util.function.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2018/12/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PredicateTest {

  @Test
  public void testA() {
    test(o -> o instanceof Long);
  }

  @Test
  public void testB() {
    test(o -> o instanceof String);
  }

  private void test(Predicate<Object> predicate) {

    if (predicate.test("test")) {
      System.out.println(">>>>>>>>>>" + "test");
    } else if (predicate.test(2L)) {
      System.out.println(">>>>>>> " + 2L);
    }
  }
}
