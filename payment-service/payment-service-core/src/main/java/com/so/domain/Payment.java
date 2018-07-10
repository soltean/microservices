package com.so.domain;

import javax.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long paymentId;

    private String itemCode;
    private long amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public Payment() {
    }

    public Payment(String itemCode, long amount) {
        this.itemCode = itemCode;
        this.amount = amount;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
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
