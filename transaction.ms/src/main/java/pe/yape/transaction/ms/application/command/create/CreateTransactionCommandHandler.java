package pe.yape.transaction.ms.application.command.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.yape.transaction.ms.application.usecase.CreateTransactionUseCaseImpl;
import pe.yape.transaction.ms.domain.model.Transaction;

@Service
@RequiredArgsConstructor
public class CreateTransactionCommandHandler {

    private final CreateTransactionUseCaseImpl createTransactionUseCase;

    public Transaction handle(CreateTransactionCommand command)
    {
        return createTransactionUseCase.createTransaction(
                command.accountExternalIdDebit(),
                command.accountExternalIdCredit(),
                command.transactionType(),
                command.value()
        );
    }

}
