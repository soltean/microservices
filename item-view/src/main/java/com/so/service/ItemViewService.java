package com.so.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.so.model.ItemState;
import com.so.repository.ItemViewRepository;

/**
 * Created by sergiu.oltean on 5/12/2017.
 */
@Service
public class ItemViewService {

	@Autowired
	private ItemViewRepository itemViewRepository;

	public void updateItemState(String itemCode, ItemState itemState) {

	}

}
