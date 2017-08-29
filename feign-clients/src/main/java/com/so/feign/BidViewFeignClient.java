package com.so.feign;

import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("BID-VIEW")
public interface BidViewFeignClient<T> {

	@RequestMapping(value = "/bids/{itemCode}", method = RequestMethod.GET)
	ResponseEntity<List<T>> getBidsForItem(@PathVariable String itemCode);

	@RequestMapping(value = "/bids/winning/{itemCode}", method = RequestMethod.GET)
	ResponseEntity<T> getWinningBid(@PathVariable String itemCode);

}
