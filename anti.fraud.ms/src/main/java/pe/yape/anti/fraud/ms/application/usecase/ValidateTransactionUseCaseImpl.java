package pe.yape.anti.fraud.ms.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.yape.anti.fraud.ms.domain.events.TransactionCreatedEvent;
import pe.yape.anti.fraud.ms.domain.events.TransactionStatusUpdatedEvent;
import pe.yape.anti.fraud.ms.domain.model.Transaction;
import pe.yape.anti.fraud.ms.domain.ports.in.ValidateTransactionUseCase;
import pe.yape.anti.fraud.ms.domain.ports.out.EventPublisher;
import pe.yape.anti.fraud.ms.infrastructure.mapper.TransactionMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValidateTransactionUseCaseImpl implements ValidateTransactionUseCase {

    private final EventPublisher<TransactionStatusUpdatedEvent> eventPublisher;
    private final TransactionMapper transactionMapper;

    @Override
    public void validateTransaction(UUID transactionExternalId, double value) {
        Transaction transaction = Transaction.create(transactionExternalId, value);
        TransactionStatusUpdatedEvent event = transactionMapper.domainToEvent(transaction);
        eventPublisher.publishEvent(event);
    }
}
