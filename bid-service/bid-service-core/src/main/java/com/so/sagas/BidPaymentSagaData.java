package com.so.sagas;

public class BidPaymentSagaData {

    private long bidId;
    private int amount;

    public BidPaymentSagaData(long bidId, int amount) {
        this.bidId = bidId;
        this.amount = amount;
    }

    public long getBidId() {
        return bidId;
    }

    public void setBidId(long bidId) {
        this.bidId = bidId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
