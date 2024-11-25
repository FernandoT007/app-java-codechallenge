package pe.yape.transaction.ms.application.command.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.yape.transaction.ms.application.usecase.UpdateTransactionUseCaseImpl;

@Service
@RequiredArgsConstructor
public class UpdateTransactionStatusCommandHandler {

    private final UpdateTransactionUseCaseImpl updateTransactionUseCase;

    public void handle(UpdateTransactionStatusCommand command){
        updateTransactionUseCase.updateTransactionStatus(
                command.transactionExternalId(),
                command.status()
        );
    };
}
