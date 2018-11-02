package com.hal.spring.clound.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CloundConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloundConfigServerApplication.class, args);
	}
}
