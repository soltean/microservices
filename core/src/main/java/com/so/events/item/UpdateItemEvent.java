package com.so.events.item;

import com.so.model.ItemState;

/**
 * Created by sergiu.oltean on 6/6/2017.
 */
public class UpdateItemEvent extends ItemEvent {

	private final String itemCode;
	private final ItemState itemState;

	public UpdateItemEvent(String itemCode, ItemState itemState) {
		this.itemCode = itemCode;
		this.itemState = itemState;
	}

	public String getItemCode() {
		return itemCode;
	}

	public ItemState getItemState() {
		return itemState;
	}
}
