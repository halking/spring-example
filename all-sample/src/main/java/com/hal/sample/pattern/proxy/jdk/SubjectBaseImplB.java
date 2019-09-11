package com.hal.sample.pattern.proxy.jdk;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/21
 */
public class ProxyBaseImplB implements ProxyBase {

  @Override
  public void request() {
    System.out.println("Proxy class B");
  }
}
