package pe.yape.transaction.ms.infrastructure.adapters.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.yape.transaction.ms.application.command.create.CreateTransactionCommandHandler;
import pe.yape.transaction.ms.infrastructure.adapters.in.rest.dto.CreateTransactionRequest;
import pe.yape.transaction.ms.infrastructure.adapters.in.rest.dto.TransactionResponse;
import pe.yape.transaction.ms.infrastructure.mapper.TransactionMapper;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final CreateTransactionCommandHandler createTransactionCommandHandler;
    private final TransactionMapper transactionMapper;

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody CreateTransactionRequest request) {
       var command = transactionMapper.requestToCommand(request);
       var createdTransaction = createTransactionCommandHandler.handle(command);
       var response = transactionMapper.domainToResponse(createdTransaction);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
