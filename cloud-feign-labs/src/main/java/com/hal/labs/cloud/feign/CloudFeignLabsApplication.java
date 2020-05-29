package com.hal.labs.cloud.feign;

import com.hal.labs.cloud.feign.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@RibbonClient(name = "feign-labs-ribbon", configuration = RibbonConfiguration.class)
public class CloudFeignLabsApplication {

  public static void main(String[] args) {
    SpringApplication.run(CloudFeignLabsApplication.class, args);
  }

}
