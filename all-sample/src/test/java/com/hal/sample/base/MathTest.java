package com.hal.sample.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2019/1/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MathTest {

  @Test
  public void parseInteger() {
    Integer i = null;
    System.out.println(Integer.toString(i));
  }

  @Test
  public void test1() {
    int index1 = 1 % 8;
    int index2 = 2 % 8;
    int index3 = 3 % 8;
    int index4 = 4 % 8;
    int index5 = 5 % 8;
    int index6 = 6 % 8;
    int index7 = 7 % 8;
    int index8 = 8 % 8;
    System.out.println(index1);
    System.out.println(index2);
    System.out.println(index3);
    System.out.println(index4);
    System.out.println(index5);
    System.out.println(index6);
    System.out.println(index7);
    System.out.println(index8);
  }
}
