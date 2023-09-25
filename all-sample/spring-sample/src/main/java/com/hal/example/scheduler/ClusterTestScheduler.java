package com.hal.example.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Steven.HUANG on 2019/1/17.
 */

@Slf4j
@Component
@Configuration
@EnableScheduling
@ConditionalOnProperty("scheduler.reminder-delete.enabled")
@DisallowConcurrentExecution
public class ClusterTestScheduler implements BaseScheduler {

  @Autowired
  private ServerProperties serverProperties;

  @Scheduled(cron = "0/5 * * * * *")
  @Override
  public void start() {
    log.info("start cluster test scheduler..." + serverProperties.getPort());
  }

}
