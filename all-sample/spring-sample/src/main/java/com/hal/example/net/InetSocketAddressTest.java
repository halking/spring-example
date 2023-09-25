package com.hal.example.net;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class InetSocketAddressTest {

  @Test
  public void InetSocketAddress() throws Exception {
    InetSocketAddress socketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 3306);
    System.out.println(socketAddress);
    InetAddress inetAddress = socketAddress.getAddress();
    System.out.println("主机信息:" + inetAddress);
    int port = socketAddress.getPort();
    System.out.println("端口号:" + port);
    String hostName = socketAddress.getHostName();
    System.out.println("主机名:" + hostName);
  }
}
