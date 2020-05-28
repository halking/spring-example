package com.hal.labs.cloud.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudConfigClientLabsApplication {

  public static void main(String[] args) {
    SpringApplication.run(CloudConfigClientLabsApplication.class, args);
  }

}
