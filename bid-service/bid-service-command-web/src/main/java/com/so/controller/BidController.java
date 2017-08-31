package com.so.controller;

import com.so.feign.BidCommandFeignClient;
import com.so.model.BidRequest;
import com.so.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
@RestController
public class BidController implements BidCommandFeignClient {

    private BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    public ResponseEntity<String> createBid(@RequestBody BidRequest bid) throws ExecutionException, InterruptedException {
        CompletableFuture<ResponseEntity> future = bidService.addBid(bid.getItemCode(), bid.getAmount()).thenApply(b -> new ResponseEntity(b.getEntityId(), HttpStatus.OK));
        return future.get();

    }
}
