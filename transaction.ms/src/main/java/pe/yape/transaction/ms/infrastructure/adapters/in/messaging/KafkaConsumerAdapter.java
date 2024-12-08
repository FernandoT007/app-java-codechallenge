package pe.yape.transaction.ms.infrastructure.adapters.in.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pe.yape.transaction.ms.domain.events.TransactionStatusUpdatedEvent;
import pe.yape.transaction.ms.domain.ports.out.EventConsumer;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KafkaConsumerAdapter {

    private final TransactionStatusUpdatedEventConsumer transactionCreatedEventConsumer;
    private final ObjectMapper objectMapper;
    private final Map<Class<?>, EventConsumer<?>> eventConsumers = new HashMap<>();

    @PostConstruct
    private void registerEventConsumers() {
        eventConsumers.put(TransactionStatusUpdatedEvent.class, transactionCreatedEventConsumer);
    }
    @KafkaListener(topics = "${kafka.topics.transactionStatusUpdated}", groupId = "${kafka.group.transactionStatusUpdated}", containerFactory = "kafkaListenerContainerFactory")
    public void listenTransactionStatusUpdated(ConsumerRecord<String, String> record) {
        processEvent(record,TransactionStatusUpdatedEvent.class);
    }

    private <T> void processEvent(ConsumerRecord<String, String> record, Class<T> eventType) {
        try {
            T event = objectMapper.readValue(record.value(), eventType);
            EventConsumer<T> consumer = (EventConsumer<T>) eventConsumers.get(eventType);

            if (consumer != null) {
                consumer.handleEvent(event);
            } else {
                System.out.println("Evento desconocido: " + eventType);
            }
        } catch (Exception e) {
            System.err.println("Error al procesar el mensaje: " + e.getMessage());
        }
    }
}
