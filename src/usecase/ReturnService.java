package usecase;

import data.BookRepository;
import data.TransactionRepository;
import domain.Book;

public class ReturnService {
    private final TransactionRepository transactionRepo;
    private final BookRepository bookRepo;

    public ReturnService(TransactionRepository transactionRepo, BookRepository bookRepo) {
        this.transactionRepo = transactionRepo;
        this.bookRepo = bookRepo;
    }

    public boolean returnBook(int bookId, int userId) {
        Book book = bookRepo.getBookById(bookId);
        if (book == null) return false;

        if (book.isAvailable()) return false;

        transactionRepo.recordReturn(bookId, userId);
        return true;
    }
}
