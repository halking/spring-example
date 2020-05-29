package com.hal.labs.cloud.feign.controller;

import com.hal.labs.cloud.feign.remote.ServiceFeignClient;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Steven HUANG
 * @Date: 2020/5/29
 */
@RestController
@RequestMapping("/api/v1/feign")
public class ClientController {

  @Autowired
  private ServiceFeignClient serviceFeignClient;

  @GetMapping
  public ResponseEntity getInfo(@RequestParam("name") String name, @RequestHeader("token") String token){
    return ResponseEntity.ok(serviceFeignClient.getInfo(name, token));
  }
}
