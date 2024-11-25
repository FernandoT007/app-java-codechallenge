package pe.yape.anti.fraud.ms.domain.events;

import pe.yape.anti.fraud.ms.domain.enums.TransactionStatus;

import java.util.UUID;

public record TransactionStatusUpdatedEvent(UUID transactionExternalId, TransactionStatus status) {}
