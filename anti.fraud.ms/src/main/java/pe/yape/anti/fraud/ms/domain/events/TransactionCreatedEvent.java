package pe.yape.anti.fraud.ms.domain.events;

import java.util.UUID;

public record TransactionCreatedEvent(UUID transactionExternalId, double value) {
}
