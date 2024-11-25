package pe.yape.anti.fraud.ms.infrastructure.adapters.in.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.yape.anti.fraud.ms.application.command.validate.ValidateTransactionCommand;
import pe.yape.anti.fraud.ms.application.command.validate.ValidateTransactionCommandHandler;
import pe.yape.anti.fraud.ms.domain.events.TransactionCreatedEvent;
import pe.yape.anti.fraud.ms.domain.ports.out.EventConsumer;

@Component
@RequiredArgsConstructor
public class TransactionCreatedEventConsumer  implements EventConsumer<TransactionCreatedEvent> {

    private final ValidateTransactionCommandHandler validateTransactionCommandHandler;

    @Override
    public void handleEvent(TransactionCreatedEvent event) {
        ValidateTransactionCommand command = new ValidateTransactionCommand(event.transactionExternalId(), event.value());
        validateTransactionCommandHandler.handle(command);
    }
}
