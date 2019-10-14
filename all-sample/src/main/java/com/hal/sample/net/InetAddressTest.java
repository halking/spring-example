package com.hal.sample.net;

import java.net.InetAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class InetAddressTest {

  @Test
  public void InetAddress() throws Exception {
    InetAddress address = InetAddress.getLocalHost();

    System.out.println("local address: " + address);
    System.out.println("hostName: " + address.getHostName());
    System.out.println("ip: " + address.getHostAddress());

    InetAddress[] ids = InetAddress.getAllByName("www.baidu.com");
    for (InetAddress id : ids) {
      System.out.println(id);
    }

  }
}
