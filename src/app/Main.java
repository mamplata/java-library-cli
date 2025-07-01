package app;

import infra.SQLiteBookRepository;
import infra.SQLiteUserRepository;
import infra.SQLiteTransactionRepository;
import usecase.BookService;
import usecase.BorrowService;
import usecase.ReturnService;
import usecase.TransactionService;
import data.UserRepository;
import domain.Book;
import domain.User;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var bookRepo = new SQLiteBookRepository(); // shared book repo
        var userRepo = new SQLiteUserRepository();
        var transactionRepo = new SQLiteTransactionRepository();
        
        // Handle services
        var bookService = new BookService(bookRepo);
        var borrowService = new BorrowService(transactionRepo, bookRepo);
        var returnService = new ReturnService(transactionRepo, bookRepo);
        var transactionService = new TransactionService(new SQLiteTransactionRepository());

        Scanner scanner = new Scanner(System.in); // For user input

        // Main CLI loop
        while (true) {
            // Display book menu
            System.out.println("\n📚 Book Menu:");
            System.out.println("1. List books");
            System.out.println("2. Add book");
            System.out.println("3. Update book");
            System.out.println("4. Delete book");
            System.out.println("5. Borrow book");
            System.out.println("6. Return book");
            System.out.println("7. View Transaction Logs");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            String choice = scanner.nextLine();

            // Handle user choice
            switch (choice) {
                case "1" -> {
                    // List all books
                    List<Book> books = bookService.listBooks();
                    for (Book b : books) System.out.println(" - " + b);
                }
                case "2" -> {
                    // Add a new book
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    bookService.addBook(title);
                    System.out.println("✅ Book added.");
                }
                case "3" -> {
                    // Update existing book title
                    System.out.print("Enter book ID to update: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    bookService.updateBook(id, newTitle);
                    System.out.println("✏️ Book updated.");
                }
                case "4" -> {
                    // Delete book by ID
                    System.out.print("Enter book ID to delete: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    bookService.deleteBook(id);
                    System.out.println("🗑 Book deleted.");
                }
                case "5" -> {
                  // Borrow book by ID
                    System.out.print("Enter book ID to borrow: ");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter user ID: ");
                    int userId = Integer.parseInt(scanner.nextLine());
                    boolean success = borrowService.borrowBook(bookId, userId);
                    System.out.println(success ? "📖 Book borrowed!" : "❌ Book is not available or doesn't exist.");
                }
                case "6" -> {
                    // Return book by id
                    System.out.print("Enter book ID to return: ");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter user ID: ");
                    int userId = Integer.parseInt(scanner.nextLine());
                    boolean success = returnService.returnBook(bookId, userId);
                    System.out.println(success ? "📥 Book returned!" : "❌ Book is already available or doesn't exist.");
                }
                case "7" -> {
                    // Return borrow and return logs
                    System.out.println("📖 Borrow Logs:");
                    for (var tx : transactionService.getBorrowLogs()) {
                        System.out.println(" - [#" + tx.getId() + "] Book ID: " + tx.getBookId()
                                + ", User ID: " + tx.getUserId() + ", Borrowed: " + tx.getDateBorrowed());
                    }
                
                    System.out.println("\n📥 Return Logs:");
                    for (var tx : transactionService.getReturnLogs()) {
                        System.out.println(" - [#" + tx.getId() + "] Book ID: " + tx.getBookId()
                                + ", User ID: " + tx.getUserId() + ", Returned: " + tx.getDateReturned());
                    }
                }
                case "0" -> {
                    // Exit the program
                    System.out.println("👋 Exiting...");
                    return;
                }
                default -> {
                    // Handle invalid input
                    System.out.println("❌ Invalid option.");
                }
            }
        }
    }
}
