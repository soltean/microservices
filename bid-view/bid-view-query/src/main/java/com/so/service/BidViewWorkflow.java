package com.so.service;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import com.so.events.bid.CreateBidEvent;
import com.so.view.BidView;

/**
 * Created by sergiu.oltean on 6/6/2017.
 */
@EventSubscriber(id = "bidWorkflow")
public class BidViewWorkflow {

	private BidViewService bidViewService;

	@Autowired
	public BidViewWorkflow(BidViewService bidViewService) {
		this.bidViewService = bidViewService;
	}

	@EventHandlerMethod
	public void createItem(DispatchedEvent<CreateBidEvent> event) {
		BidView bidView = new BidView(event.getEvent().getItemCode(), event.getEvent().getAmount());
		bidViewService.addBid(bidView);
	}

}
