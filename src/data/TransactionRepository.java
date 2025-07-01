package data;

import domain.BorrowTransaction;
import domain.ReturnTransaction;
import java.util.List;

public interface TransactionRepository {
    void recordBorrow(int bookId, int userId);
    void recordReturn(int bookId, int userId);
    List<BorrowTransaction> getAllBorrowTransactions();
    List<ReturnTransaction> getAllReturnTransactions();

}
