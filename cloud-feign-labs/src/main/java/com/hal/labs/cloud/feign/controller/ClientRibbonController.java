package com.hal.labs.cloud.feign.controller;

import com.hal.labs.cloud.feign.remote.ServiceFeignClient;
import com.hal.labs.cloud.feign.remote.ServiceFeignRibbonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Steven HUANG
 * @Date: 2020/5/29
 */
@RestController
@RequestMapping(value = "/api/v1/feign-ribbon", produces = "application/json;charset=UTF-8")
public class ClientRibbonController {

  @Autowired
  private ServiceFeignRibbonClient serviceFeignRibbonClient;

  @GetMapping
  public ResponseEntity getInfo(@RequestParam("name") String name, @RequestHeader("token") String token){
    return ResponseEntity.ok(serviceFeignRibbonClient.getInfo(name, token));
  }

  @PostMapping
  public ResponseEntity saveInfo(@RequestBody String body){
    return ResponseEntity.ok(serviceFeignRibbonClient.saveInfo(body));
  }
}
