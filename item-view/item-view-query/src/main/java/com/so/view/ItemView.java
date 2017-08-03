package com.so.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sergiu.oltean on 5/12/2017.
 */
@Document
public class ItemView {

    private int reservePrice;
    @Id
    private String code;

    public ItemView(String code, int reservePrice) {
        this.code = code;
        this.reservePrice = reservePrice;
    }

    public int getReservePrice() {
        return reservePrice;
    }

    public String getCode() {
        return code;
    }

    public void setReservePrice(int reservePrice) {
        this.reservePrice = reservePrice;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
