package pe.yape.transaction.ms.domain.ports.in;

import pe.yape.transaction.ms.domain.model.Transaction;
import java.util.Optional;
import java.util.UUID;

public interface GetTransactionUseCase {

    Optional<Transaction> getTransactionById (UUID transactionId);
}
