package com.so.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
@Configuration
@ComponentScan
@Import(BidConfiguration.class)
public class BidWebConfiguration {

}
