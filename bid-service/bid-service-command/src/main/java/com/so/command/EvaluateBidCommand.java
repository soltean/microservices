package com.so.command;

/**
 * Created by sergiu.oltean on 5/10/2017.
 */
public class EvaluateBidCommand implements BidCommand {

	private final String itemCode;

	private final int highestAmount;

	public EvaluateBidCommand(String itemCode, int highestAmount) {
		this.itemCode = itemCode;
		this.highestAmount = highestAmount;
	}

	public String getItemCode() {
		return itemCode;
	}

	public int getHighestAmount() {
		return highestAmount;
	}
}
