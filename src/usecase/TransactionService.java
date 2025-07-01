package usecase;

import data.TransactionRepository;
import domain.BorrowTransaction;
import domain.ReturnTransaction;

import java.util.List;

public class TransactionService {
    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public List<BorrowTransaction> getBorrowLogs() {
        return repo.getAllBorrowTransactions();
    }

    public List<ReturnTransaction> getReturnLogs() {
        return repo.getAllReturnTransactions();
    }
}
