package com.so.controller;

import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.so.feign.BidCommandFeignClient;
import com.so.feign.BidViewFeignClient;
import com.so.feign.ItemCommandFeignClient;
import com.so.feign.ItemViewFeignClient;
import com.so.model.BidRequest;
import com.so.model.ItemRequest;
import com.so.service.CacheService;

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

	@Autowired
	private CacheService cacheService;

	@RequestMapping(value = "/items", method = RequestMethod.POST)
	private ResponseEntity<String> createItem(@RequestBody ItemRequest item) throws ExecutionException, InterruptedException, JsonProcessingException {
		cacheService.saveItemToCache(item.getItemCode(), item);
		return itemCommandFeignClient.createItem(item);
	}

	@HystrixCommand(fallbackMethod = "getLatestItemsFromCache")
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	private ResponseEntity<String> findAllItems() throws JsonProcessingException {
		return itemViewFeignClient.findAllItems();
	}

	private ResponseEntity<String> getLatestFromCache() throws JsonProcessingException {
		return new ResponseEntity<>("[]", HttpStatus.OK);
	}

	@RequestMapping(value = "/bids", method = RequestMethod.POST)
	private ResponseEntity<String> createBid(@RequestBody BidRequest bid) throws ExecutionException, InterruptedException, JsonProcessingException {
		cacheService.saveBidToCache(bid.getItemCode(), bid);
		return bidCommandFeignClient.createBid(bid);
	}

	@HystrixCommand(fallbackMethod = "getLatestBidsFromCache")
	@RequestMapping(value = "/bids/{itemCode}", method = RequestMethod.GET)
	private ResponseEntity<String> getBidsForItem(@PathVariable String itemCode) throws JsonProcessingException {
		return bidViewFeignClient.getBidsForItem(itemCode);
	}

	private ResponseEntity<String> getLatestBidsFromCache(String itemCode) throws JsonProcessingException {
		return new ResponseEntity<>("[]", HttpStatus.OK);
	}

	@HystrixCommand(fallbackMethod = "cannotDetermineNow")
	@RequestMapping(value = "/bids/winning/{itemCode}", method = RequestMethod.GET)
	private ResponseEntity<String> getWinningBid(@PathVariable String itemCode) throws JsonProcessingException {
		return bidViewFeignClient.getWinningBid(itemCode);
	}

	private ResponseEntity<String> cannotDetermineNow(String itemCode) throws JsonProcessingException {
		return new ResponseEntity<>("Cannot determine at this moment winning bid for item " + itemCode, HttpStatus.OK);
	}

}
