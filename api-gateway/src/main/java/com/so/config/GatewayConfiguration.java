package com.so.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient
@ComponentScan("com.so")
public class GatewayConfiguration {
}
