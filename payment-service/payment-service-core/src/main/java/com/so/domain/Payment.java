package com.so.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    private long bidId;


    private String itemCode;
    private long amount;

    private PaymentStatus status;

    public Payment() {
    }

    public Payment(String itemCode, long amount) {
        this.itemCode = itemCode;
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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void finish() {
        this.status = PaymentStatus.SUCCESS;
    }

    public void reject() {
        this.status = PaymentStatus.REJECTED;
    }
}
