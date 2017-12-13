package com.so.resources;

import org.springframework.hateoas.ResourceSupport;

public class CreateBidResourceV2 extends ResourceSupport {

    private final String itemCode;
    private final String newProperty;

    public CreateBidResourceV2(String itemCode, String newProperty) {
        this.itemCode = itemCode;
        this.newProperty = newProperty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getNewProperty() {
        return newProperty;
    }
}
