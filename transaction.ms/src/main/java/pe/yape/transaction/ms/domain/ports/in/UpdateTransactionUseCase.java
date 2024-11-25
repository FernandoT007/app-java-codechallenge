package pe.yape.transaction.ms.domain.ports.in;

import pe.yape.transaction.ms.domain.enums.TransactionStatus;
import java.util.UUID;

public interface UpdateTransactionUseCase {

    void updateTransactionStatus(UUID transactionId, TransactionStatus newStatus);
}
