package com.so.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "ITEM-VIEW", configuration = FeignConfiguration.class)
public interface ItemViewFeignClient {

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    ResponseEntity findAllItems();
}
