package com.so.app;

import com.so.config.BidWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Import;

/**
 * Created by sergiu.oltean on 5/16/2017.
 */
@SpringBootApplication
@Import(BidWebConfiguration.class)
@EnableEurekaClient
@FeignClient("http://bid-service-command")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
