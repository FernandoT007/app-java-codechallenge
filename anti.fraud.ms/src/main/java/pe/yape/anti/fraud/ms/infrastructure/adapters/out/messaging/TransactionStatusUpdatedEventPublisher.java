package pe.yape.anti.fraud.ms.infrastructure.adapters.out.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pe.yape.anti.fraud.ms.domain.events.TransactionStatusUpdatedEvent;
import pe.yape.anti.fraud.ms.domain.ports.out.EventPublisher;

@Component
@RequiredArgsConstructor
public class TransactionStatusUpdatedEventPublisher implements EventPublisher<TransactionStatusUpdatedEvent> {

    private final KafkaProducerAdapter<TransactionStatusUpdatedEvent> kafkaProducerAdapter;

    @Value("${kafka.topics.transactionStatusUpdated}")
    private String transactionStatusUpdated;

    @Override
    public void publishEvent(TransactionStatusUpdatedEvent event) {
        kafkaProducerAdapter.publishEvent(transactionStatusUpdated,event);
    }
}