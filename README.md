### Features Branch for Book CRUD Functionality

### Run
```bash
java -cp "lib/*:out" app.Main
```

## 🧪 CLI Menu Example

```
📚 Book Menu:
1. List books
2. Add book
3. Update book
4. Delete book
0. Exit

Choose option:
```
## 🧪 Output Example

```
Example usage:
Choose option: 2
Enter book title: Java for Beginners
✅ Book added.

Choose option: 1
[1] The Hobbit (Available)

[2] 1984 (Available)

[3] Clean Code (Available)

[4] Java for Beginners (Available)
```

## 🛠 Tech Stack

* Java (JDK 11+)
* SQLite (via JDBC)
* SLF4J (no-op logging)
* Clean Architecture principles
* CLI-based interface

---

## 📌 Next Feature Branches

* `feature/borrow-book` – Borrow functionality with transaction logging
* `feature/return-book` – Return functionality
* `feature/transactions` – Immutable transaction logs
* `feature/unit-tests` – JUnit test coverage
