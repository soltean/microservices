package com.so.service;

import com.so.repository.ItemViewRepository;
import com.so.view.ItemView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sergiu.oltean on 5/12/2017.
 */
@Service
public class ItemViewService {

    private ItemViewRepository itemViewRepository;

    @Autowired
    public ItemViewService(ItemViewRepository itemViewRepository) {
        this.itemViewRepository = itemViewRepository;
    }

    public void createItem(ItemView itemView) {
        itemViewRepository.save(itemView);
    }

    public List<ItemView> findAllItems() {
        return itemViewRepository.findAll();
    }

}
