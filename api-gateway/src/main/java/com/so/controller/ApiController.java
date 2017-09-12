package com.so.controller;

import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.so.model.BidRequest;
import com.so.model.ItemRequest;
import com.so.service.ApiService;

@RestController
public class ApiController {

	@Autowired
	private ApiService apiService;

	@RequestMapping(value = "/items", method = RequestMethod.POST)
	private ResponseEntity<String> createItem(@RequestBody ItemRequest item) throws ExecutionException, InterruptedException, JsonProcessingException {
		return apiService.createItem(item);
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	private ResponseEntity<String> findAllItems() throws JsonProcessingException {
		return apiService.findAllItems();
	}

	@RequestMapping(value = "/bids", method = RequestMethod.POST)
	private ResponseEntity<String> createBid(@RequestBody BidRequest bid) throws ExecutionException, InterruptedException, JsonProcessingException {
		return apiService.createBid(bid);
	}

	@RequestMapping(value = "/bids/{itemCode}", method = RequestMethod.GET)
	private ResponseEntity<String> getBidsForItem(@PathVariable String itemCode) throws JsonProcessingException {
		return apiService.getBidsForItem(itemCode);
	}

	@RequestMapping(value = "/bids/winning/{itemCode}", method = RequestMethod.GET)
	private ResponseEntity<String> getWinningBid(@PathVariable String itemCode) throws JsonProcessingException {
		return apiService.getWinningBid(itemCode);
	}
}
