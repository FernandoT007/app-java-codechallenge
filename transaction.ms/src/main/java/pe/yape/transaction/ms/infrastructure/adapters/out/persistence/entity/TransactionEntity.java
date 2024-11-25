package pe.yape.transaction.ms.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "account_external_id_credit", nullable = false)
    private UUID accountExternalIdDebit;

    @Column(name = "account_external_id_debit", nullable = false)
    private UUID accountExternalIdCredit;

    @Column(name = "transaction_type", nullable = false)
    private int transactionType;

    @Column(nullable = false)
    private double value;

    @Column( name = "status", nullable = false)
    private int  status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}
