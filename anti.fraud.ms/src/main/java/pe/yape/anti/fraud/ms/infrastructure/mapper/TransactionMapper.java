package pe.yape.anti.fraud.ms.infrastructure.mapper;

import org.mapstruct.Mapper;
import pe.yape.anti.fraud.ms.domain.events.TransactionCreatedEvent;
import pe.yape.anti.fraud.ms.domain.events.TransactionStatusUpdatedEvent;
import pe.yape.anti.fraud.ms.domain.model.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionStatusUpdatedEvent domainToEvent(Transaction transaction);

}
