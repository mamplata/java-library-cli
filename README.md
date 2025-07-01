### Features Branch for View Transaction Logs Functionality

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
6. Return book
7. View Transaction Logs
0. Exit

Choose option:
```
## 🧪 Output Example

```
Example usage:
Choose option: 7
📖 Borrow Logs:
 - [#1] Book ID: 2, User ID: 1, Borrowed: 2025-07-01 09:00:00

📥 Return Logs:
 - [#1] Book ID: 2, User ID: 1, Returned: 2025-07-01 09:05:00
```

## 🛠 Tech Stack

* Java (JDK 11+)
* SQLite (via JDBC)
* SLF4J (no-op logging)
* Clean Architecture principles
* CLI-based interface

---

## 📌 Next Feature Branches
* `feature/unit-tests` – JUnit test coverage
