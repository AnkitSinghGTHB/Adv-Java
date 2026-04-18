# Activity 2: JDBC, Connection Pooling & Database Access

---

### **1. Differentiate between Statement, PreparedStatement, and CallableStatement. Compare them with respect to syntax, performance, security, and practical use cases.**

**Answer:**
In JDBC, these three interfaces are used to execute SQL queries, but they serve entirely different functional purposes based on the dynamic nature of queries and enterprise needs.

| Feature | `Statement` | `PreparedStatement` | `CallableStatement` |
| :--- | :--- | :--- | :--- |
| **Definition** | Used to execute **static SQL** queries. | Used to execute **pre-compiled** and parameterized SQL queries. | Used to execute **Stored Procedures** inside the database engine. |
| **Performance** | **Slower**. The query is compiled by the database *every time* it is executed. | **Faster**. Query compilation happens only once. Repeated executions just pass new parameters. | **Fastest**. The procedure is pre-compiled and lives on the DB server, saving network calls and execution overhead. |
| **Syntax** | `Statement stmt = con.createStatement();` | `PreparedStatement pstmt = con.prepareStatement("SELECT * from emp where id=?");` | `CallableStatement cstmt = con.prepareCall("{call myProcedure(?, ?)}");` |
| **Security** | Highly vulnerable to **SQL Injection** attacks as inputs are directly concatenated. | **Highly Secure**. Automatically escapes input characters, preventing SQL injection. | **Highly Secure**. Parameters are passed directly without parsing risks. |
| **Use Case** | Useful for **DDL statements** (`CREATE`, `ALTER`) or one-time batch queries. | The **industry standard** for dynamic `SELECT`, `INSERT`, `UPDATE`, `DELETE` operations where inputs come from users. | Used when complex business logic is written in the database (e.g., PL/SQL stored procedures) or when executing functions returning out parameters. |

---

### **2. What is connection pooling? Why is it required in enterprise database applications? Explain its working principle and advantages over normal JDBC connections.**

**Answer:**
**Connection Pooling** is a cache or a "pool" of pre-established, ready-to-use database connections maintained in memory. 

**Working Principle:**
Instead of constantly creating and tearing down connections, a Connection Pool manager creates a certain number of connections at startup.
1. When an application needs to talk to the DB, it **borrows** a connection from the pool.
2. The application performs its queries.
3. Once done, instead of closing the connection physically, it **returns it to the pool** (Logical Close) so another thread can reuse it.

**Why it is required & Advantages:**
*   **Performance Overhead Reduction:** Creating a physical database connection (via TCP/IP handshakes, authentication) is an extremely heavy and slow process. Pooling avoids this overhead entirely.
*   **Resource Management:** Limits the maximum number of concurrent database connections so the database server memory is not overloaded.
*   **Scalability:** Allows handling thousands of concurrent users with a small number of physical connections.

**Diagram:**
```text
  [App Thread 1] --->  (Borrows Conn) ---> [ CONNECTION POOL ] ---> [ Database ]
  [App Thread 2] --->  (Borrows Conn) ---> [ (Keeps 10 idle) ] ---> [ Database ]
  [App Thread 3] <--- (Returns Conn)  <--- [   connections   ]
```

---

### **3. A web application connects to the database thousands of times per minute. Explain why DriverManager-based connections may become inefficient and how DataSource with connection pooling solves the problem.**

**Answer:**
Using `DriverManager.getConnection()` for a web application receiving thousands of hits per minute is an architectural **anti-pattern**.

**Why DriverManager is Inefficient:**
Every time a user makes a request, `DriverManager` opens a new physical socket to the database, sends the user/password, authenticates, allocates memory, executes the query, and then closes everything down. The time spent simply *building* and *destroying* the connection would far exceed the time spent executing actual queries, resulting in extreme **latency and server crashes** under load.

**How DataSource Solves the Problem:**
`javax.sql.DataSource` is the preferred factory for connections.
*   **Built-in Pooling:** DataSource is implemented by Application Servers (Tomcat, WebLogic) or libraries (HikariCP, c3p0) to natively provide **Connection Pooling**.
*   **JNDI Lookup:** Instead of hardcoding database credentials in Java code, `DataSource` retrieves pool details from the server via JNDI (Java Naming and Directory Interface), decoupling the app code from the infrastructure.
*   **Logical vs Physical Close:** When `connection.close()` is called on a DataSource object, it simply returns to the pool, resulting in instantly available connections for thousands of rapid requests.

---

### **4. Explain with examples how SQL query results are mapped into Java objects. Discuss the role of getters like getInt(), getString(), getDate(), and the importance of correct type mapping.**

**Answer:**
When a `SELECT` query runs, JDBC returns a `ResultSet` object. This object acts like a cursor pointing to rows in the database table. Mapping involves pulling data from this `ResultSet` and setting it into a Java Object (Often called a POJO or DTO).

**The Role of Getters:**
SQL databases speak SQL types (VARCHAR, NUMBER, DATE), but Java speaks Java objects (`String`, `int`, `java.sql.Date`). The `ResultSet` provides specific getter methods to bridge this gap safely.

**Code Example Mapping SQL to a Java Object:**
```java
// Our Java Object
class Employee {
    int id;
    String name;
    java.sql.Date joinDate;
}

// ... inside a DAO method ...
PreparedStatement pstmt = con.prepareStatement("SELECT emp_id, emp_name, join_date FROM Employees");
ResultSet rs = pstmt.executeQuery();

List<Employee> employeeList = new ArrayList<>();

// Iterate through the Result Set
while(rs.next()) {
    Employee emp = new Employee();
    
    // EXAMINER NOTE: Mapping phase using type-safe getters
    // 1 allows column index, passing column name is safer for clarity.
    emp.id = rs.getInt("emp_id");          // Maps SQL INT to Java int
    emp.name = rs.getString("emp_name");   // Maps SQL VARCHAR to Java String
    emp.joinDate = rs.getDate("join_date");// Maps SQL DATE to java.sql.Date
    
    employeeList.add(emp); // Add mapped object to list
}
```

**Importance of Correct Type Mapping:**
If types are mismatched (e.g., trying to use `getInt()` on a string column), an `SQLException` is thrown. Also, precision matters; using `getDouble()` compared to `getBigDecimal()` affects financial calculations due to floating-point truncation.

---

### **5. Compare java.sql API and javax.sql API in detail. Highlight their major features, differences, and practical usage in enterprise applications.**

**Answer:**

| Feature Area | `java.sql` (Core JDBC) | `javax.sql` (JDBC Optional/Enterprise Package) |
| :--- | :--- | :--- |
| **Origin / Nature** | Introduced in JDK 1.1. Contains the core client-side JDBC API. | Introduced in JDBC 2.0 extension. Contains server-side API. |
| **Primary Focus** | Establishing basic database connectivity, executing queries, processing results. | Providing advanced enterprise-level database features. |
| **Connection Factory** | `DriverManager` | `DataSource` |
| **Connection Pooling** | Not supported inherently. | **Natively supported** via `ConnectionPoolDataSource`. |
| **Distributed Transactions** | Not supported. | **Supported** via `XADataSource` (Committing across multiple differing databases). |
| **ResultSets** | Basic scrollable/updatable `ResultSet`. | Introduces **`RowSet`**, a disconnected component architecture suitable for JavaBeans. |
| **Enterprise Usage** | Good for standalone console apps or small desktop software. | **Crucial for Modern Web & Enterprise apps** relying on application servers like Tomcat or Spring Boot. |

---

### **6. Evaluate the role of javax.sql in modern enterprise database programming. Why is it considered an improvement over basic JDBC usage?**

**Answer:**
The `javax.sql` package is the backbone of robust enterprise applications because it addresses the severe limitations of standard JDBC scaling.

**Why it is an improvement (Examiner Evaluation Points):**
1.  **Decoupling via JNDI & DataSource:** Basic JDBC requires hardcoding `com.mysql.cj.jdbc.Driver` and passwords. `javax.sql.DataSource` allows administrators to configure the DB external to the application (like in Tomcat's `context.xml`), allowing developers to simply lookup the resource via JNDI.
2.  **Scalability via Connection Pooling:** As evaluated earlier, avoiding `DriverManager` in favor of application-server managed pools drastically reduces latency.
3.  **Disconnected Architecture (RowSets):** `javax.sql.RowSet` allows database data to be cached in memory, the connection closed, and the data sent over a network (or to a JSP view page) without holding an open DB lock, freeing up server resources.
4.  **Distributed Transactions (Two-Phase Commit):** Modern microservices often need to update a MySQL database and an Oracle database simultaneously. The `javax.sql.XADataSource` allows both to be wrapped in a single, atomic enterprise transaction.
