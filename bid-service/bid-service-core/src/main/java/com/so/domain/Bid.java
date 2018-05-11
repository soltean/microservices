package com.so.domain;

import javax.persistence.*;

@Entity
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BidStatus status;

    private String itemCode;
    private int amount;

    public Bid(String itemCode, int amount) {
        this.itemCode = itemCode;
        this.amount = amount;
        this.status = BidStatus.PENDING;
    }

    public void reject() {
        this.status = BidStatus.REJECTED;
    }

    public BidStatus getStatus() {
        return status;
    }

    public int getAmount() {
        return amount;
    }

    public Long getId() {
        return id;
    }
}
