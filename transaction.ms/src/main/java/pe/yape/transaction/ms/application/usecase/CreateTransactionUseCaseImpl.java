package pe.yape.transaction.ms.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.yape.transaction.ms.domain.enums.TransactionType;
import pe.yape.transaction.ms.domain.events.TransactionCreatedEvent;
import pe.yape.transaction.ms.domain.model.Transaction;
import pe.yape.transaction.ms.domain.ports.in.CreateTransactionUseCase;
import pe.yape.transaction.ms.domain.ports.out.CacheRepository;
import pe.yape.transaction.ms.domain.ports.out.EventPublisher;
import pe.yape.transaction.ms.domain.ports.out.TransactionRepository;
import pe.yape.transaction.ms.infrastructure.mapper.TransactionMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final EventPublisher<TransactionCreatedEvent> eventPublisher;
    private final CacheRepository<Transaction> cacheRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public Transaction createTransaction(UUID accountExternalIdDebit, UUID accountExternalIdCredit, TransactionType transferType, double value) {

        Transaction transaction = Transaction.create(accountExternalIdDebit,accountExternalIdCredit,transferType,value);
        Transaction savedTransaction = transactionRepository.save(transaction);
        cacheRepository.save(savedTransaction.getTransactionExternalId(),savedTransaction);
        TransactionCreatedEvent event = transactionMapper.domainToEvent(savedTransaction);
        eventPublisher.publishEvent(event);
        return savedTransaction;
    }
}
