package com.so.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.so.aggregate.ItemAggregate;
import com.so.commands.CreateItemCommand;
import com.so.commands.ItemCommand;

/**
 * Created by sergiu.oltean on 5/10/2017.
 */
@Service
public class ItemService {

	@Autowired
	private AggregateRepository<ItemAggregate, ItemCommand> itemRepository;

	public CompletableFuture<EntityWithIdAndVersion<ItemAggregate>> createItem(String itemCode, int reservePrice) {
		return itemRepository.save(new CreateItemCommand(itemCode, reservePrice));
	}

}
