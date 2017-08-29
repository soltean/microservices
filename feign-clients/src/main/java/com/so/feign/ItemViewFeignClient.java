package com.so.feign;

import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ITEM-VIEW")
public interface ItemViewFeignClient<T> {

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	ResponseEntity<List<T>> findAllItems();
}
