package com.so.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    private long bidId;

    private long amount;

    private PaymentStatus status;

    public Payment() {
    }

    public Payment(long bidId, long amount) {
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
