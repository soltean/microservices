package com.so.service;

import com.so.ApprovePaymentCommand;
import com.so.RejectPaymentCommand;
import com.so.domain.Bid;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class BidCommandHandler {

    @Autowired
    private BidService bidService;

    public CommandHandlers commandHandlerDefinitions() {
        return SagaCommandHandlersBuilder
                .fromChannel("paymentService")
                .onMessage(RejectPaymentCommand.class, this::rejectBid)
                .onMessage(ApprovePaymentCommand.class, this::bidPayed)
                .build();
    }

    private Message rejectBid(CommandMessage<RejectPaymentCommand> cmd) {
        RejectPaymentCommand rejectPaymentCommand = cmd.getCommand();
        Optional<Bid> bid = bidService.find(rejectPaymentCommand.getItemCode());
        if (bid.isPresent()) {
            bid.get().reject();
            bidService.update(bid.get());
        }
        return withFailure();
    }

    private Message bidPayed(CommandMessage<ApprovePaymentCommand> cmd) {
        ApprovePaymentCommand approvePaymentCommand = cmd.getCommand();
        bidService.find(approvePaymentCommand.getItemCode()).ifPresent(Bid::reject);
        return withSuccess();
    }
}
