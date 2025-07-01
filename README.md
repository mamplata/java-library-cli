📚 Java CLI Library System

A simple command-line library management system built with Java and SQLite using Clean Architecture principles.  
Features are implemented incrementally using GitHub Pull Requests per feature.

✅ Features (as of `init-project` branch)

- Clean Architecture layout (app, domain, data, infra)
- SQLite database with:
  - Books table (auto-created and seeded)
  - Users table (auto-created and seeded with 3 users)
- Displays book list and user list in console

🗂 Project Structure

library-cli-system/
├── app/                  # CLI main entry point
├── domain/               # Entity classes (Book, User)
├── data/                 # Repository interfaces
├── infra/                # SQLite implementations
├── lib/                  # SQLite JDBC driver
├── library.db            # Auto-generated SQLite database
└── README.md             # This file

⚙️ Setup & Run

1. Install SQLite and Java (JDK 11+)

Make sure `javac` and `sqlite3` are available in your system path.

2. Download SQLite JDBC Driver

- Place `sqlite-jdbc-<version>.jar` in the `lib/` folder.  
  Example (used here): [sqlite-jdbc-3.45.1.0.jar](https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.45.1.0/sqlite-jdbc-3.45.1.0.jar)

3. Compile

bash
javac -cp ".:lib/sqlite-jdbc.jar" app/Main.java domain/*.java data/*.java infra/*.java

4. Run

bash
java -cp ".:lib/sqlite-jdbc.jar" app.Main

🧪 Output Example

📚 Books:
 - [1] The Hobbit (Available)
 - [2] 1984 (Available)
 - [3] Clean Code (Available)

👤 Users:
 - [1] Alice
 - [2] Bob
 - [3] Charlie

🛠 Tech Stack

* Java (JDK 11+)
* SQLite (via JDBC)
* CLI-based architecture
* Clean Architecture principles

📌 Next Feature Branches

* `feature/book-crud` – Add Create, Update, Delete for Books
* `feature/borrow-book` – Borrow functionality with transaction logging
* `feature/return-book` – Return functionality
* `feature/transactions` – Immutable transaction logs
* `feature/unit-tests` – JUnit test coverage
