package com.so.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.so.aggregate.BidAggregate;
import com.so.command.BidCommand;
import com.so.command.CreateBidCommand;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
@Service
public class BidService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private AggregateRepository<BidAggregate, BidCommand> bidRepository;

	@Autowired
	public BidService(AggregateRepository<BidAggregate, BidCommand> bidRepository) {
		this.bidRepository = bidRepository;
	}

	public CompletableFuture<EntityWithIdAndVersion<BidAggregate>> addBid(String itemCode, int amount) {
		return bidRepository.save(new CreateBidCommand(itemCode, amount));
	}
}
