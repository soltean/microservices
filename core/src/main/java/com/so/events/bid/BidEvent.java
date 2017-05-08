package com.so.events.bid;

import io.eventuate.Event;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
public class BidEvent implements Event {

	private String itemCode;

	public BidEvent(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemCode() {
		return itemCode;
	}

}
