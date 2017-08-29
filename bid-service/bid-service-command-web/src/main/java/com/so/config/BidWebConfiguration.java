package com.so.config;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
@Configuration
@ComponentScan("com.so")
@EnableEurekaClient
@Import({BidConfiguration.class, EventuateDriverConfiguration.class})
public class BidWebConfiguration {

}
