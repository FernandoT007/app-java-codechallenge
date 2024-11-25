package pe.yape.transaction.ms.infrastructure.adapters.out.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducerAdapter<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    public void publishEvent(String topic, T event) {
        kafkaTemplate.send(topic, event);
    }
}
