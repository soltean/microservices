package com.so;

import io.eventuate.tram.commands.common.Command;

public class InitiatePaymentCommand implements Command {

    public InitiatePaymentCommand() {
    }

    public InitiatePaymentCommand(String itemCode, long amount) {
        this.itemCode = itemCode;
        this.amount = amount;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    private String itemCode;
    private long amount;
}
