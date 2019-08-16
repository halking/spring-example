package com.hal.sample.crypto;

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
    String result = passwordEncoder.encode("test2");
    System.out.println(result);

    boolean f = passwordEncoder.matches("admin", "$2a$10$KALcAngkPLIkAGqznnxWMOJMc1O1Ath0E6/F7hmd1S9Yk5iqXvZMq");
    System.out.println(f);
  }

}
