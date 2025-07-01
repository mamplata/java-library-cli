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

    // Getters and toString()
}
