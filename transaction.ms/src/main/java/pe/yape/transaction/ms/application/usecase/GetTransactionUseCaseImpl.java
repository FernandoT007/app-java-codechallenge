package pe.yape.transaction.ms.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.yape.transaction.ms.domain.model.Transaction;
import pe.yape.transaction.ms.domain.ports.in.GetTransactionUseCase;
import pe.yape.transaction.ms.domain.ports.out.CacheRepository;
import pe.yape.transaction.ms.domain.ports.out.TransactionRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetTransactionUseCaseImpl implements GetTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final CacheRepository<Transaction> cacheRepository;

    @Override
    public Optional<Transaction> getTransactionById(UUID transactionId) {
        Transaction cachedTransaction = cacheRepository.getCached(transactionId);
        if (cachedTransaction != null) {
            return Optional.of(cachedTransaction);
        }
        return transactionRepository.findById(transactionId);
    }



}
