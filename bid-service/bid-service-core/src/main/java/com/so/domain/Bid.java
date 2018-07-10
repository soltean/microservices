package com.so.domain;

import javax.persistence.*;

@Entity
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private BidStatus status;

    private String itemCode;
    private int amount;

    public Bid() {
    }

    public Bid(String itemCode, int amount) {
        this.itemCode = itemCode;
        this.amount = amount;
        this.status = BidStatus.PENDING;
    }

    public void reject() {
        this.status = BidStatus.REJECTED;
    }

    public void approve() {
        this.status = BidStatus.APPROVED;
    }

    public BidStatus getStatus() {
        return status;
    }

    public int getAmount() {
        return amount;
    }

    public String getItemCode() {
        return itemCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStatus(BidStatus status) {
        this.status = status;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
