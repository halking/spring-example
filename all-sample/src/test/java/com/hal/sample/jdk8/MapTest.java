package com.hal.sample.jdk8;

import com.google.common.collect.Maps;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2018/12/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapTest {

  private Map<String, Integer> mapA;

  @Before
  public void setup() {

    mapA = Maps.newHashMap();

  }

  @Test
  public void valueNull() {

    mapA.put("a", 1);
    mapA.put("b", 1);

    System.out.println(mapA.get("c"));

  }

}
