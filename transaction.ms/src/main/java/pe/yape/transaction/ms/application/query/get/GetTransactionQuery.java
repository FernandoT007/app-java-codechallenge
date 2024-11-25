package pe.yape.transaction.ms.application.query.get;

import java.util.UUID;

public record GetTransactionQuery(
    UUID transactionId
){}
