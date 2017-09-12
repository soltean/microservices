package com.so.service;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.so.feign.BidCommandFeignClient;
import com.so.feign.BidViewFeignClient;
import com.so.feign.ItemCommandFeignClient;
import com.so.feign.ItemViewFeignClient;
import com.so.model.BidRequest;
import com.so.model.ItemRequest;

@Service
public class ApiService {

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

	private ObjectMapper objectMapper = new ObjectMapper();

	public ResponseEntity<String> createItem(ItemRequest item) throws ExecutionException, InterruptedException, JsonProcessingException {
		ResponseEntity<String> responseEntity = itemCommandFeignClient.createItem(item);
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			cacheService.saveItemToCache(item.getItemCode(), item);
		}
		return responseEntity;
	}

	@HystrixCommand(fallbackMethod = "getLatestItemsFromCache", commandKey = "getItems", commandProperties = {
			@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "5000") })
	public ResponseEntity<String> findAllItems() throws JsonProcessingException {
		return itemViewFeignClient.findAllItems();
	}

	private ResponseEntity<String> getLatestItemsFromCache() throws JsonProcessingException {
		Collection<ItemRequest> latest = cacheService.getItemsFromCache();
		return new ResponseEntity<>(objectMapper.writeValueAsString(latest), HttpStatus.OK);
	}

	public ResponseEntity<String> createBid(BidRequest bid) throws ExecutionException, InterruptedException, JsonProcessingException {
		ResponseEntity<String> responseEntity = bidCommandFeignClient.createBid(bid);
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			cacheService.saveBidToCache(bid.getItemCode(), bid);
		}
		return responseEntity;
	}

	@HystrixCommand(fallbackMethod = "getLatestBidsFromCache", commandKey = "getBids", commandProperties = {
			@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "5000") })
	public ResponseEntity<String> getBidsForItem(String itemCode) throws JsonProcessingException {
		return bidViewFeignClient.getBidsForItem(itemCode);
	}

	private ResponseEntity<String> getLatestBidsFromCache(String itemCode) throws JsonProcessingException {
		Collection<BidRequest> latestBidsForItem = cacheService.getBidsFromCache(itemCode);
		return new ResponseEntity<>(objectMapper.writeValueAsString(latestBidsForItem), HttpStatus.OK);
	}

	@HystrixCommand(fallbackMethod = "cannotDetermineNow", commandKey = "getWinningBid", commandProperties = {
			@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "5000") })
	public ResponseEntity<String> getWinningBid(String itemCode) throws JsonProcessingException {
		return bidViewFeignClient.getWinningBid(itemCode);
	}

	private ResponseEntity<String> cannotDetermineNow(String itemCode) throws JsonProcessingException {
		return new ResponseEntity<>("Cannot determine at this moment winning bid for item " + itemCode, HttpStatus.OK);
	}

}
