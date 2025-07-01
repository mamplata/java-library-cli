### Java CLI Library Management System

A simple Java command-line Library Management System built using Clean Architecture principles and an SQLite database.

## Features

- 📚 Manage books (add, list, update, delete)
- 🔄 Borrow and return books
- 🧾 Immutable transaction logs (borrows and returns)
- 👤 Predefined users
- 🧪 Unit tested core logic using JUnit and in-memory SQLite

## Tech Stack

- Java 17+
- SQLite (via `sqlite-jdbc`)
- JUnit 5 (Jupiter)
- Clean Architecture (Domain, Use Case, Infra, App layers)

## Project Structure

```

src/
├── app            # Main entry point
├── domain         # Entity models (Book, User, Transaction)
├── usecase        # Business logic (Services)
├── data           # Repository interfaces
├── infra          # SQLite implementations
test/
└── usecase        # Unit tests

````

## Setup

### 1. Compile the app

```bash
javac -cp "lib/*" -d out $(find src -name "*.java")
````

### 2. Run the app

```bash
java -cp "out:lib/*" app.Main
```

### 3. Run tests

```bash
javac -cp "lib/*:src" -d test-out $(find test -name "*.java")
java -cp "lib/*:test-out" org.junit.platform.console.ConsoleLauncher   --scan-class-path
```

## Sample Users

Currently, users are prepopulated and fixed in code. Book borrowing and returning assumes user context from internal logic.

## Notes

* The test uses `jdbc:sqlite::memory:` with a shared connection to ensure schema persists during test execution.
* Clean architecture allows you to swap out infra (e.g., use MySQL or file storage) without changing core logic.
