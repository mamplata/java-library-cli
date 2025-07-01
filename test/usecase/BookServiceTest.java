package usecase;

import domain.Book;
import infra.SQLiteBookRepository;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {
    private Connection conn;
    private BookService bookService;

    @BeforeEach
    public void setUp() throws Exception {
        
        conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        bookService = new BookService(new SQLiteBookRepository(conn));
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    @Test
    public void testAddBook() {
        bookService.addBook("Test Book");
        List<Book> books = bookService.listBooks();
        assertEquals(1, books.size());
        assertEquals("Test Book", books.get(0).getTitle());
    }
}

