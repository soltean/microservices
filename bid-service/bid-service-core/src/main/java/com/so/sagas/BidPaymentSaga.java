package com.so.sagas;

import com.so.InitiatePaymentCommand;
import com.so.RejectPaymentCommand;
import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

public class BidPaymentSaga implements SimpleSaga<BidPaymentSagaData> {

    @Override
    public SagaDefinition<BidPaymentSagaData> getSagaDefinition() {
        return step().withCompensation(this::reject)
                .step().invokeParticipant(this::initiatePayment)
                .build();
    }

    public CommandWithDestination initiatePayment(BidPaymentSagaData data) {
        return send(new InitiatePaymentCommand(data.getBidId(), data.getAmount()))
                .to("paymentService")
                .build();
    }

    public CommandWithDestination reject(BidPaymentSagaData data) {
        return send(new RejectPaymentCommand(data.getBidId()))
                .to("bidService")
                .build();
    }
}
