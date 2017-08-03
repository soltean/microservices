package com.so.config;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
@Configuration
@ComponentScan("com.so")
@Import({ItemConfiguration.class, EventuateDriverConfiguration.class})
public class ItemWebConfiguration {

}
