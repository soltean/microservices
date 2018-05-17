package com.so.service;

import com.so.InitiatePaymentCommand;
import com.so.domain.Payment;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class PaymentCommandHandler {

    @Autowired
    private PaymentService paymentService;

    public CommandHandlers commandHandlerDefinitions() {
        return SagaCommandHandlersBuilder
                .fromChannel("bidService")
                .onMessage(InitiatePaymentCommand.class, this::pay)
                .build();
    }

    private Message pay(CommandMessage<InitiatePaymentCommand> commandMessage) {
        InitiatePaymentCommand cmd = commandMessage.getCommand();
        Payment payment = new Payment(cmd.getBidId(), cmd.getAmount());
        try {
            paymentService.pay(payment);
            return withSuccess();
        } catch (Exception e) {
            payment.reject();
            return withFailure();
        }
    }
}
