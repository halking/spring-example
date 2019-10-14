package com.hal.example.provider;

import com.hal.example.service.SampleService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

/**
 * @Author: Steven HUANG
 * @Date: 2019/9/12
 */
public class App {

  public static void main(String[] args) throws Exception {
    ServiceConfig<SampleServiceImpl> serviceConfig = new ServiceConfig<>();

    serviceConfig.setApplication(new ApplicationConfig("sample-provide"));

    RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");
    registryConfig.setGroup("api-sample");
    serviceConfig.setRegistry(registryConfig);

    serviceConfig.setInterface(SampleService.class);
    serviceConfig.setRef(new SampleServiceImpl());
    serviceConfig.export();

    System.in.read();
  }
}
