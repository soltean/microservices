package com.so.feign;

import com.so.model.BidRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CompletableFuture;

@FeignClient(value = "BID-COMMAND", configuration = FeignConfiguration.class)
public interface BidCommandFeignClient {

    @RequestMapping(value = "/bids", method = RequestMethod.POST)
    @ResponseBody
    CompletableFuture<ResponseEntity> createBid(@RequestBody BidRequest bid);

}
