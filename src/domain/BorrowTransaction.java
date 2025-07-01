package domain;

public class BorrowTransaction {
    private int id;
    private int bookId;
    private int userId;
    private String dateBorrowed;

    public BorrowTransaction(int id, int bookId, int userId, String dateBorrowed) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.dateBorrowed = dateBorrowed;
    }

    // Getters and toString()
}
