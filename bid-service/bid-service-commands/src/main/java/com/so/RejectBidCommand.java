package com.so;

import io.eventuate.tram.commands.common.Command;

public class RejectBidCommand implements Command {

    private String itemCode;

    public RejectBidCommand(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
