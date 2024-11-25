package pe.yape.transaction.ms.infrastructure.adapters.out.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.yape.transaction.ms.domain.model.Transaction;
import pe.yape.transaction.ms.domain.ports.out.TransactionRepository;
import pe.yape.transaction.ms.infrastructure.adapters.out.persistence.entity.TransactionEntity;
import pe.yape.transaction.ms.infrastructure.mapper.TransactionMapper;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TransactionRepositoryAdapter implements TransactionRepository {

    private final TransactionJpaRepository transactionJpaRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = transactionMapper.domainToEntity(transaction);
        return transactionMapper.entityToDomain(transactionJpaRepository.save(entity));
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return transactionJpaRepository.findById(id).map(transactionMapper::entityToDomain);
    }
}
