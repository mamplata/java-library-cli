package infra;

import data.TransactionRepository;
import domain.BorrowTransaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteTransactionRepository implements TransactionRepository {
    private final String url = "jdbc:sqlite:library.db";

    public SQLiteTransactionRepository() {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Create borrow_transactions table if it doesn't exist
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS borrow_transactions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    book_id INTEGER NOT NULL,
                    user_id INTEGER NOT NULL,
                    date_borrowed TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (book_id) REFERENCES books(id),
                    FOREIGN KEY (user_id) REFERENCES users(id)
                )
            """);

        } catch (SQLException e) {
            System.out.println("Transaction DB error: " + e.getMessage());
        }
    }

    @Override
    public void recordBorrow(int bookId, int userId) {
        try (Connection conn = DriverManager.getConnection(url)) {
            conn.setAutoCommit(false);

            // Insert into borrow_transactions
            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO borrow_transactions (book_id, user_id) VALUES (?, ?)")) {
                stmt.setInt(1, bookId);
                stmt.setInt(2, userId);
                stmt.executeUpdate();
            }

            // Update books table: set available = 0
            try (PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE books SET available = 0 WHERE id = ?")) {
                stmt.setInt(1, bookId);
                stmt.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BorrowTransaction> getAllTransactions() {
        List<BorrowTransaction> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM borrow_transactions")) {

            while (rs.next()) {
                list.add(new BorrowTransaction(
                        rs.getInt("id"),
                        rs.getInt("book_id"),
                        rs.getInt("user_id"),
                        rs.getString("date_borrowed")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
