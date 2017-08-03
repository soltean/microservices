package com.so.events.bid;

import io.eventuate.Event;
import io.eventuate.EventEntity;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
@EventEntity(entity = "com.so.aggregate.BidAggregate")
public class BidEvent implements Event {

	private String itemCode;

	public BidEvent(String itemCode) {
		this.itemCode = itemCode;
	}

	public BidEvent() {
	}

	public String getItemCode() {
		return itemCode;
	}

}
