package com.hal.sample.pattern.listener;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件源
 *
 * @Author: Steven HUANG
 * @Date: 2019/8/20
 */
public class SourceConext {

  List<MyListener> listeners = new ArrayList<>();

  List<Source> sources = new ArrayList<>();

  public void addMyListener(MyListener myListener) {
    listeners.add(myListener);
  }

  public void addSource(Source source) {
    sources.add(source);
  }


  public void action() {
    for (Source source : sources) {
      MyEvent event = new MyEvent(source, "action");
      notifyListener(event);
    }

  }

  private void notifyListener(MyEvent event) {
    for (MyListener listener : listeners) {
      listener.myEvent(event);
    }
  }
}
