package com.so;

import io.eventuate.tram.commands.common.Command;

public class RejectPaymentCommand implements Command {

  private String itemCode;

  private RejectPaymentCommand() {
  }

  public RejectPaymentCommand(String itemCode) {
    this.itemCode = itemCode;
  }

  public String getBidId() {
    return itemCode;
  }

  public void setBidId(String bidId) {
    this.itemCode = bidId;
  }
}
