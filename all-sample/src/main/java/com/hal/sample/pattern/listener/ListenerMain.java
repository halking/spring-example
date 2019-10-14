package com.hal.sample.pattern.listener;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/20
 */
public class ListenerMain {

  public static void main(String[] args) {
    Source source = new Source();
    source.addMyListener(event -> System.out.println(event.getType()));
    source.action();
  }
}
