package com.so.workflow;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import com.so.events.bid.CreateBidEvent;
import com.so.service.BidViewService;
import com.so.view.BidView;
import org.springframework.stereotype.Component;

/**
 * Created by sergiu.oltean on 5/24/2017.
 */
@EventSubscriber(id = "bidViewWorkflow")
@Component
public class BidViewWorkflow {

	private BidViewService bidViewService;

	@Autowired
	public BidViewWorkflow(BidViewService bidViewService) {
		this.bidViewService = bidViewService;
	}

	@EventHandlerMethod
	public void createBid(DispatchedEvent<CreateBidEvent> event) {
		BidView itemView = new BidView(event.getEvent().getItemCode(), event.getEvent().getAmount());
		bidViewService.addBid(itemView);
	}

}
