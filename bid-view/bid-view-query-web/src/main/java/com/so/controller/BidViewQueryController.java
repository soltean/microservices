package com.so.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.so.feign.BidViewFeignClient;
import com.so.service.BidViewService;
import com.so.view.BidView;

/**
 * Created by sergiu.oltean on 5/23/2017.
 */
@RestController
public class BidViewQueryController implements BidViewFeignClient {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BidViewService bidViewService;

	private ObjectMapper objectMapper = new ObjectMapper();

	public ResponseEntity<String> getBidsForItem(@PathVariable String itemCode) throws JsonProcessingException {
		logger.info("Getting bids for item " + itemCode);
		List<BidView> bids = bidViewService.getBidsForItem(itemCode);
		String bidsAsJson = objectMapper.writeValueAsString(bids);
		return new ResponseEntity<>(bidsAsJson, HttpStatus.OK);
	}

	public ResponseEntity<String> getWinningBid(@PathVariable String itemCode) throws JsonProcessingException {
		logger.info("Calculate winning bid for item " + itemCode);
		BidView bid = bidViewService.findWinningBid(itemCode);
		String bidAsJson = objectMapper.writeValueAsString(bid);
		return new ResponseEntity<>(bidAsJson, HttpStatus.OK);
	}

}
