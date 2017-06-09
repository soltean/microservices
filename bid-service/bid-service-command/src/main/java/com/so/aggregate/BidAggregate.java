package com.so.aggregate;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import java.util.List;
import java.util.UUID;
import com.so.command.BidCommand;
import com.so.command.CreateBidCommand;
import com.so.command.EvaluateBidCommand;
import com.so.events.bid.BidWonEvent;
import com.so.events.bid.CreateBidEvent;
import com.so.events.bid.RejectBidEvent;
import com.so.model.BidState;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
public class BidAggregate extends ReflectiveMutableCommandProcessingAggregate<BidAggregate, BidCommand> {

	private String bidCode;
	private BidState bidState;
	private String itemCode;
	private int amount;

	public List<Event> process(CreateBidCommand createBidCommand) {
		return EventUtil.events(new CreateBidEvent(createBidCommand.getItemCode(), createBidCommand.getAmount()));
	}

	public List<Event> process(EvaluateBidCommand evaluateBidCommand) {
		if (this.amount < evaluateBidCommand.getHighestAmount()) {
			return EventUtil.events(new RejectBidEvent(evaluateBidCommand.getItemCode()));
		}
		return EventUtil.events(new BidWonEvent(evaluateBidCommand.getItemCode()));
	}

	public void apply(CreateBidEvent createBidEvent) {
		this.bidCode = UUID.randomUUID().toString();
		this.amount = createBidEvent.getAmount();
		this.itemCode = createBidEvent.getItemCode();
		this.bidState = BidState.NEW;
	}

	public void apply(RejectBidEvent rejectBidEvent) {
		this.itemCode = rejectBidEvent.getItemCode();
		this.bidState = BidState.REJECT;
	}

	public void apply(BidWonEvent wonEvent) {
		this.itemCode = wonEvent.getItemCode();
		this.bidState = BidState.WON;
	}

}
