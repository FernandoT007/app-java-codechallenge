package pe.yape.transaction.ms.infrastructure.adapters.in.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pe.yape.transaction.ms.application.command.update.UpdateTransactionStatusCommand;
import pe.yape.transaction.ms.application.command.update.UpdateTransactionStatusCommandHandler;
import pe.yape.transaction.ms.domain.events.TransactionStatusUpdatedEvent;
import pe.yape.transaction.ms.domain.ports.out.EventConsumer;

@Component
@RequiredArgsConstructor
public class TransactionStatusUpdatedEventConsumer implements EventConsumer<TransactionStatusUpdatedEvent> {

    private final UpdateTransactionStatusCommandHandler updateTransactionStatusCommandHandler;

    @Override
    public void handleEvent(TransactionStatusUpdatedEvent event) {
        UpdateTransactionStatusCommand command = new UpdateTransactionStatusCommand(event.transactionExternalId(), event.status());
        updateTransactionStatusCommandHandler.handle(command);
    }
}