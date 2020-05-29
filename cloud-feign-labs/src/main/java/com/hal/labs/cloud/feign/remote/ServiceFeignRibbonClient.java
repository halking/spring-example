package com.hal.labs.cloud.feign.remote;

import com.hal.labs.cloud.feign.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Steven HUANG
 * @Date: 2020/5/29
 */
@FeignClient(value = "cloud-client-service", configuration = FeignConfiguration.class,
    path = "/client-service/api/v1/client")
public interface ServiceFeignRibbonClient {

  @GetMapping
  String getInfo(@RequestParam("name") String name, @RequestHeader("token") String token);

  @PostMapping
  String saveInfo(@RequestBody String body);
}
