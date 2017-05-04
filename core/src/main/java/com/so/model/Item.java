package com.so.model;

public final class Item {

	private int reservePrice;
	private String code;

	public Item(String code, int reservePrice) {
		this.code = code;
		this.reservePrice = reservePrice;
	}

	public int getReservePrice() {
		return reservePrice;
	}

	public String getCode() {
		return code;
	}

}