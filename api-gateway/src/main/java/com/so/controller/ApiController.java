package com.so.controller;

import com.so.feign.BidCommandFeignClient;
import com.so.feign.BidViewFeignClient;
import com.so.feign.ItemCommandFeignClient;
import com.so.feign.ItemViewFeignClient;
import com.so.model.BidRequest;
import com.so.model.ItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class ApiController {

	@Autowired
	private ItemCommandFeignClient itemCommandFeignClient;

	@Autowired
	private ItemViewFeignClient itemViewFeignClient;

	@Autowired
	private BidCommandFeignClient bidCommandFeignClient;

	@Autowired
	private BidViewFeignClient bidViewFeignClient;

	@RequestMapping(value = "/items", method = RequestMethod.POST)
	private ResponseEntity<String> createItem(@RequestBody ItemRequest item) throws ExecutionException, InterruptedException {
		return itemCommandFeignClient.createItem(item);
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	private ResponseEntity<List<?>> findAllItems() {
		return itemViewFeignClient.findAllItems();
	}

	@RequestMapping(value = "/bids", method = RequestMethod.POST)
	private CompletableFuture<ResponseEntity> createBid(@RequestBody BidRequest bid) {
		return bidCommandFeignClient.createBid(bid);
	}

	@RequestMapping(value = "/bids/{itemCode}", method = RequestMethod.GET)
	private ResponseEntity<List<?>> getBidsForItem(@PathVariable String itemCode) {
		return bidViewFeignClient.getBidsForItem(itemCode);
	}

	@RequestMapping(value = "/bids/winning/{itemCode}", method = RequestMethod.GET)
	private ResponseEntity<?> getWinningBid(@PathVariable String itemCode) {
		return bidViewFeignClient.getWinningBid(itemCode);
	}

}
