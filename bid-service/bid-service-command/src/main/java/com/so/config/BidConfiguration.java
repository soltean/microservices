package com.so.config;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.so.aggregate.BidAggregate;
import com.so.command.BidCommand;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
@Configuration
@EnableEventHandlers
public class BidConfiguration {

	@Bean
	public AggregateRepository<BidAggregate, BidCommand> bidRepository(EventuateAggregateStore eventStore) {
		return new AggregateRepository<>(BidAggregate.class, eventStore);
	}

}
