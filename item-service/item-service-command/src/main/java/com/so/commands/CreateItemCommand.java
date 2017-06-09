package com.so.commands;

/**
 * Created by sergiu.oltean on 5/9/2017.
 */
public class CreateItemCommand extends ItemCommand {

	private int reservePrice;

	public CreateItemCommand(String itemCode, int reservePrice) {
		super(itemCode);
		this.reservePrice = reservePrice;
	}

	public int getReservePrice() {
		return reservePrice;
	}
}
