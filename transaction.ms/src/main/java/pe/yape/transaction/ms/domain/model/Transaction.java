package pe.yape.transaction.ms.domain.model;

import lombok.Getter;
import pe.yape.transaction.ms.domain.enums.TransactionStatus;
import pe.yape.transaction.ms.domain.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Transaction {

    private final UUID transactionExternalId;
    private final UUID accountExternalIdDebit;
    private final UUID accountExternalIdCredit;
    private final TransactionType transactionType;
    private final double value;
    private TransactionStatus status;
    private final LocalDateTime createdAt;

    private Transaction(UUID transactionExternalId, UUID accountExternalIdDebit, UUID accountExternalIdCredit, TransactionType transactionType, double value, TransactionStatus status, LocalDateTime createdAt) {
        this.transactionExternalId = transactionExternalId;
        this.accountExternalIdDebit = accountExternalIdDebit;
        this.accountExternalIdCredit = accountExternalIdCredit;
        this.transactionType = transactionType;
        this.value = value;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Transaction loadExisting(UUID transactionExternalId, UUID accountExternalIdDebit, UUID accountExternalIdCredit, TransactionType transferType, double value, TransactionStatus status, LocalDateTime createdAt) {
        return new Transaction(transactionExternalId, accountExternalIdDebit, accountExternalIdCredit, transferType, value, status, createdAt);
    }
    public static Transaction create(UUID accountExternalIdDebit, UUID accountExternalIdCredit, TransactionType transferType, double value) {
        return new Transaction(null, accountExternalIdDebit, accountExternalIdCredit, transferType, value, TransactionStatus.PENDING , LocalDateTime.now());
    }

    public void updateStatus(TransactionStatus newStatus) {
        this.status = newStatus;
    }
}
