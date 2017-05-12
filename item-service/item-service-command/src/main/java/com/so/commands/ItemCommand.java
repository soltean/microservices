package com.so.commands;

import io.eventuate.Command;
import com.so.model.ItemState;

/**
 * Created by sergiu.oltean on 5/5/2017.
 */
public class ItemCommand implements Command {

	private final String itemCode;
	private int reservePrice;
	private ItemState itemState;

	public ItemCommand(String itemCode, int reservePrice) {
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
