package com.hal.sample.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BitMainpulationTest {

  /**
   * ^(按位异或运算) ，针对二进制，相同的为0，不同的为1
   */
  @Test
  public void yiHuoTest() {

  }

  /**
   * &（按位与运算） 针对二进制，只要有一个为0，就为0
   */
  @Test
  public void yuTest() {
    int v1 = 5;
    int v2 = 6;
    System.out.println(v1 & 0);
    System.out.println(v1 & 1);
    System.out.println(v1 & 2);
    System.out.println(v1 & 3);
    System.out.println(v1 & 4);
    System.out.println(v1 & 5);
    System.out.println(v1 & 6);

    System.out.println(Integer.toBinaryString(2));
  }

  /**
   * |（按位或运算） 针对二进制，只要有一个为0，就为0
   */
  @Test
  public void huoTest() {

  }

  /**
   * <<（左移）
   */
  @Test
  public void leftMoveTest() {

  }

  /**
   * >>（右移）
   */
  @Test
  public void rightMoveTest() {

  }

  /**
   * >>>（无符号右移）
   */
  @Test
  public void unRightMoveTest() {

  }
}
