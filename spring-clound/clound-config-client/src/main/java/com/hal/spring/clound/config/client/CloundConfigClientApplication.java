package com.hal.spring.clound.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CloundConfigClientApplication {

  @Value("${foo}")
  private String foo;

  public static void main(String[] args) {
    SpringApplication.run(CloundConfigClientApplication.class, args);
  }

  @RequestMapping(value = "/foo")
  public String getFoo() {
    return foo;
  }
}
