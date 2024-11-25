package org.example;

import java.util.UUID;

public record Transaction (
    UUID transactionId,
    double value
) {}
