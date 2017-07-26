package com.so.config;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by sergiu.oltean on 5/23/2017.
 */
@Configuration
@ComponentScan("com.so")
@Import({ ItemViewConfiguration.class, EventuateDriverConfiguration.class })
public class ItemViewWebConfiguration {

}
