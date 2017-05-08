package com.so.command;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
public class CreateBidCommand implements BidCommand {

	private String itemCode;
	private int amount;

	public CreateBidCommand(String itemCode, int amount) {
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
