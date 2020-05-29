package com.hal.labs.cloud.feign.remote;

import org.springframework.stereotype.Component;

/**
 * @Author: Steven HUANG
 * @Date: 2020/5/29
 */
@Component
public class ServiceFeignFallback implements ServiceFeignClient {

  @Override
  public String getInfo(String name, String token) {
    return "this is null";
  }

  @Override
  public String saveInfo(String body) {
    return "body is null";
  }
}
