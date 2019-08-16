package com.hal.sample.collection;

import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TreeMapTest {

  private NavigableMap<String, String> map;

  @Before
  public void init() {
    map = new TreeMap<>();
    map.put("a", "a1");
    map.put("b", "b1");
    map.put("d", "d1");
    map.put("e", "e1");
    map.put("f", "f1");
    map.put("c", "c1");
    map.put("h", "h1");
    map.put("s", "s1");
  }

  @Test
  public void get() {
    for (Entry<String, String> entry : map.entrySet()) {
      System.out.println(entry.getKey() + "->" + entry.getValue());
    }
  }

  @Test
  public void firstEntry() {
    Entry<String, String> entry = map.firstEntry();
    System.out.println(entry.getKey() + "->" + entry.getValue());
  }

  @Test
  public void lastEntry() {
    Entry<String, String> entry = map.lastEntry();
    System.out.println(entry.getKey() + "->" + entry.getValue());
  }

  /**
   * 返回 大于给定key的最小Entry
   */
  @Test
  public void higherEntry() {
    Entry<String, String> entry = map.higherEntry("c");
    System.out.println(entry.getKey() + "->" + entry.getValue());
  }

  /**
   * 返回 小于给定key的最大的Entry
   */
  @Test
  public void lowerEntry() {
    Entry<String, String> entry = map.lowerEntry("c");
    System.out.println(entry.getKey() + "->" + entry.getValue());
  }

  /**
   * 返回 小于等于给定key的最大的Entry
   */
  @Test
  public void floorEntry() {
    Entry<String, String> entry = map.floorEntry("g");
    System.out.println(entry.getKey() + "->" + entry.getValue());
  }

  /**
   * 返回 大于等于给定key的最小Entry
   */
  @Test
  public void ceilingEntry() {
    Entry<String, String> entry = map.ceilingEntry("g");
    System.out.println(entry.getKey() + "->" + entry.getValue());
  }

  /**
   * 返回 from fromKey to toKey 的子map  左闭右开
   */
  @Test
  public void subMap() {
    SortedMap<String, String> subMap = map.subMap("b", "e");
    for (Entry<String, String> entry : subMap.entrySet()) {
      System.out.println(entry.getKey() + "->" + entry.getValue());
    }

  }

  /**
   * 返回 不包含key之前的 head map entry
   */
  @Test
  public void headMap() {
    SortedMap<String, String> headMap = map.headMap("c");
    for (Entry<String, String> entry : headMap.entrySet()) {
      System.out.println(entry.getKey() + "->" + entry.getValue());
    }
  }

}
