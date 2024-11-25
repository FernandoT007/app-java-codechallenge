package pe.yape.transaction.ms.infrastructure.adapters.in.rest.dto;

import java.util.UUID;

public record CreateTransactionRequest(
        UUID accountExternalIdDebit,
        UUID accountExternalIdCredit,
        int tranferTypeId,
        double value
) { }
