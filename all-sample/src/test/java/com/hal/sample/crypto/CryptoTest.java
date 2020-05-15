package com.hal.sample.crypto;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CryptoTest {

  @Test
  public void BCryptPasswordEncoderTest() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    Map<String, String> enMap = Maps.newLinkedHashMap();

    Map<String, String> deMap = Maps.newLinkedHashMap();

    List<String> users = Lists.newArrayList("steven.huang@digibridge.cn",
        "philippe.han@digibridge.cn",
        "lacoste.cs@galaxeed.com",
        "CrHuang@lacoste.com",
        "SaYang@lacoste.com",
        "DLu@lacoste.com",
        "wwang@lacoste.com",
        "Jcao@lacoste.com");

    for (String user : users) {
      String pwd = "Lac@" + RandomStringUtils.randomAlphanumeric(6);
      deMap.put(user, pwd);
      String result = passwordEncoder.encode(pwd);
      enMap.put(user, result);
    }

    deMap.entrySet().forEach(entry -> {
      System.out.println(entry.getKey() + ":" + entry.getValue());
    });

    enMap.entrySet().forEach(entry -> {
      System.out.println(entry.getKey() + "=" + entry.getValue());
    });
  }

  @Test
  public void adminTest() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String pwd = "Lacoste@Admin";
    String result = passwordEncoder.encode(pwd);
    System.out.println(result);
  }

}
