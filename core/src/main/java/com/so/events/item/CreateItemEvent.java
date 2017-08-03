package com.so.events.item;

/**
 * Created by sergiu.oltean on 5/9/2017.
 */
public class CreateItemEvent implements ItemEvent {

    private String itemCode;
    private int reservePrice;

    public CreateItemEvent(String itemCode, int reservePrice) {
        this.itemCode = itemCode;
        this.reservePrice = reservePrice;
    }

    public CreateItemEvent() {
    }

    public String getItemCode() {
        return itemCode;
    }

    public int getReservePrice() {
        return reservePrice;
    }

}
