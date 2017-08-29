package com.so.feign;

import java.util.concurrent.CompletableFuture;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.so.model.ItemRequest;

@FeignClient("ITEM-COMMAND")
public interface ItemCommandFeignClient {

	@RequestMapping(value = "/items", method = RequestMethod.POST)
	CompletableFuture<ResponseEntity> createItem(@RequestBody ItemRequest item);

}
