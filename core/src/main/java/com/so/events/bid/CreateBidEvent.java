package com.so.events.bid;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
public class CreateBidEvent extends BidEvent {

	private String itemCode;
	private int amount;

	public CreateBidEvent(String itemCode, int amount) {
		super(itemCode);
		this.amount = amount;
	}

	public String getItemCode() {
		return itemCode;
	}

	public int getAmount() {
		return amount;
	}
}
