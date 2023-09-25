package com.hal.example.pattern.proxy.cglib;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.FixedValue;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CglibText {

  @Test
  public void proxyClass() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(CglibClass.class);
    enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
      System.out.println("normal test before");
      Object result = methodProxy.invokeSuper(o, objects);
      System.out.println("normal test after");
      return result;
    });

    //生成代理类
    CglibClass cglibClass = (CglibClass) enhancer.create();
    cglibClass.test("test");
    cglibClass.testFinal("test");
  }

  @Test
  public void outputFixed() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(CglibClass.class);
    enhancer.setCallback((FixedValue) () -> "fixed test value");

    //生成代理类
    CglibClass cglibClass = (CglibClass) enhancer.create();
    System.out.println(cglibClass.test("test"));
    System.out.println(cglibClass.testFinal("test"));
    System.out.println(cglibClass.toString());
  }
}
