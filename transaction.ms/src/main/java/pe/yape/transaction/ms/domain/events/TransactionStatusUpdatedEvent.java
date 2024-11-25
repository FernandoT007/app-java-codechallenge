package pe.yape.transaction.ms.domain.events;

import pe.yape.transaction.ms.domain.enums.TransactionStatus;

import java.util.UUID;

public record TransactionStatusUpdatedEvent(UUID transactionExternalId, TransactionStatus status) {}
