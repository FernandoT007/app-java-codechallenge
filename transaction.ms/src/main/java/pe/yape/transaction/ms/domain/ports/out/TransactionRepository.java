package pe.yape.transaction.ms.domain.ports.out;

import pe.yape.transaction.ms.domain.model.Transaction;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {

    Transaction save(Transaction transaction);
    Optional<Transaction> findById(UUID id);
}
