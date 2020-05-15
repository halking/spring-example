package com.hal.sample.pattern.listener;

import java.util.EventListener;

/**
 * 监听器
 *
 * @Author: Steven HUANG
 * @Date: 2019/8/20
 */
public interface MyListener extends EventListener {

  void myEvent(MyEvent event);

}
