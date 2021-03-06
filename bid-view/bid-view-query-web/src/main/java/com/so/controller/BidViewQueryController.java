package com.so.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.so.service.BidViewService;
import com.so.view.BidView;

/**
 * Created by sergiu.oltean on 5/23/2017.
 */
@RestController
public class BidViewQueryController {

	@Autowired
	private BidViewService bidViewService;

	@RequestMapping(value = "/bids/{itemCode}", method = RequestMethod.GET)
	public ResponseEntity<List<BidView>> getBidsForItem(@PathVariable String itemCode) {
		List<BidView> bids = bidViewService.getBidsForItem(itemCode);
		return new ResponseEntity<>(bids, HttpStatus.OK);
	}

	@RequestMapping(value = "/bids/winning/{itemCode}", method = RequestMethod.GET)
	public ResponseEntity<BidView> getWinningBid(@PathVariable String itemCode) {
		BidView bid = bidViewService.findWinningBid(itemCode);
		return new ResponseEntity<>(bid, HttpStatus.OK);
	}

}
