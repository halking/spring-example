package com.hal.example.pattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/20
 */
public class MyObserver implements Observer {

  @Override
  public void update(Observable o, Object arg) {
    System.out.println(((MyObservable) o).getValue());
  }
}
