package com.hal.labs.cloud.clientservice.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
@Slf4j
@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

  @Autowired
  private Environment environment;

  @GetMapping
  public ResponseEntity getInfo(@RequestParam("name") String name, @RequestHeader("token") String token){
    Map<String, String> map = new HashMap<>();
    map.put("name", name);
    map.put("token", token);


    String serverPort = environment.getProperty("local.server.port");
    System.out.println("Port : " + serverPort);

    return ResponseEntity.ok(map);
  }
}
