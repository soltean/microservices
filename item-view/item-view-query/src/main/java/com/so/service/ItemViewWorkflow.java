package com.so.service;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import com.so.events.item.CreateItemEvent;
import com.so.events.item.UpdateItemEvent;
import com.so.view.ItemView;

/**
 * Created by sergiu.oltean on 6/6/2017.
 */
@EventSubscriber(id = "itemWorkflow")
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

	@EventHandlerMethod
	public void updateItem(DispatchedEvent<UpdateItemEvent> event) {
		itemViewService.updateItemState(event.getEvent().getItemCode(), event.getEvent().getItemState());
	}

}
