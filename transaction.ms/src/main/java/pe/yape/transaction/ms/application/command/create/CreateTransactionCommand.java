package pe.yape.transaction.ms.application.command.create;

import pe.yape.transaction.ms.domain.enums.TransactionType;
import java.util.UUID;

public record CreateTransactionCommand (
    UUID accountExternalIdDebit,
    UUID accountExternalIdCredit,
    TransactionType transactionType,
    double value
) {}
