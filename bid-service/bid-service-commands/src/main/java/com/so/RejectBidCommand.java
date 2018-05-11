package com.so;

import io.eventuate.tram.commands.common.Command;

public class RejectBidCommand implements Command {

    private long bidId;

    public RejectBidCommand(long bidId) {
        this.bidId = bidId;
    }

    public long getBidId() {
        return bidId;
    }

    public void setBidId(long bidId) {
        this.bidId = bidId;
    }
}
