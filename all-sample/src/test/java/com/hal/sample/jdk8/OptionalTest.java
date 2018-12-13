package com.hal.sample.jdk8;

import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/** Created by Steven.HUANG on 2018/12/12. */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OptionalTest {

  @Test
  public void ifPresent() {
    Optional<String> optional = Optional.of("test");
    optional.ifPresent((value) -> { throw new RuntimeException("testCode");});

    System.out.println("exception after");
//    assertThat(optional.isPresent(),Matchers.is(true));
  }
}
