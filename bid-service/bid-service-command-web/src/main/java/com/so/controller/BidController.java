package com.so.controller;

import com.so.aggregate.BidAggregate;
import com.so.feign.BidCommandFeignClient;
import com.so.mediatypes.BidServiceMediaType;
import com.so.model.BidRequest;
import com.so.resources.CreateBidResourceV1;
import com.so.resources.CreateBidResourceV2;
import com.so.service.BidService;
import io.eventuate.EntityWithIdAndVersion;
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

    @RequestMapping(value = "/bidsHateoas", headers = HttpHeaders.ACCEPT, method = RequestMethod.POST)
    public ResponseEntity createBidWithHateoas(@RequestHeader(HttpHeaders.ACCEPT) String accept, @RequestBody BidRequest bid) throws ExecutionException, InterruptedException {
        logger.info("Creating bid " + bid);
        String version = accept.split("version=")[1];

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, BidServiceMediaType.BID_SERVICE_BID_VALUE + ";version=" + version);
        headers.add(HttpHeaders.VARY, "Content-Type");
        CompletableFuture<EntityWithIdAndVersion<BidAggregate>> future = bidService.addBid(bid.getItemCode(), bid.getAmount());
        EntityWithIdAndVersion result = future.get();
        if (version.contains("1.9.0")) {
            CreateBidResourceV1 resource = new CreateBidResourceV1(result.getEntityId());
            resource.add(linkTo(methodOn(BidController.class).createBidWithHateoas(accept, bid)).withRel("bidsHateoas"));
            return new ResponseEntity(resource, headers, HttpStatus.OK);
        }
        if (version.contains("2.1.0")) {
            CreateBidResourceV2 resource = new CreateBidResourceV2(result.getEntityId(), "I am Groot!");
            resource.add(linkTo(methodOn(BidController.class).createBidWithHateoas(accept, bid)).withRel("bidsHateoas"));
            return new ResponseEntity(resource, headers, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
