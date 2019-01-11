package com.hal.sample.jdk8;

import com.hal.sample.entity.User;
import java.time.LocalDate;
import java.util.Optional;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/** Created by Steven.HUANG on 2018/12/12. */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OptionalTest {

  private Optional<User> optionalUser;

  @Before
  public void setup(){
    User user = User.builder().name("name").birthday(LocalDate.now()).gender(1).age(22).build();
    optionalUser =Optional.of(user);
  }

  /**
   * 存在才对它做点什么
   */
  @Test
  public void ifPresent() {
    Optional<String> optional = Optional.of("test");
    optional.ifPresent((value) -> { throw new RuntimeException("testCode");});

    System.out.println("exception after");
//    assertThat(optional.isPresent(),Matchers.is(true));
  }

  /**
   * 存在即返回, 无则提供默认值
   */
  @Test
  public void orElse(){

  }
  /**
   * 存在即返回, 无则由函数来产生
   */
  @Test
  public void orElseGet(){

  }

  @Test
  public void map(){
    String name  = optionalUser.map(user -> user.getName())
        .orElse("test");

    MatcherAssert.assertThat(name, Matchers.is("name"));

    String address  = optionalUser.map(user -> user.getAddress())
        .orElse("test");

    MatcherAssert.assertThat(address, Matchers.is("test"));
  }

  @Test
  public void flatMap(){

  }

  /**
   * 存在即返回, 无则抛出异常
   */
  @Test
  public void orElseThrow(){

  }
}
