package com.hal.labs.cloud.config.client.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Steven HUANG
 * @Date: 2020/5/26
 */
@Data
@Component
@ConfigurationProperties(prefix = "labs")
public class ConfigProperties {

  private String env;

  private ConfigData data;

  @Data
  public static class ConfigData {

    private String name;

    private String address;
  }
}
