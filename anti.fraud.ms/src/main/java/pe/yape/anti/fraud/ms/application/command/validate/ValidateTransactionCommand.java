package pe.yape.anti.fraud.ms.application.command.validate;

import java.util.UUID;

public record ValidateTransactionCommand(
        UUID transactionExternalId,
        double value
) { }
