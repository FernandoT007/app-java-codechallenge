package pe.yape.anti.fraud.ms.infrastructure.adapters.in.messaging;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pe.yape.anti.fraud.ms.domain.events.TransactionCreatedEvent;
import pe.yape.anti.fraud.ms.domain.ports.out.EventConsumer;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KafkaConsumerAdapter {

    private final TransactionCreatedEventConsumer transactionCreatedEventConsumer;
    private final ObjectMapper objectMapper;
    private final Map<Class<?>, EventConsumer<?>> eventConsumers = new HashMap<>();

    @PostConstruct
    private void registerEventConsumers() {
        eventConsumers.put(TransactionCreatedEvent.class, transactionCreatedEventConsumer);
    }

    @KafkaListener(topics = "${kafka.topics.transactionCreated}", groupId = "${kafka.group.transactionCreated}")
    public void listenTransactionCreated(ConsumerRecord<String, String> record) {
        processEvent(record, TransactionCreatedEvent.class);
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
