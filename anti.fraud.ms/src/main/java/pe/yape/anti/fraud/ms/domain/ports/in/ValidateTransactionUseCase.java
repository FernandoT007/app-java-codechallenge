package pe.yape.anti.fraud.ms.domain.ports.in;

import java.util.UUID;

public interface ValidateTransactionUseCase {
    void validateTransaction(UUID transactionExternalId, double value);

}
