package com.hal.sample.collection;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArrayListTest {

  @Test
  public void init(){
    List<Integer> intList = new ArrayList();
    System.out.println(intList.size());
/*    intList.add(1);
    System.out.println(intList.size());*/

    for (int i = 0; i < 10; i++) {
      intList.add(i);
    }

    intList.add(10);

    System.out.println(intList.size());
  }

}
