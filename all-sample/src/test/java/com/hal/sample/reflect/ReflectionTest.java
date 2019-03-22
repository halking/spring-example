package com.hal.sample.reflect;

import com.hal.sample.entity.User;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

/**
 * Created by Steven.HUANG on 2019/3/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReflectionTest {

  private User user;

  @Test
  public void field(){
    Class clazz = new User().getClass();
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      String fieldName = field.getName();
      System.out.println(fieldName);
      Method method = ReflectionUtils.findMethod(clazz,fieldName);
    }
  }

  @Test
  public void method(){

  }
}
