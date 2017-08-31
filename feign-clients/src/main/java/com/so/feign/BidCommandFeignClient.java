package com.so.feign;

import com.so.model.BidRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.ExecutionException;

@FeignClient(value = "BID-COMMAND")
public interface BidCommandFeignClient {

    @RequestMapping(value = "/bids", method = RequestMethod.POST)
    ResponseEntity<String> createBid(@RequestBody BidRequest bid) throws ExecutionException, InterruptedException;

}
