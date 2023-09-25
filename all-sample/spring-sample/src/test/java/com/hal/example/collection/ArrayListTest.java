package com.hal.example.collection;

import com.hal.sample.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/5
 */
@Slf4j
@RunWith(JUnit4.class)
public class ArrayListTest {

  @Test
  public void init() {
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

  @Test
  public void demo() {
    List<Integer> intList = new ArrayList();
    System.out.println(intList.size());
/*    intList.add(1);
    System.out.println(intList.size());*/

    for (int i = 0; i < 10; i++) {
      intList.add(i);
    }
    System.out.println(intList.size());
    Integer[] arr = intList.toArray(new Integer[0]);
    System.out.println(arr.length);
    for (Integer integer : arr) {
      System.out.println(integer);
    }


  }

  @Test
  public void sizeTest() throws Exception {
    List<Integer> list = new ArrayList();
    log.info("init size : {}", list.size());

/*    intList.add(1);
    System.out.println(intList.size());*/

    for (int i = 0; i < 10; i++) {
      list.add(i);
    }

    log.info("after add size : {}", list.size());

    int step = 10;
    int size = list.size();
    for (int i = 0; i < size; i += step) {
      log.info("开始第[{}]次拆分List", i);
      List<Integer> subList;
      if (size - i < step) {
        subList = list.subList(i, size);
      } else {
        subList = list.subList(i, step + i);
      }

      log.info("结束第[{}]次拆分List, {}", i, JsonUtil.objectToJson(subList));
    }

  }

  @Test
  public void isEqualCollection() {
    List<Integer> list1 = new ArrayList();
    List<Integer> list2 = new ArrayList();

    for (int i = 0; i < 10; i++) {
      list1.add(i);
    }

    for (int i = 9; i >= 0; i--) {
      list2.add(i);
    }

    System.out.println(CollectionUtils.isEqualCollection(list1, list2));
  }
}