package usecase;

import data.BookRepository;
import domain.Book;

import java.util.List;

public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> listBooks() {
        return repo.getAllBooks();
    }

    public void addBook(String title) {
        repo.addBook(new Book(0, title, true));
    }

    public void updateBook(int id, String newTitle) {
        repo.updateBook(new Book(id, newTitle, true));
    }

    public void deleteBook(int id) {
        repo.deleteBook(id);
    }
}
