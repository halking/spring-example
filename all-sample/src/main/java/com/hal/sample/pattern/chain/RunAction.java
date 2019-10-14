package com.hal.sample.pattern.chain;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/20
 */
public class RunAction implements Action {

  @Override
  public void doAction(ActionInBean inBean, ActionOutBean outBean, ActionChain actionChain) {
    System.out.println("Run action");
    actionChain.doAction(inBean, outBean);
  }
}
