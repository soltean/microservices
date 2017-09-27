package com.so.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;
import com.so.aggregate.ItemAggregate;
import com.so.commands.CreateItemCommand;
import com.so.commands.ItemCommand;

/**
 * Created by sergiu.oltean on 5/10/2017.
 */
@Service
public class ItemService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AggregateRepository<ItemAggregate, ItemCommand> itemRepository;

	@Autowired
	private Tracer tracer;

	public CompletableFuture<EntityWithIdAndVersion<ItemAggregate>> createItem(String itemCode, int reservePrice) {
		Span createItemSpan = tracer.createSpan("createItem method");
		try {
			logger.info("The service created the item " + itemCode + ", " + reservePrice);
			return itemRepository.save(new CreateItemCommand(itemCode, reservePrice));
		} finally {
			tracer.close(createItemSpan);
		}
	}

}
