package com.so.model;

public final class Bid {

	private Item item;
	private int amount;

	public Bid(Item item, int amount) {
		this.item = item;
		this.amount = amount;
	}

	public Item getItem() {
		return item;
	}

	public int getAmount() {
		return amount;
	}
}