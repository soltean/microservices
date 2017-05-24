package com.so.config;

import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
@Configuration
@EnableEventHandlers
@ComponentScan
@Import(BidViewMongoConfiguration.class)
public class BidViewConfiguration {

}
