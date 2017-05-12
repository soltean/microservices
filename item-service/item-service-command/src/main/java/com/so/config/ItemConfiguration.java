package com.so.config;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.so.aggregate.ItemAggregate;
import com.so.commands.ItemCommand;

/**
 * Created by sergiu.oltean on 5/10/2017.
 */
@Configuration
@EnableEventHandlers
public class ItemConfiguration {

	@Bean
	public AggregateRepository<ItemAggregate, ItemCommand> itemRepository(EventuateAggregateStore eventStore) {
		return new AggregateRepository(ItemAggregate.class, eventStore);
	}

}
