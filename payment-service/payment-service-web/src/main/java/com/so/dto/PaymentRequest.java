package com.so.dto;

public class PaymentRequest {

    private String itemCode;
    private int amount;

    public PaymentRequest() {
    }

    public PaymentRequest(String itemCode, int amount) {
        this.itemCode = itemCode;
        this.amount = amount;
    }

    public String getItemCode() {
        return itemCode;
    }

    public int getAmount() {
        return amount;
    }
}
