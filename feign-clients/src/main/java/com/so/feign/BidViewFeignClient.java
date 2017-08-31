package com.so.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "BID-VIEW")
public interface BidViewFeignClient {

    @RequestMapping(value = "/bids/{itemCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> getBidsForItem(@PathVariable(value = "itemCode") String itemCode) throws JsonProcessingException;

    @RequestMapping(value = "/bids/winning/{itemCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> getWinningBid(@PathVariable(value = "itemCode") String itemCode) throws JsonProcessingException;

}
