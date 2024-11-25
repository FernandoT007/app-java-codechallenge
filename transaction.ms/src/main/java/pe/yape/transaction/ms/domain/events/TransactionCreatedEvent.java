package pe.yape.transaction.ms.domain.events;

import java.util.UUID;

public record TransactionCreatedEvent(UUID transactionExternalId, double value) {
}
