package com.so.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.so.repository.BidViewRepository;
import com.so.view.BidView;

/**
 * Created by sergiu.oltean on 5/12/2017.
 */
@Service
public class BidViewService {

	@Autowired
	private BidViewRepository bidViewRepository;

	public BidView findWinningBid(String itemCode) {
		return new BidView(itemCode, 0);
	}

}
