package com.so.workflow;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import com.so.aggregate.BidAggregate;
import com.so.command.BidWonCommand;
import com.so.command.RejectBidCommand;
import com.so.events.bid.BidWonEvent;
import com.so.events.bid.RejectBidEvent;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
@EventSubscriber(id = "bidWorkflow")
@Component
public class BidWorkflow {

	@EventHandlerMethod
	public CompletableFuture<EntityWithIdAndVersion<BidAggregate>> bidRejected(EventHandlerContext<RejectBidEvent> ctx) {
		String itemCode = ctx.getEvent().getItemCode();
		return ctx.update(BidAggregate.class, itemCode, new RejectBidCommand());
	}

	@EventHandlerMethod
	public CompletableFuture<EntityWithIdAndVersion<BidAggregate>> bidWon(EventHandlerContext<BidWonEvent> ctx) {
		String itemCode = ctx.getEvent().getItemCode();
		return ctx.update(BidAggregate.class, itemCode, new BidWonCommand());
	}
}
