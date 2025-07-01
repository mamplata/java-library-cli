package usecase;

import data.TransactionRepository;

public class BorrowService {
    private final TransactionRepository repo;

    public BorrowService(TransactionRepository repo) {
        this.repo = repo;
    }

    public void borrowBook(int bookId, int userId) {
        repo.recordBorrow(bookId, userId);
    }
}
