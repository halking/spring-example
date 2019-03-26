package com.hal.sample.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Steven.HUANG on 2019/3/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UriComponentTest {

  @Test
  public void paramTest(){
    UriComponents  components = UriComponentsBuilder
        .fromPath("https://localhost:8080/uri/test?a=a&b=b").queryParam("a","a=a")
        .buildAndExpand("uriValue");

    System.out.println(components.getPathSegments());
    System.out.println(components.getFragment());
    System.out.println(components.getQuery());
    System.out.println(components.getQueryParams().get("a"));
    System.out.println(components.getUserInfo());
  }

}
