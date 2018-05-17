package com.so.dto;

public class PaymentRequest {

	private long bidCode;
	private int amount;

	public PaymentRequest(){
	}

	public PaymentRequest(long bidCode, int amount) {
		this.bidCode = bidCode;
		this.amount = amount;
	}

	public long getBidCode() {
		return bidCode;
	}

	public int getAmount() {
		return amount;
	}
}
