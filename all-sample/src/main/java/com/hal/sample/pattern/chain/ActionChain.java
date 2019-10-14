package com.hal.sample.pattern.chain;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/20
 */
public interface ActionChain {

  void doAction(ActionInBean inBean, ActionOutBean outBean);
}
