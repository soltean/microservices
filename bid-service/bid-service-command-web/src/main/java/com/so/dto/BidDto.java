package com.so.dto;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
public class BidDto {

	private String itemCode;
	private int amount;

	public BidDto(String itemCode, int amount) {
		this.itemCode = itemCode;
		this.amount = amount;
	}

	public String getItemCode() {
		return itemCode;
	}

	public int getAmount() {
		return amount;
	}
}
