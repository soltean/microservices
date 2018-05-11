package com.so;

import io.eventuate.tram.commands.common.Command;

public class InitiatePaymentCommand implements Command {

    public InitiatePaymentCommand(long bidId, long amount) {
        this.bidId = bidId;
        this.amount = amount;
    }

    public long getBidId() {
        return bidId;
    }

    public void setBidId(long bidId) {
        this.bidId = bidId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    private long bidId;
    private long amount;
}
