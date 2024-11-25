package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AntifraudService {

    private final KafkaTemplate<String, TransactionValidated> kafkaTemplate;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = Constants.TRANSACTION_CREATED_TOPIC, groupId = Constants.GROUP_ID_TRANSACTION_TOPIC)
    public void listenerTransactionCreated(ConsumerRecord<String, String> record)
    {
        try {
            Transaction transaction = objectMapper.readValue(record.value(), Transaction.class);
            TransactionStatus status = transaction.value() > 1000 ? TransactionStatus.REJECTED : TransactionStatus.APPROVED;
            TransactionValidated transactionValidated = new TransactionValidated(transaction.transactionId(), status);
            kafkaTemplate.send(Constants.TRANSACTION_UPDATE_STATUS_TOPIC, transactionValidated);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
