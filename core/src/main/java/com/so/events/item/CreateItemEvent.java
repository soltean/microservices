package com.so.events.item;

/**
 * Created by sergiu.oltean on 5/9/2017.
 */
public class CreateItemEvent extends ItemEvent {

	public CreateItemEvent(String itemCode, int reservePrice) {
		super(itemCode, reservePrice);
	}
}
