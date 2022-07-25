package com.hal.sample.crypto;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
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

    List<String> users = Lists.newArrayList("nuttAdmin");

    for (String user : users) {
      String pwd = StringUtils.substring(user, 0 ,StringUtils.indexOf(user, "@"));
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
    String pwd = "CS@lacoste";
    String result = passwordEncoder.encode(pwd);
    System.out.println(result);
  }

  @Test
  public void md5() {
    String timestamp = String.valueOf(System.currentTimeMillis());
    String token = DigestUtils.md5Hex(timestamp + "exiangsui2020");
    System.out.println("timestamp:"+timestamp + "\n token:"+token);
  }

}
