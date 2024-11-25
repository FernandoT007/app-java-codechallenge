package pe.yape.transaction.ms.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.yape.transaction.ms.domain.enums.TransactionStatus;
import pe.yape.transaction.ms.domain.ports.in.UpdateTransactionUseCase;
import pe.yape.transaction.ms.domain.ports.out.TransactionRepository;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateTransactionUseCaseImpl implements UpdateTransactionUseCase {

    private final TransactionRepository transactionRepository;
    @Override
    public void updateTransactionStatus(UUID transactionId, TransactionStatus newStatus) {
        transactionRepository.findById(transactionId).ifPresentOrElse(
                transaction -> {
                    transaction.updateStatus(newStatus);
                    transactionRepository.save(transaction);
                },
                () -> { throw new IllegalArgumentException("Transaction not found with ID: " + transactionId); }
        );
    }
}
