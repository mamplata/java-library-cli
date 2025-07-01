package domain;

public class ReturnTransaction {
    private int id;
    private int bookId;
    private int userId;
    private String dateReturned;

    public ReturnTransaction(int id, int bookId, int userId, String dateReturned) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.dateReturned = dateReturned;
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

    public String getDateReturned() {
        return dateReturned;
    }

    @Override
    public String toString() {
        return "ReturnTransaction{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", dateReturned='" + dateReturned + '\'' +
                '}';
    }
}
