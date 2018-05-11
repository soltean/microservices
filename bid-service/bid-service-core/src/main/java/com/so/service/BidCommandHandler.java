package com.so.service;

import com.so.RejectBidCommand;
import com.so.domain.Bid;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class BidCommandHandler {

    @Autowired
    private BidService bidService;

    public CommandHandlers commandHandlerDefinitions() {
        return SagaCommandHandlersBuilder
                .fromChannel("bidService")
                .onMessage(RejectBidCommand.class, this::rejectBid)
                .build();
    }

    private Message rejectBid(CommandMessage<RejectBidCommand> cmd) {
        RejectBidCommand rejectPaymentCommand = cmd.getCommand();
        bidService.find(rejectPaymentCommand.getBidId()).ifPresent(Bid::reject);
        return withSuccess();
    }
}
