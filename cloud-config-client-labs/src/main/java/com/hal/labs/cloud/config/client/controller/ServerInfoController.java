package com.hal.labs.cloud.config.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Steven HUANG
 * @Date: 2020/5/28
 */
@RestController
@RequestMapping("/api/v1/server")
public class ServerInfoController {

  @Autowired
  private DiscoveryClient discoveryClient;

  @GetMapping("/info")
  public ResponseEntity getServerInfo(){
    return ResponseEntity.ok(discoveryClient.getServices());
  }

}
