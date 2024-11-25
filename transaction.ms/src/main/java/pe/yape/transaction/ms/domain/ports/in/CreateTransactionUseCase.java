package pe.yape.transaction.ms.domain.ports.in;

import pe.yape.transaction.ms.domain.enums.TransactionType;
import pe.yape.transaction.ms.domain.model.Transaction;

import java.util.UUID;

public interface CreateTransactionUseCase {

    Transaction createTransaction(UUID accountExternalIdDebit, UUID accountExternalIdCredit, TransactionType transferType, double value);
}
