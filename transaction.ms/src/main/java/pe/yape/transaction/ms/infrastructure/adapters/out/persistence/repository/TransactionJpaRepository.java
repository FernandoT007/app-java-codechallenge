package pe.yape.transaction.ms.infrastructure.adapters.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.yape.transaction.ms.infrastructure.adapters.out.persistence.entity.TransactionEntity;

import java.util.UUID;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, UUID> {

}
