package com.hal.example.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/15
 */
@Component
@ConfigurationProperties(prefix = "brand.server")
@Data
public class ServerProperties {

  private Map<String, ServerInfo> details = new LinkedHashMap<>();

  @Data
  public static class ServerInfo {

    private String protocol;

    private String host;

    private Integer port;

    private String username;

    private String password;

    private Integer connectionTimeout;

    private Integer sessionTimeout;

    private String strictHostKeyChecking;

    private String rootPath;

    private String privateKey;

    private String passphrase;
  }
}
