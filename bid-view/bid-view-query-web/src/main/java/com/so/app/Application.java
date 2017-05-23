package com.so.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Import;
import com.so.config.BidViewWebConfiguration;

/**
 * Created by sergiu.oltean on 5/16/2017.
 */
@SpringBootApplication
@Import(BidViewWebConfiguration.class)
@EnableEurekaClient
@FeignClient("http://bid-service-query")
public class Application {

}
