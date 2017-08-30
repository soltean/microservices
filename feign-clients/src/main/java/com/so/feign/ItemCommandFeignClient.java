package com.so.feign;

import com.so.model.ItemRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.ExecutionException;

@FeignClient(value = "ITEM-COMMAND")
public interface ItemCommandFeignClient {

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    ResponseEntity<String> createItem(@RequestBody ItemRequest item) throws ExecutionException, InterruptedException;

}
