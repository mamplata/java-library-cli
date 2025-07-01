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

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getUserId() {
        return userId;
    }

    public String getDateBorrowed() {
        return dateBorrowed;
    }

    @Override
    public String toString() {
        return "BorrowTransaction{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", dateBorrowed='" + dateBorrowed + '\'' +
                '}';
    }
}
