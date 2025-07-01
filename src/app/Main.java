package app;

import infra.SQLiteBookRepository;
import infra.SQLiteUserRepository;
import data.BookRepository;
import data.UserRepository;
import domain.Book;
import domain.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepo = new SQLiteBookRepository();
        UserRepository userRepo = new SQLiteUserRepository();

        System.out.println("📚 Books:");
        for (Book book : bookRepo.getAllBooks()) {
            System.out.println(" - " + book);
        }

        System.out.println("\n👤 Users:");
        for (User user : userRepo.getAllUsers()) {
            System.out.println(" - " + user);
        }
    }
}
