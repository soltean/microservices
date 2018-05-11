package com.so.config;

import com.so.service.PaymentCommandHandler;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaLockManager;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import(SagaParticipantConfiguration.class)
@EnableJpaRepositories
@EnableAutoConfiguration
public class PaymentConfiguration {

    @Bean
    public PaymentCommandHandler paymentCommandHandler() {
        return new PaymentCommandHandler();
    }

    @Bean
    public CommandDispatcher consumerCommandDispatcher(PaymentCommandHandler target, SagaLockManager sagaLockManager) {
        return new SagaCommandDispatcher("paymentCommandDispatcher", target.commandHandlerDefinitions());
    }
}
