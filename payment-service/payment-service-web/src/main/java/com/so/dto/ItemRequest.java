package com.so.dto;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
public class ItemRequest {

	private String itemCode;
	private int reservePrice;

	public ItemRequest(){
	}

    public ItemRequest(String itemCode, int reservePrice) {
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
