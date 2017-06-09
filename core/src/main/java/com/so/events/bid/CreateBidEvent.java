package com.so.events.bid;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
public class CreateBidEvent extends BidEvent {

	private int amount;

	public CreateBidEvent(String itemCode, int amount) {
		super(itemCode);
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}
}
