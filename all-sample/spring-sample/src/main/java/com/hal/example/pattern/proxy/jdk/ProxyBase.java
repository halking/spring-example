package com.hal.example.pattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/21
 */
public class ProxyBase implements InvocationHandler {

  private SubjectBase target;

  public ProxyBase(SubjectBase object) {
    this.target = object;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    return method.invoke(target, args);
  }
}
