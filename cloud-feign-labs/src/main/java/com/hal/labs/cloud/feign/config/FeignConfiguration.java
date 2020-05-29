package com.hal.labs.cloud.feign.config;

import com.hal.labs.cloud.feign.custom.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Steven HUANG
 * @Date: 2020/5/29
 */
@Configuration
public class FeignConfiguration {

  /**
   * 定制化适合项目的Http Client组件
   * @return
   */
  @Bean
  public OkHttpClient client() {
    return new OkHttpClient();
  }

  /**
   * 添加请求拦截器
   * @return
   */
/*  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
      requestTemplate.header("user", username);
      requestTemplate.header("password", password);
      requestTemplate.header("Accept", ContentType.APPLICATION_JSON.getMimeType());
    };
  }*/

  /**
   * 自定义error decoder
   * @return
   */
  @Bean
  public ErrorDecoder errorDecoder() {
    return new CustomErrorDecoder();
  }

}
