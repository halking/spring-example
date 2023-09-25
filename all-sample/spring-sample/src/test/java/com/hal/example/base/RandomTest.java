package com.hal.example.base;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * @Author: Steven HUANG
 * @Date: 2019/6/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomTest {

  @Test
  public void nextInt() {
    Random random = new Random();
    System.out.println(random.nextInt(1));
    System.out.println(random.nextInt(1));
    System.out.println(random.nextInt(1));
  }

  @Test
  public void randomString() {
    System.out.println(RandomStringUtils.random(8));
    System.out.println(RandomStringUtils.random(8, true, true));
    System.out.println(RandomStringUtils.random(10, 5, 129, true, true));
    System.out.println(RandomStringUtils.randomAlphanumeric(8));
    System.out.println(RandomStringUtils.randomAlphanumeric(5, 15));
    System.out.println(RandomStringUtils.randomNumeric(15, 20));
    System.out.println(RandomStringUtils.randomAlphabetic(32));
  }
}