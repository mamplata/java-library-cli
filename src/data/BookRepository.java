package data;

import domain.Book;
import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();
}
