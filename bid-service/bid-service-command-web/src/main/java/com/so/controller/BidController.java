package com.so.controller;

import com.so.feign.BidCommandFeignClient;
import com.so.model.BidRequest;
import com.so.service.BidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
@RestController
public class BidController implements BidCommandFeignClient {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    public ResponseEntity<String> createBid(@RequestBody BidRequest bid) throws ExecutionException, InterruptedException {
        logger.info("Creating bid " + bid);
        CompletableFuture<ResponseEntity> future = bidService.addBid(bid.getItemCode(), bid.getAmount())
                .thenApply(b -> new ResponseEntity(b.getEntityId(), HttpStatus.OK));
        return future.get();
    }

    @RequestMapping(value = "/bidsHateoas", method = RequestMethod.POST)
    public ResponseEntity<CreateBidResource> createBidWithHateoas(@RequestHeader(HttpHeaders.ACCEPT) String accept, @RequestBody BidRequest bid) throws ExecutionException, InterruptedException {
        logger.info("Creating bid " + bid);
        CreateBidResource resource = new CreateBidResource();
        String version = accept.split("version=")[1];
        if (version.contains("v1")) {

        }

        if (version.contains("v2")) {

        }

        resource.add(linkTo(methodOn(BidController.class).createBidWithHateoas(accept, bid)).withRel("bidsHateoas"));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "must match the accept type");
        headers.add(HttpHeaders.VARY, "Content-Type");
        CompletableFuture<ResponseEntity> future = bidService.addBid(bid.getItemCode(), bid.getAmount())
                .thenApply(b -> {
                    resource.setItemCode(b.getEntityId());
                    return new ResponseEntity(resource, headers, HttpStatus.OK);
                });
        return future.get();
    }
}
