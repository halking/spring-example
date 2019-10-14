package com.hal.example;

import com.hal.example.service.SampleService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) throws Exception {
    ReferenceConfig<SampleService> referenceConfig = new ReferenceConfig();

    referenceConfig.setApplication(new ApplicationConfig("api-consumer"));

    RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");
    registryConfig.setGroup("api-sample");
    referenceConfig.setRegistry(registryConfig);

    referenceConfig.setInterface(SampleService.class);

    SampleService sampleService = referenceConfig.get();

    System.out.println(sampleService.doTest("api-sample"));
  }
}
