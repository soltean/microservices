package com.so;

import io.eventuate.tram.commands.common.Command;

public class RejectPaymentCommand implements Command {

  private long bidId;

  private RejectPaymentCommand() {
  }


  public RejectPaymentCommand(long bidId) {

    this.bidId = bidId;
  }

  public long getBidId() {
    return bidId;
  }

  public void setBidId(long bidId) {
    this.bidId = bidId;
  }
}
