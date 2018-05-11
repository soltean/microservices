package com.so;

import io.eventuate.tram.commands.common.Command;

public class ApprovePaymentCommand implements Command {

  private long bidId;

  private ApprovePaymentCommand() {
  }


  public ApprovePaymentCommand(long bidId) {

    this.bidId = bidId;
  }

  public long getBidId() {
    return bidId;
  }

  public void setBidId(long bidId) {
    this.bidId = bidId;
  }
}
