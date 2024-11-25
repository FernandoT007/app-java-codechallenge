package org.example;

import java.util.UUID;

public record TransactionValidated(
    UUID transactionId,
    TransactionStatus status
) { }
