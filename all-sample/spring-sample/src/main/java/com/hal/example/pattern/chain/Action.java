package com.hal.example.pattern.chain;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/20
 */
public interface Action {

  default void init() {
  }

  void doAction(ActionInBean inBean, ActionOutBean outBean, ActionChain actionChain);

  default void afterProcess() {
  }
}
