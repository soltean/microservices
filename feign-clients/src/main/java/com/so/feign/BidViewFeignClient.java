package com.so.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "BID-VIEW", configuration = FeignConfiguration.class)
public interface BidViewFeignClient {

	@RequestMapping(value = "/bids/{itemCode}", method = RequestMethod.GET)
	ResponseEntity getBidsForItem(@PathVariable(value = "itemCode") String itemCode);

	@RequestMapping(value = "/bids/winning/{itemCode}", method = RequestMethod.GET)
	ResponseEntity getWinningBid(@PathVariable(value = "itemCode") String itemCode);

}
