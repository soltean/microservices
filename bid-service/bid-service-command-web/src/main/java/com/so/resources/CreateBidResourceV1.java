package com.so.resources;

import org.springframework.hateoas.ResourceSupport;

public class CreateBidResourceV1 extends ResourceSupport {

    private final String itemCode;

    public CreateBidResourceV1(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemCode() {
        return itemCode;
    }


}
