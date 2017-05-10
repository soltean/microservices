package com.so.model;

import java.util.UUID;

public final class Bid {

	private String bidCode;
	private String itemCode;
	private int amount;
	private BidState state;

	public Bid(String itemCode, int amount) {
		this.bidCode = UUID.randomUUID().toString();
		this.itemCode = itemCode;
		this.amount = amount;
		this.state = BidState.NEW;
	}

	public String getItemCode() {
		return itemCode;
	}

	public int getAmount() {
		return amount;
	}

	public BidState getState() {
		return state;
	}
}