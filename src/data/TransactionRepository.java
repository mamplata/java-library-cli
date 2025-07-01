package data;

import domain.BorrowTransaction;
import java.util.List;

public interface TransactionRepository {
    void recordBorrow(int bookId, int userId);
    List<BorrowTransaction> getAllTransactions();
}
