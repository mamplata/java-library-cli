# 🧪 Testing Guide

This document provides instructions for compiling and running unit tests for the **Java CLI Library Management System** using **JUnit 5** and **SQLite (in-memory)**.

---

## 📁 Directory Structure (Relevant to Testing)

```
lib/                    # JAR dependencies
test/                   # Unit test source files
test-out/               # Compiled test class output
src/                    # Main source code
```

---

## 🛠 Requirements

Make sure the following JAR files are present in the `lib/` folder:

* `junit-platform-console-standalone-1.10.2.jar`
* `sqlite-jdbc-3.45.1.0.jar`
* `slf4j-api-2.0.13.jar`
* `slf4j-nop-2.0.13.jar`

---

## 🧾 Steps to Compile & Run Tests

### 1. Compile the test files

```bash
javac -cp "lib/*:src" -d test-out $(find test -name "*.java")
```

This compiles all test source files inside `test/` and outputs the compiled `.class` files to the `test-out/` directory.

### 2. Run the tests

```bash
java -cp "lib/*:test-out" org.junit.platform.console.ConsoleLauncher --scan-class-path
```

This scans the `test-out/` directory and executes all tests using the JUnit Platform Console Launcher.

---

## ✅ What It Tests

Currently tested features:

* `BookServiceTest`: validates adding and listing books using an isolated in-memory database (`jdbc:sqlite::memory:`).

---

## 💡 Notes

* Each test uses its own SQLite in-memory database to ensure isolation.
* The `SLF4J` JARs are required because the SQLite JDBC driver uses SLF4J internally.
* You can write more tests in `test/usecase/` and reuse the in-memory database strategy.

---
