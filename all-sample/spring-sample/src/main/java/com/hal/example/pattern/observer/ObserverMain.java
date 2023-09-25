package com.hal.example.pattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/20
 */
public class ObserverMain {

  public static void main(String[] args) {
    Observer observer = new MyObserver();
    Observable observable = new MyObservable();
    observable.addObserver(observer);
    ((MyObservable) observable).action("test observer");
  }
}
