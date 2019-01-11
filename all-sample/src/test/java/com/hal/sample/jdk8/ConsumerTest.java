package com.hal.sample.jdk8;

import java.util.function.Consumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2018/12/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerTest {

  static void add(int a, Consumer<Integer> consumer){
    consumer.accept(a);
  }

  @Test
  public void accept(){
    add(10,val-> System.out.println(val+10));
  }
}
