package com.so.service;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import com.so.events.item.CreateItemEvent;
import com.so.view.ItemView;
import org.springframework.stereotype.Component;

/**
 * Created by sergiu.oltean on 6/6/2017.
 */
@EventSubscriber(id = "itemWorkflow")
@Component
public class ItemViewWorkflow {

	private ItemViewService itemViewService;

	@Autowired
	public ItemViewWorkflow(ItemViewService itemViewService) {
		this.itemViewService = itemViewService;
	}

	@EventHandlerMethod
	public void createItem(DispatchedEvent<CreateItemEvent> event) {
		ItemView itemView = new ItemView(event.getEvent().getItemCode(), event.getEvent().getReservePrice());
		itemViewService.createItem(itemView);
	}

}
