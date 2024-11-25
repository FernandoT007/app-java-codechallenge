package pe.yape.transaction.ms.infrastructure.adapters.out.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pe.yape.transaction.ms.domain.events.TransactionCreatedEvent;
import pe.yape.transaction.ms.domain.ports.out.EventPublisher;

@Component
@RequiredArgsConstructor
public class TransactionCreatedEventPublisher implements EventPublisher<TransactionCreatedEvent> {

    private final KafkaProducerAdapter<TransactionCreatedEvent> kafkaProducerAdapter;

    @Value("${kafka.topics.transactionCreated}")
    private String transactionCreatedTopic;

    @Override
    public void publishEvent(TransactionCreatedEvent event) {
        kafkaProducerAdapter.publishEvent(transactionCreatedTopic,event);
    }
}