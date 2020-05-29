package com.hal.labs.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CloudFeignLabsApplication {

  public static void main(String[] args) {
    SpringApplication.run(CloudFeignLabsApplication.class, args);
  }

}
