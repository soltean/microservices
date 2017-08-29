package com.so.controller;

import com.so.feign.BidCommandFeignClient;
import com.so.model.BidRequest;
import com.so.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

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

	@RequestMapping(value = "/bids", method = RequestMethod.POST)
	public CompletableFuture<ResponseEntity> createBid(@RequestBody BidRequest bid) {
		return bidService.addBid(bid.getItemCode(), bid.getAmount()).thenApply(b -> new ResponseEntity(b.getEntityId(), HttpStatus.OK));
	}
}
