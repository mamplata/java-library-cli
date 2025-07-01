package infra;

import data.BookRepository;
import domain.Book;

import java.sql.*;
import java.util.*;

public class SQLiteBookRepository implements BookRepository {
    private final String url;
    private final Connection sharedConn;

    // Production constructor (uses library.db)
    public SQLiteBookRepository() {
        this("jdbc:sqlite:library.db", null);
    }

    // Custom constructor (by URL)
    public SQLiteBookRepository(String dbUrl) {
        this(dbUrl, null);
    }

    // Testing constructor (shared in-memory connection)
    public SQLiteBookRepository(Connection sharedConn) {
        this(null, sharedConn);
    }

    private SQLiteBookRepository(String dbUrl, Connection sharedConn) {
        this.url = dbUrl;
        this.sharedConn = sharedConn;
        initializeSchemaAndSeed();
    }

    private boolean isShared() {
        return sharedConn != null;
    }

    private Connection getConnection() throws SQLException {
        return isShared() ? sharedConn : DriverManager.getConnection(url);
    }

    private void closeIfNeeded(Connection conn) {
        try {
            if (!isShared() && conn != null) conn.close();
        } catch (SQLException ignored) {}
    }

    private void initializeSchemaAndSeed() {
        Connection conn = null;
        try {
            conn = getConnection();
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS books (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        title TEXT NOT NULL,
                        available BOOLEAN NOT NULL DEFAULT 1
                    )
                """);

                // Seed only for file-based DBs
                if (!isShared() && !url.equals("jdbc:sqlite::memory:")) {
                    ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM books");
                    if (rs.next() && rs.getInt(1) == 0) {
                        stmt.executeUpdate("""
                            INSERT INTO books (title) VALUES 
                            ('The Hobbit'), 
                            ('1984'), 
                            ('Clean Code')
                        """);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Book DB error: " + e.getMessage());
        } finally {
            closeIfNeeded(conn);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection();
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {

                while (rs.next()) {
                    books.add(new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getBoolean("available")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeIfNeeded(conn);
        }
        return books;
    }

    @Override
    public void addBook(Book book) {
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO books (title, available) VALUES (?, 1)")) {
                stmt.setString(1, book.getTitle());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeIfNeeded(conn);
        }
    }

    @Override
    public void updateBook(Book book) {
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE books SET title = ? WHERE id = ?")) {
                stmt.setString(1, book.getTitle());
                stmt.setInt(2, book.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeIfNeeded(conn);
        }
    }

    @Override
    public void deleteBook(int id) {
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM books WHERE id = ?")) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeIfNeeded(conn);
        }
    }

    @Override
    public Book getBookById(int id) {
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM books WHERE id = ?")) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getBoolean("available")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeIfNeeded(conn);
        }
        return null;
    }
}
