package com.hal.labs;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hal.labs"})
public class WebfluxLabsApplication {

  @Value("${server.timeZone:#{T(java.time.ZoneId).systemDefault().getId()}}")
  private String timeZone;

  public static void main(String[] args) {
    SpringApplication.run(WebfluxLabsApplication.class, args);
  }

  @PostConstruct
  void setDefaultTimeZone() {
    TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
  }
}
