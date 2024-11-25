package pe.yape.transaction.ms.application.query.get;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.yape.transaction.ms.application.usecase.GetTransactionUseCaseImpl;
import pe.yape.transaction.ms.domain.model.Transaction;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetTransactionQueryHandler {

    private final GetTransactionUseCaseImpl getTransactionUseCase;

    public Optional<Transaction> handle(GetTransactionQuery query){
        return getTransactionUseCase.getTransactionById(query.transactionId());
    }

}
