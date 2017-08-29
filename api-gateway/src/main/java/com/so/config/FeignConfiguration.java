package com.so.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = { "com.so.feign" })
@ComponentScan("com.so")
public class FeignConfiguration {

}
