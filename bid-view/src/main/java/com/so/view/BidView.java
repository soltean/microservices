package com.so.view;

import java.util.UUID;
import com.so.model.BidState;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sergiu.oltean on 5/12/2017.
 */
@Document
public class BidView {

	@Id
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

	public void setBidCode(String bidCode) {
		this.bidCode = bidCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setState(BidState state) {
		this.state = state;
	}
}
