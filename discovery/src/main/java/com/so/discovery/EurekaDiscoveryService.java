package com.so.discovery;

/**
 * Created by sergiu.oltean on 5/16/2017.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaDiscoveryService {

	public static void main(String[] args) {
		SpringApplication.run(EurekaDiscoveryService.class, args);
	}
}