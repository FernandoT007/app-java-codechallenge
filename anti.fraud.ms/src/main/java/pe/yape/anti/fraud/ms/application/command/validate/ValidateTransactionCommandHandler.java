package pe.yape.anti.fraud.ms.application.command.validate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.yape.anti.fraud.ms.application.usecase.ValidateTransactionUseCaseImpl;

@Service
@RequiredArgsConstructor
public class ValidateTransactionCommandHandler {

    private final ValidateTransactionUseCaseImpl validateTransactionUseCase;

    public void handle(ValidateTransactionCommand command) {
        validateTransactionUseCase.validateTransaction(command.transactionExternalId(), command.value());
    }
}
