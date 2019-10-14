package com.hal.sample.pattern.listener;

import java.util.EventObject;

/**
 * 事件
 *
 * @Author: Steven HUANG
 * @Date: 2019/8/20
 */
public class MyEvent extends EventObject {

  private String type;

  /**
   * Constructs a prototypical Event.
   *
   * @param source The object on which the Event initially occurred.
   * @throws IllegalArgumentException if source is null.
   */
  public MyEvent(Source source, String type) {
    super(source);
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
