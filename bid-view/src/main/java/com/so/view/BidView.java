package com.so.view;

import java.util.UUID;
import com.so.model.BidState;

/**
 * Created by sergiu.oltean on 5/12/2017.
 */
public class BidView {

	private String bidCode;
	private String itemCode;
	private int amount;
	private BidState state;

	public BidView(String itemCode, int amount) {
		this.bidCode = UUID.randomUUID().toString();
		this.itemCode = itemCode;
		this.amount = amount;
		this.state = BidState.NEW;
	}

	public String getItemCode() {
		return itemCode;
	}

	public int getAmount() {
		return amount;
	}

	public BidState getState() {
		return state;
	}

}
