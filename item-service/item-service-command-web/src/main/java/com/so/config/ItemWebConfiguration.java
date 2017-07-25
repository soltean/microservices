package com.so.config;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.so.config.ItemConfiguration;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
@Configuration
@ComponentScan("com.so")
@Import({ ItemConfiguration.class, EventuateDriverConfiguration.class })
public class ItemWebConfiguration {

}
