package com.hal.labs.cloud.config.client.controller;

import com.hal.labs.cloud.config.client.properties.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Steven HUANG
 * @Date: 2020/5/26
 */
@RestController
@RequestMapping("/api/v1")
public class ConfigController {

  @Autowired
  private ConfigProperties configProperties;

  @GetMapping("/config")
  public ResponseEntity getConfig(){
    return ResponseEntity.ok(configProperties);
  }
}
