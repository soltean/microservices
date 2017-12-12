package com.so.controller;

import org.springframework.hateoas.ResourceSupport;

public class CreateBidResource extends ResourceSupport {

    private  String itemCode;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
