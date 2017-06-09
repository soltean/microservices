package com.so.commands;

import io.eventuate.Command;

/**
 * Created by sergiu.oltean on 5/5/2017.
 */
public class ItemCommand implements Command {

	private final String itemCode;

	public ItemCommand(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemCode() {
		return itemCode;
	}

}
