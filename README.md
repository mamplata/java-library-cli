### Features Branch for Borrow Functionality

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
5. Borrow book
0. Exit

Choose option:
```
## 🧪 Output Example

```
Example usage:
Choose option: 5
Enter book ID to borrow: 2
Enter user ID: 1
📖 Book borrowed!
```

## 🛠 Tech Stack

* Java (JDK 11+)
* SQLite (via JDBC)
* SLF4J (no-op logging)
* Clean Architecture principles
* CLI-based interface

---

## 📌 Next Feature Branches

* `feature/return-book` – Return functionality
* `feature/transactions` – Immutable transaction logs
* `feature/unit-tests` – JUnit test coverage
