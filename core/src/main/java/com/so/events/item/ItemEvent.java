package com.so.events.item;

import io.eventuate.Event;

/**
 * Created by sergiu.oltean on 5/9/2017.
 */
public class ItemEvent implements Event {

	private final String itemCode;
	private final int reservePrice;

	public ItemEvent(String itemCode, int reservePrice) {
		this.itemCode = itemCode;
		this.reservePrice = reservePrice;
	}

	public String getItemCode() {
		return itemCode;
	}

	public int getReservePrice() {
		return reservePrice;
	}
}
