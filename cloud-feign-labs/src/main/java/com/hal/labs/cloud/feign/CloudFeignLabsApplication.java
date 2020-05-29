package com.hal.labs.cloud.feign;

import com.hal.labs.cloud.feign.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableRetry
@RibbonClient(name = "cloud-client-service", configuration = RibbonConfiguration.class)
public class CloudFeignLabsApplication {

  public static void main(String[] args) {
    SpringApplication.run(CloudFeignLabsApplication.class, args);
  }

}
