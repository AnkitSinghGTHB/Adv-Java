# Module 3 (Continued): JDBC ResultSet and MetaData

While `Statement` and `DriverManager` help establish connections and execute queries, `ResultSet` and `MetaData` are crucial for handling the returned data effectively.

---

## 1. The `ResultSet` Interface
**Definition:**
The `ResultSet` interface represents the result set of a database query. Whenever you execute a `SELECT` statement in JDBC, the returned rows are structured within a `ResultSet` object. It acts as an iterator that points to one row of data at a time.

**Key Concepts:**
*   By default, a `ResultSet` object is not updatable and has a cursor that moves forward only.
*   You use the `next()` method to move the cursor to the next row. Initially, it is positioned *before* the first row.

**Example Usage:**
```java
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM students");

while (rs.next()) {
    // Accessing columns by name
    int id = rs.getInt("student_id");
    String name = rs.getString("name");
    System.out.println(id + " - " + name);
}
```

---

## 2. Prepared Statements (`PreparedStatement`)
*(Highly crucial for security and efficiency)*

Instead of just using `Statement`, modern Java applications use the `PreparedStatement` interface (a subinterface of `Statement`). 

**Why use PreparedStatement?**
1.  **Security (Prevents SQL Injection):** It automatically escapes inputs, preventing hackers from tampering with the query string.
2.  **Performance:** The query is precompiled by the database. If you need to run the same query multiple times with different parameters, it executes much faster.

**Example:**
```java
// Using '?' as placeholders
String query = "SELECT * FROM users WHERE username = ? AND password = ?";
PreparedStatement pstmt = con.prepareStatement(query);

// Binding Values
pstmt.setString(1, "admin");
pstmt.setString(2, "secret123");

ResultSet rs = pstmt.executeQuery();
```

---

## 3. Metadata in JDBC
*(Frequently asked in 5-mark short notes)*

Metadata is simply defined as **"Data about Data"**. 
In JDBC, it describes the internal structure, properties, and constraints of your data and databases. There are two primary types of Metadata in JDBC:

### A. ResultSetMetaData
Provides information about the data contained *within a specific ResultSet*. 
*   **Use Case:** Useful when you execute a query dynamically (e.g., in a GUI tool) and don't know in advance how many columns will be returned or what their data types are.
*   **Key Methods:** `getColumnCount()`, `getColumnName(int column)`, `getColumnTypeName(int column)`.

**Example:**
```java
ResultSetMetaData rsmd = rs.getMetaData();
int columnCount = rsmd.getColumnCount();
System.out.println("Total Columns: " + columnCount);
System.out.println("First Column Name: " + rsmd.getColumnName(1));
```

### B. DatabaseMetaData
Provides comprehensive information about the database itself as a whole, independent of any specific table.
*   **Use Case:** Used by database administration tools or ORM frameworks (like Hibernate) to deduce the database dialect, supported functions, limits, and underlying schema.
*   **Key Methods:** `getDriverName()`, `getDatabaseProductName()`, `getDatabaseProductVersion()`, `getTables()`.

**Example:**
```java
DatabaseMetaData dbmd = con.getMetaData();
System.out.println("Driver: " + dbmd.getDriverName());
System.out.println("Database Product: " + dbmd.getDatabaseProductName());
```

---

## 4. Typical Exam Questions ("Test Me")

### 2 Mark Questions:
1. What is the difference between `Statement` and `PreparedStatement`?
2. What is Metadata in context to JDBC?

### 5 Mark Questions:
1. Explain `ResultSetMetaData` and `DatabaseMetaData` with an example code snippet.
2. Why is `PreparedStatement` preferred over `Statement`? How do you prevent SQL injection in Java?

### 10 Mark Questions:
1. Write a complete JDBC application that retrieves student details from a database using a `PreparedStatement`. Extract the metadata of the Resultset and print the number of columns and their names before printing the actual data rows.
