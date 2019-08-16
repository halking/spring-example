package com.hal.sample.base;

/**
 * Created by Steven.HUANG on 2019/1/25.
 */
public class BaseB extends BaseA {

  public BaseB() {
    System.out.println("HelloB");
  }

  {
    System.out.println("I'm B class");
  }

  static {
    System.out.println("static B");
  }

}
