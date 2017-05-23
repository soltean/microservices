package so.app;

import so.config.ItemViewWebConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Import;

/**
 * Created by sergiu.oltean on 5/16/2017.
 */
@SpringBootApplication
@Import(ItemViewWebConfiguration.class)
@EnableEurekaClient
@FeignClient("http://item-service-query")
public class Application {

}
