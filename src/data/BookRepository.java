package data;

import domain.Book;
import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(int id);
    Book getBookById(int id);
}
