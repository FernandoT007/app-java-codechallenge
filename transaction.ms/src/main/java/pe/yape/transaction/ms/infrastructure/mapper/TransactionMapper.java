package pe.yape.transaction.ms.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.yape.transaction.ms.application.command.create.CreateTransactionCommand;
import pe.yape.transaction.ms.domain.enums.TransactionStatus;
import pe.yape.transaction.ms.domain.enums.TransactionType;
import pe.yape.transaction.ms.domain.events.TransactionCreatedEvent;
import pe.yape.transaction.ms.domain.model.Transaction;
import pe.yape.transaction.ms.infrastructure.adapters.in.rest.dto.CreateTransactionRequest;
import pe.yape.transaction.ms.infrastructure.adapters.in.rest.dto.TransactionResponse;
import pe.yape.transaction.ms.infrastructure.adapters.out.persistence.entity.TransactionEntity;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "tranferTypeId", target = "transactionType")
    CreateTransactionCommand requestToCommand (CreateTransactionRequest transaction);

    @Mapping(target = "transactionType", expression = "java(mapTransactionType(transaction.getTransactionType()))")
    @Mapping(target = "transactionStatus", expression = "java(mapTransactionStatus(transaction.getStatus()))")
    TransactionResponse domainToResponse (Transaction transaction);

    @Mapping(target = "transactionType", expression = "java(transaction.getTransactionType().getTypeId())")
    @Mapping(target = "status", expression = "java(transaction.getStatus().getTypeId())")
    @Mapping(source = "transactionExternalId", target = "id")
    TransactionEntity domainToEntity(Transaction transaction);
    default Transaction entityToDomain(TransactionEntity entity) {
        return Transaction.loadExisting(
                entity.getId(),
                entity.getAccountExternalIdDebit(),
                entity.getAccountExternalIdCredit(),
                TransactionType.fromTypeId(entity.getTransactionType()),
                entity.getValue(),
                TransactionStatus.fromTypeId(entity.getStatus()),
                entity.getCreatedAt()
        );
    }

    TransactionCreatedEvent domainToEvent(Transaction transaction);


    default TransactionResponse.TransactionTypeResponse mapTransactionType(TransactionType transactionType) {
        return new TransactionResponse.TransactionTypeResponse(transactionType.name());
    }

    default TransactionResponse.TransactionStatusResponse mapTransactionStatus(TransactionStatus transactionStatus) {
        return new TransactionResponse.TransactionStatusResponse(transactionStatus.name());
    }

    default TransactionType mapTransactionType(int tranferTypeId) {
        return TransactionType.fromTypeId(tranferTypeId);
    }
    default TransactionStatus mapTransactionStatus(int statusId) {
        return TransactionStatus.fromTypeId(statusId);
    }

}
