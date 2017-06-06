package com.so.events.item;

/**
 * Created by sergiu.oltean on 5/9/2017.
 */
public class CreateItemEvent extends ItemEvent {

	private final String itemCode;
	private final int reservePrice;

	public CreateItemEvent(String itemCode, int reservePrice) {
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
