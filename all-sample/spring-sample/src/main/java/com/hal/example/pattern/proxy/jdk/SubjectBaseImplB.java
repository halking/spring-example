package com.hal.example.pattern.proxy.jdk;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/21
 */
public class SubjectBaseImplB implements SubjectBase {

  @Override
  public void request() {
    System.out.println("Proxy class B");
  }
}
