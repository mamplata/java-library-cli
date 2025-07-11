package usecase;

import data.BookRepository;
import data.TransactionRepository;
import domain.Book;

public class BorrowService {
    private final TransactionRepository transactionRepo;
    private final BookRepository bookRepo;

    public BorrowService(TransactionRepository transactionRepo, BookRepository bookRepo) {
        this.transactionRepo = transactionRepo;
        this.bookRepo = bookRepo;
    }

    public boolean borrowBook(int bookId, int userId) {
        Book book = bookRepo.getBookById(bookId);
        if (book == null) return false;

        if (!book.isAvailable()) return false;

        transactionRepo.recordBorrow(bookId, userId);
        return true;
    }
}
