package com.so.view;

/**
 * Created by sergiu.oltean on 5/12/2017.
 */
public class ItemView {

	private int reservePrice;
	private String code;

	public ItemView(String code, int reservePrice) {
		this.code = code;
		this.reservePrice = reservePrice;
	}

	public int getReservePrice() {
		return reservePrice;
	}

	public String getCode() {
		return code;
	}

}
