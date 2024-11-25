package pe.yape.transaction.ms.infrastructure.adapters.in.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import pe.yape.transaction.ms.application.query.get.GetTransactionQuery;
import pe.yape.transaction.ms.application.query.get.GetTransactionQueryHandler;
import pe.yape.transaction.ms.infrastructure.adapters.in.rest.dto.TransactionResponse;
import pe.yape.transaction.ms.infrastructure.mapper.TransactionMapper;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class TransactionGraphQLController{

    private final GetTransactionQueryHandler getTransactionQueryHandler;
    private final TransactionMapper transactionMapper;

    @QueryMapping
    public TransactionResponse getTransaction(@Argument UUID transactionExternalId) {
        GetTransactionQuery query = new GetTransactionQuery(transactionExternalId);
        var transactionOptional = getTransactionQueryHandler.handle(query);
        return transactionOptional.map(transactionMapper::domainToResponse).orElse(null);
    }
}
