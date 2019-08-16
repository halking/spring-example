package com.hal.sample.collection;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkedHashMapTest {

  private Map<String, String> map;

  @Before
  public void init() {
    map = new LinkedHashMap<>();
  }

  @Test
  public void put(){
    map.put("a", "a1");
    map.put("b", "b1");
    map.put("c", "c1");
    map.put("d", "d1");
    map.put("e", "e1");
    map.put("f", "f1");

    for (Entry<String, String> entry : map.entrySet()) {
      System.out.println(entry.getKey() + "->" + entry.getValue());
    }

    Map<String, String> accessMap = new LinkedHashMap<>(10,0.75f, true);
    accessMap.put("a", "a1");
    accessMap.put("b", "b1");
    accessMap.put("c", "c1");
    accessMap.put("d", "d1");
    accessMap.put("e", "e1");
    accessMap.put("f", "f1");

    for (Entry<String, String> entry : accessMap.entrySet()) {
      System.out.println(entry.getKey() + "->" + entry.getValue());
    }

    accessMap.get("a");
    accessMap.get("c");

    for (Entry<String, String> entry : accessMap.entrySet()) {
      System.out.println(entry.getKey() + "->" + entry.getValue());
    }
  }
}
