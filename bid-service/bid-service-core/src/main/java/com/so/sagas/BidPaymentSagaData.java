package com.so.sagas;

public class BidPaymentSagaData {

    private String itemCode;
    private int amount;

    public BidPaymentSagaData() {
    }

    public BidPaymentSagaData(String itemCode, int amount) {
        this.itemCode = itemCode;
        this.amount = amount;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
