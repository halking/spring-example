package com.hal.sample.pattern.observer;

import java.util.Observable;

/**
 * 被观察者
 *
 * @Author: Steven HUANG
 * @Date: 2019/8/20
 */
public class MyObservable extends Observable {

  private Object value;

  public Object getValue() {
    return value;
  }

  public void action(Object data) {
    this.value = data;
    setChanged();
    notifyObservers(data);
  }
}
