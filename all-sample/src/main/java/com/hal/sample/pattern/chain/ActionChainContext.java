package com.hal.sample.pattern.chain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/20
 */
public class ActionChainContext implements ActionChain {

  private List<Action> actions = new ArrayList<>();

  public ActionChainContext() {
    this.actions.add(new RunAction());
    this.actions.add(new SleepAction());
  }

  private AtomicInteger index = new AtomicInteger(0);

  @Override
  public void doAction(ActionInBean inBean, ActionOutBean outBean) {
    if (index.get() >= actions.size()) {
      return;
    }

    actions.get(index.getAndIncrement()).doAction(inBean, outBean, this);
  }

  public static void main(String[] args) {
    ActionChainContext chainContext = new ActionChainContext();
    chainContext.doAction(new ActionInBean(), new ActionOutBean());
  }
}
