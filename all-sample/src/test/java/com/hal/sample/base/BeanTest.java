package com.hal.sample.base;

import com.hal.sample.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2019/3/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanTest {


  @Test
  public void beanCopy() {
    User oldUser = User.builder().name("huang").age(30).build();
    User newUser = oldUser;

    newUser.setAge(40);
    System.out.println(newUser);
    System.out.println(oldUser);
    System.out.println(oldUser == newUser);

  }
}
