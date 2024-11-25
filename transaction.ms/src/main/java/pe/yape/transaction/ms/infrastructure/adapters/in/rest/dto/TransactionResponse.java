package pe.yape.transaction.ms.infrastructure.adapters.in.rest.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponse (
        UUID transactionExternalId,
        TransactionTypeResponse transactionType,
        TransactionStatusResponse transactionStatus,
        double value,
        LocalDateTime createdAt
) {
    public record TransactionTypeResponse(String name) {}
    public record TransactionStatusResponse(String name) {}
}
