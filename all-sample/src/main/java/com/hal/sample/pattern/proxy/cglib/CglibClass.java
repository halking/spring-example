package com.hal.sample.pattern.proxy.cglib;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/21
 */
@Data
@NoArgsConstructor
public class CglibClass {

  private Integer value;

  public CglibClass(Integer value) {
    this.value = value;
  }

  public String test(String text) {
    return "this is normal " + text;
  }

  public static String testStatic(String text) {
    return "this is static " + text;
  }

  public final String testFinal(String text) {
    return "this is final " + text;
  }
}
