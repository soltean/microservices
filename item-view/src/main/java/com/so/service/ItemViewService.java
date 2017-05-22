package com.so.service;

import com.so.model.ItemState;
import com.so.repository.ItemViewRepository;
import com.so.view.ItemView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sergiu.oltean on 5/12/2017.
 */
@Service
public class ItemViewService {

    @Autowired
    private ItemViewRepository itemViewRepository;

    public void createItem(ItemView itemView) {
        itemViewRepository.save(itemView);
    }

    public void updateItemState(String itemCode, ItemState itemState) {
        ItemView item = itemViewRepository.findOne(itemCode);
        item.setItemState(itemState);
    }

}
