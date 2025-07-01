package app;

import infra.SQLiteBookRepository;
import infra.SQLiteUserRepository;
import usecase.BookService;
import data.UserRepository;
import domain.Book;
import domain.User;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize services
        var bookService = new BookService(new SQLiteBookRepository()); // Handles book-related logic
        var userRepo = new SQLiteUserRepository(); // Initialized (not used here yet, will be used in future features)

        Scanner scanner = new Scanner(System.in); // For user input

        // Main CLI loop
        while (true) {
            // Display book menu
            System.out.println("\n📚 Book Menu:");
            System.out.println("1. List books");
            System.out.println("2. Add book");
            System.out.println("3. Update book");
            System.out.println("4. Delete book");
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
