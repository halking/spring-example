package com.hal.spring.cloundserviceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CloundServiceServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CloundServiceServerApplication.class, args);
  }
}
