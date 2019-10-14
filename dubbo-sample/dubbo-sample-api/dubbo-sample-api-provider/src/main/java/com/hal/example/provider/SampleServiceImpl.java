package com.hal.example.provider;

import com.hal.example.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @Author: Steven HUANG
 * @Date: 2019/9/12
 */
@Slf4j
public class SampleServiceImpl implements SampleService {

  @Override
  public String doTest(String content) {
    log.info("Hello " + content + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
    return "Hello " + content + ", response from provider: " + RpcContext.getContext().getLocalAddress();
  }
}
