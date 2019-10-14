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

    List<String> users = Lists.newArrayList("valentina.xie@herschel.com",
        "isabella.liu@herschel.com",
        "zhuofeng.gu@baozun.com",
        "xinxing.li@baozun.com",
        "xiaoqing.wang@baozun.com",
        "jieting.fu@baozun.com",
        "ling.chen1@baozun.com",
        "ryan.zhao@herschel.com",
        "vivian.chen@herschel.com",
        "jiahao.chen1@baozun.com",
        "wenjie.ji@baozun.com");

    for (String user : users) {
      String pwd = "Herschel" + RandomStringUtils.randomNumeric(4);
      deMap.put(user, pwd);
      String result = passwordEncoder.encode(pwd);
      enMap.put(user, result);
    }

    deMap.entrySet().forEach(entry -> {
      System.out.println(entry.getKey() + "=" + entry.getValue());
    });

    enMap.entrySet().forEach(entry -> {
      System.out.println(entry.getKey() + "=" + entry.getValue());
    });
  }

  @Test
  public void adminTest(){
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String pwd = "Ray@ban!2";
    String result = passwordEncoder.encode(pwd);
    System.out.println(result);
  }

}
