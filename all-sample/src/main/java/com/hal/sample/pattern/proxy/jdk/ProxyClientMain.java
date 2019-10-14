package com.hal.sample.pattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/21
 */
public class ProxyClientMain {

  public static void main(String[] args) {

    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

    //代理类
    InvocationHandler handler = new ProxyBase(new SubjectBaseImplA());

    //获取代理类实例
    SubjectBase subjectProxy = (SubjectBase) Proxy.newProxyInstance(SubjectBase.class.getClassLoader(),
        new Class[]{SubjectBase.class}, handler);

    //调用方法, 内部执行由代理类实现的Invoke方法接管
    subjectProxy.request();
  }

}
