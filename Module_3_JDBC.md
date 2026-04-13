# Module 3: Java Database Connectivity (JDBC)

JDBC is an interface between a Java Application and a backend Database. It translates Java queries into native SQL commands to communicate with the DB.

---

## 1. JDBC Architecture

The JDBC Architecture consists of two main layers:
1. **JDBC API (Application Layer)**: Provides the application-to-JDBC Manager connection. Interfaces include `Connection`, `Statement`, `ResultSet`, `RowSet`.
2. **JDBC Driver API (Driver Layer)**: Provides the JDBC Manager-to-Driver connection.

### ODBC vs JDBC
- **ODBC (Open Database Connectivity)**: Formulated in 1992 by Microsoft. It's a general API standard built primarily using a C-based API. Highly platform-dependent, and generally faster.
- **JDBC**: Formulated in 1997 by Sun Microsystems. specifically designed for Java applications. It is object-oriented, utilizing the Java Virtual Machine, rendering it completely platform-independent.

### `java.sql` vs `javax.sql` API

### 📌 Activity Question: Compare `java.sql` API and `javax.sql` API in detail.
**Question:** Compare java.sql API and javax.sql API in detail. Highlight their major features, differences, and practical usage in enterprise applications. Evaluate the role of javax.sql in modern enterprise database programming.

**Solution/Explanation:**
- **`java.sql`**: The core JDBC API package used for basic database tasks. Features include fundamental interfaces heavily utilized in client-side applications (`DriverManager`, `Connection`, `Statement`, `ResultSet`). Connects strictly via DriverManager string endpoints.
- **`javax.sql`**: An extension of the JDBC API designed mainly for server-side enterprise applications. Major improvements over basic JDBC include **DataSources** (bypassing the slow DriverManager), **Connection Pooling**, and **Distributed Transactions** (XA connections). 
- **Role in Modern Enterprises**: Modern Java EE systems inherently rely on configuring `DataSource` instances at the Application Server level rather than hardcoding DB URLs. Connection pooling manages memory overhead significantly better under massive load, making `javax.sql` the golden standard.

---

## 2. JDBC Drivers
A JDBC Driver translates Java methods to database-specific calls. There are 4 types:

1. **Type 1: JDBC-ODBC Bridge Driver**: Converts JDBC calls into ODBC function calls. Requires the ODBC driver installed on the client machine. Degrades performance and is no longer recommended.
2. **Type 2: Native-API Driver (Partially Java)**: Uses client-side libraries of the database to translate calls into the database's native API. Requires vendor software on the client machine.
3. **Type 3: Network Protocol Driver (Fully Java)**: Uses middleware (application server) that takes JDBC calls and indirectly translates them. Avoids client-side library requirements but adds complexity in middle-tier configurations.
4. **Type 4: Thin Driver (Fully Java)**: Converts calls directly into the vendor-specific database protocol natively without middleware. Requires no additional software and performs optimally. *This is universally preferred.*

---

## 3. Seven Steps to the JDBC Connection

1. **Import the Package**: `import java.sql.*`
2. **Load and Register the Driver**: `Class.forName("com.mysql.cj.jdbc.Driver");`
3. **Establish Connection**: `DriverManager.getConnection(url, username, password);`
4. **Create a Statement**: `Statement stmt = con.createStatement();`
5. **Execute the Statement**: `ResultSet rs = stmt.executeQuery("SELECT * FROM students");`
6. **Retrieve the Result**: Process row-by-row using `while(rs.next())`.
7. **Close the Connection**: Release resources securely `con.close();`

---

## 4. Statements and Connection Pooling

The `Statement` interface carries out static SQL queries. In an enterprise system, optimizing these statements is crucial.

### 📌 Activity Question: Differentiate between Statements
**Question:** Differentiate between Statement, PreparedStatement, and CallableStatement. Compare them with respect to syntax, performance, security, and practical use cases.

**Solution/Explanation:**
- **`Statement`**: 
    - *Syntax*: Hardcoded strings: `stmt.executeQuery("SELECT * FROM users WHERE id=" + id);`
    - *Performance*: Slow; query is compiled every time.
    - *Security*: Highly vulnerable to SQL Injection.
    - *Use Case*: Queries ran strictly once without user input.
- **`PreparedStatement`**: 
    - *Syntax*: Uses parameterized queries: `pstmt.setInt(1, id);`
    - *Performance*: Fast; pre-compiled by the database. 
    - *Security*: Protects completely against SQL Injection.
    - *Use Case*: Repetitive queries carrying user inputs (e.g., login screens, search filters).
- **`CallableStatement`**: 
    - *Syntax*: Executes stored procedures: `cstmt.prepareCall("{call get_user_data(?)}");`
    - *Performance*: Extremely fast, as logic runs natively on the database server.
    - *Security*: Safest approach; abstracts DB schema from the app layer.
    - *Use Case*: Complex business logic that operates directly against the backing data.

### 📌 Activity Question: Connection Pooling and DataSource
**Question:** What is connection pooling? Explain why DriverManager-based connections may become inefficient for thousands of requests and how DataSource with connection pooling solves the problem.

**Solution/Explanation:**
- **Problem**: Opening and closing a DB connection via `DriverManager.getConnection()` involves heavy network latency and DB authorization overhead. Doing this thousands of times per minute freezes applications.
- **Connection Pooling**: Maintaining a "pool" (cache) of alive, readied database connections inside application memory. When a client requests a connection, they "borrow" one from the pool.
- **Working Principle (DataSource)**: Handled by `javax.sql.DataSource`. Instead of closing connections, `.close()` returns the connection gracefully back to the pool to be reused by the next thread. This dramatically reduces resource exhaustion and maintains scaling speeds.

---

## 5. ResultSets and Datatype Mapping

A `ResultSet` represents the rows of data queried from the database. 

### 📌 Activity Question: SQL to Java Type Mapping
**Question:** Explain with examples how SQL query results are mapped into Java objects. Discuss the role of getters like `getInt()`, `getString()`, `getDate()`.

**Solution/Explanation:**
Data types in SQL natively differ from Java properties (e.g., SQL's `VARCHAR` vs Java's `String`, or SQL's `DATETIME` vs `java.sql.Date`). 
- When iterating through a `ResultSet`, we must extract each column to build our native Java object to prevent ClassCastExceptions. 
- Using methods like `getInt("id")`, the JDBC driver automatically parses the binary database integer into a 32-bit Java `int`.

```java
import java.sql.*;

public class ResultSetMapping {
    public static void main(String[] args) {
        // Establishing connection using Try-with-resources
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/campusdb", "root", "password");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT emp_id, emp_name, join_date FROM employees")) {

            while (rs.next()) {
                // Correct Type Mapping
                int id = rs.getInt("emp_id");             // Maps SQL INT to Java int
                String name = rs.getString("emp_name");   // Maps SQL VARCHAR to Java String
                Date joinDate = rs.getDate("join_date");  // Maps SQL DATE to java.sql.Date

                System.out.println("ID: " + id + ", Name: " + name + ", Joined: " + joinDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```
**Expected Terminal Output:**
```text
ID: 101, Name: John Doe, Joined: 2022-05-14
ID: 102, Name: Jane Smith, Joined: 2023-01-20
ID: 103, Name: Ada Lovelace, Joined: 2024-03-05
```

---

## 6. Metadata Interfaces

Metadata describes the structural properties of your data.
- **ResultSetMetaData**: Describes columns inside a query layout (e.g., column names, number of columns). Can be fetched via `rs.getMetaData()`.
- **DatabaseMetaData**: Describes the database capacities (e.g., product version, supported functions, limits, driver username). Fetched via `con.getMetaData()`.
