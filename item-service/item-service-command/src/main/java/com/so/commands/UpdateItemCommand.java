package com.so.commands;

import com.so.model.ItemState;

/**
 * Created by sergiu.oltean on 6/9/2017.
 */
public class UpdateItemCommand extends ItemCommand {

    private ItemState itemState;

    public UpdateItemCommand(String itemCode, ItemState itemState) {
        super(itemCode);
        this.itemState = itemState;
    }

    public ItemState getItemState() {
        return itemState;
    }
}
