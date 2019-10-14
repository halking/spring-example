package com.hal.sample.collection;

import com.google.common.collect.Maps;
import com.google.common.collect.Maps.EntryTransformer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HashMapTest {

  private Map<String, String> map = new HashMap<>();

  @Before
  public void init() {
    map.put("a", "a1");
    map.put("b", "b1");
  }

  @Test
  public void transformValues() {
    Map<String, String> newMaps = Maps.transformValues(map, input -> input + "t");
    System.out.println(newMaps);
  }

  @Test
  public void transformEntries() {
    EntryTransformer<String, String, String> transformer = new EntryTransformer<String, String, String>() {
      @Override
      public String transformEntry(@Nullable String key, @Nullable String value) {
        return key;
      }
    };
  }

  @Test
  public void put() {
    Map<String, String> mapPut = new HashMap<>();
    mapPut.put("a", "a");

    mapPut.put("a", "b");
    System.out.println();

    mapPut.values();
    Set<String> keys = mapPut.keySet();
    for (String key : keys) {

    }
  }
}
