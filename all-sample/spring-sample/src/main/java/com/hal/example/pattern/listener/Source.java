package com.hal.example.pattern.listener;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件源
 *
 * @Author: Steven HUANG
 * @Date: 2019/8/20
 */
public class Source {


  List<MyListener> listeners = new ArrayList<>();

  public void addMyListener(MyListener myListener) {
    listeners.add(myListener);
  }

  public void action() {
    MyEvent event = new MyEvent(this, "action");
    notifyListener(event);
  }

  private void notifyListener(MyEvent event) {
    for (MyListener listener : listeners) {
      listener.myEvent(event);
    }
  }
}
