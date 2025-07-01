package infra;

import data.BookRepository;
import domain.Book;

import java.sql.*;
import java.util.*;

public class SQLiteBookRepository implements BookRepository {
    private final String url = "jdbc:sqlite:library.db";

    public SQLiteBookRepository() {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Create books table
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS books (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    available BOOLEAN NOT NULL DEFAULT 1
                )
            """);

            // Seed initial books only if table is empty
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM books");
            if (rs.next() && rs.getInt(1) == 0) {
                stmt.executeUpdate("INSERT INTO books (title) VALUES ('The Hobbit'), ('1984'), ('Clean Code')");
            }

        } catch (SQLException e) {
            System.out.println("Book DB error: " + e.getMessage());
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getBoolean("available")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    @Override
    public void addBook(Book book) {
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO books (title, available) VALUES (?, 1)")) {
            stmt.setString(1, book.getTitle());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateBook(Book book) {
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement("UPDATE books SET title = ? WHERE id = ?")) {
            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteBook(int id) {
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM books WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Book getBookById(int id) {
    try (Connection conn = DriverManager.getConnection(url);
         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books WHERE id = ?")) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Book(rs.getInt("id"), rs.getString("title"), rs.getBoolean("available"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


}
