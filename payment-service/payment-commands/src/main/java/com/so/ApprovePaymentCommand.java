package com.so;

import io.eventuate.tram.commands.common.Command;

public class ApprovePaymentCommand implements Command {

  private String itemCode;

  private ApprovePaymentCommand() {
  }

  public ApprovePaymentCommand(String itemCode) {

    this.itemCode = itemCode;
  }

  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }
}
