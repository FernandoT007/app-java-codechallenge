package pe.yape.transaction.ms.application.command.update;

import pe.yape.transaction.ms.domain.enums.TransactionStatus;
import java.util.UUID;

public record UpdateTransactionStatusCommand (
   UUID transactionExternalId,
   TransactionStatus status
){}
