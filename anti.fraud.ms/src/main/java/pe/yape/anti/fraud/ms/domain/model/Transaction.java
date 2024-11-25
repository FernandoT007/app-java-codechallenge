package pe.yape.anti.fraud.ms.domain.model;

import lombok.Getter;
import pe.yape.anti.fraud.ms.domain.enums.TransactionStatus;


import java.util.UUID;

@Getter
public class Transaction {

    private final UUID transactionExternalId;
    private final double value;
    private TransactionStatus status;
    private Transaction(UUID transactionExternalId,
                        double value,
                        TransactionStatus status) {
        this.transactionExternalId = transactionExternalId;
        this.value = value;
        this.status = status;

    }
    public static Transaction create(UUID accountExternalIdDebit,
                                     double value) {
        TransactionStatus status = determineTransactionStatus(value);
        return new Transaction(accountExternalIdDebit,value,status);
    }

    private static TransactionStatus determineTransactionStatus(double value) {
        return value > 1000 ? TransactionStatus.REJECTED : TransactionStatus.APPROVED;
    }
}
