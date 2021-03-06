package com.so.config;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by sergiu.oltean on 5/23/2017.
 */
@Configuration
@ComponentScan("com.so")
@Import({ BidViewConfiguration.class, EventuateDriverConfiguration.class })
public class BidViewWebConfiguration {

}
