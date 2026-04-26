# Advanced Java Solutions - Part 4 (Q16 to Q20)

---

## Question 16: Write a Java program to demonstrate event handling using ActionListener, MouseListener, or KeyListener.

**Topic Introduction: Event Handling in Java using Listeners.**

1. **Topic Introduction**: Event handling enables interactive applications. In this example, we demonstrate `ActionListener`, `MouseListener`, and `KeyListener` operating simultaneously.
2. **Action Event Context**: `ActionListener` specifically triggers when a button is clicked or "Enter" is pressed in a text field, executing `actionPerformed()`.
3. **Mouse Event Context**: `MouseListener` responds to diverse mouse interactions like clicking, pressing, releasing, entering, and exiting a component's graphical boundary.
4. **Key Event Context**: `KeyListener` captures raw keyboard inputs, responding when keys are pressed, typed, or released while a component has focus.
5. **UI Setup**: The program utilizes a `JFrame` containing a `JButton` (for Action), a `JPanel` (for Mouse), and a `JTextField` (for Key events).
6. **Interface Implementation**: The `main` class or dedicated inner classes must implement these interfaces, providing concrete bodies for every abstract method defined in them.
7. **Adapter Classes**: Alternatively, `MouseAdapter` and `KeyAdapter` can be used to avoid writing blank methods, though we demonstrate direct interface implementation here.
8. **Registering Listeners**: Listeners are attached via `addActionListener()`, `addMouseListener()`, and `addKeyListener()` to their respective UI components.
9. **Event Propagation**: When an event occurs, the OS passes it to the JVM, which packages it into an Event Object and dispatches it to the registered listener.
10. **Action Output**: Clicking the button prints "Button Clicked!" to the console.
11. **Mouse Output**: Hovering over the panel prints "Mouse Entered!", and clicking it prints "Mouse Clicked!".
12. **Key Output**: Typing into the text field captures each keystroke, printing the pressed character to the console dynamically.

```text
  [USER ACTION] -----> [COMPONENT] -----> [LISTENER EXECUTED]
  Mouse Click   -----> JPanel      -----> mouseClicked(e)
  Key Press     -----> JTextField  -----> keyPressed(e)
  Button Click  -----> JButton     -----> actionPerformed(e)
```

```java
// Explanation: Multiple Event Listeners Demo   //
import java.awt.*; import java.awt.event.*;     // Imports
import javax.swing.*;                           // Swing
                                                //
public class MultiEventDemo {                   // Main class
    public static void main(String[] args) {    // Program entry
        JFrame f = new JFrame("Events Demo");   // Main window
        f.setLayout(new FlowLayout());          // Set Layout
                                                //
        JButton btn = new JButton("Click Me");  // Action component
        btn.addActionListener(e ->              // Action Listener
            System.out.println("Action fired!"));// Lambda handler
                                                //
        JTextField tf = new JTextField(15);     // Key component
        tf.addKeyListener(new KeyAdapter() {    // Key Listener
            public void keyTyped(KeyEvent e) {  // Method override
                System.out.println("Key: " + e.getKeyChar());
            }                                   //
        });                                     //
                                                //
        JPanel p = new JPanel();                // Mouse component
        p.setBackground(Color.RED);             // Set color
        p.setPreferredSize(new Dimension(50,50));// Set size
        p.addMouseListener(new MouseAdapter() { // Mouse Listener
            public void mouseEntered(MouseEvent e) { // Hover event
                System.out.println("Mouse Entered!");// Console log
            }                                   //
        });                                     //
                                                //
        f.add(btn); f.add(tf); f.add(p);        // Add to frame
        f.setSize(300, 200); f.setVisible(true);// Display
    }                                           //
}                                               //
```

---

## Question 17: List and explain different types of JDBC drivers used for RDBMS systems.

**Topic Introduction: Different types of JDBC Drivers.**

1. **Topic Introduction**: A JDBC driver is a software component that enables a Java application to interact with a database, translating standard JDBC calls into database-specific protocols.
2. **Four Driver Types**: Java defines exactly four architectural types of JDBC drivers, ranging from Type 1 (legacy) to Type 4 (modern).
3. **Type 1: JDBC-ODBC Bridge**: Acts as a bridge, translating JDBC method calls into ODBC function calls. It requires ODBC drivers installed on the client machine.
4. **Type 1 Status**: It is highly platform-dependent, slow due to translation overhead, and was officially removed from Java starting in JDK 8.
5. **Type 2: Native-API Driver**: Converts JDBC calls into native C/C++ API calls specific to the database (like Oracle Call Interface).
6. **Type 2 Limitations**: Faster than Type 1, but still requires database-specific native libraries to be installed locally on every client machine.
7. **Type 3: Network Protocol Driver**: Follows a 3-tier approach. The driver translates JDBC calls into a middleware vendor's protocol, which a middleware server translates to database calls.
8. **Type 3 Advantages**: Pure Java, completely database-independent on the client side, and excellent for complex enterprise network architectures.
9. **Type 4: Thin Driver (Native Protocol)**: Completely written in Java, it directly converts JDBC calls into the specific network protocol understood by the database.
10. **Type 4 Advantages**: It communicates directly via sockets, requires no client-side native libraries or middleware, and is platform-independent.
11. **Performance**: Type 4 is generally the highest performing and most commonly used driver today (e.g., MySQL Connector/J).
12. **Selection Criteria**: Use Type 4 for modern web apps; Type 3 for 3-tier enterprise architectures; avoid Type 1 and 2 entirely for modern development.

```text
  [Type 1] Java App -> JDBC-ODBC -> ODBC -> DB
  [Type 2] Java App -> Native API -> DB Client Lib -> DB
  [Type 3] Java App -> Middleware Server -> DB
  [Type 4] Java App -> Direct Socket Protocol -> DB (Best)
```

```java
// Explanation: Registering a Type 4 Driver     //
import java.sql.*;                              // Import SQL package
                                                //
public class DriverDemo {                       // Main class
    public static void main(String[] args) {    // Program entry point
        try {                                   // Start exception handling
            // Note: Modern JDBC (Type 4)       // Automatically loads drivers
            // via Service Provider Interface,  // making Class.forName optional
            // but historically it looked like: //
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL Driver
            System.out.println("Driver Loaded!");// Success message
                                                //
            // Establish Connection             //
            String url = "jdbc:mysql://localhost/testdb"; // DB URL string
            String user = "root";               // Database username
            String pass = "password";           // Database password
            Connection con = DriverManager.getConnection(url, user, pass);
                                                //
            System.out.println("Connected!");   // Print success
            con.close();                        // Close resource
        } catch (Exception e) {                 // Catch SQL/Class errors
            e.printStackTrace();                // Print error trace
        }                                       //
    }                                           //
}                                               //
```

---

## Question 18: Explain the java.sql API and its key interfaces such as Connection, Statement, and ResultSet.

**Topic Introduction: `java.sql` API and Key Interfaces.**

1. **Topic Introduction**: The `java.sql` package contains the core JDBC API, providing the foundational interfaces and classes for database access and processing.
2. **API Architecture**: The API strictly relies on interfaces rather than concrete classes, allowing different database vendors to provide their own underlying implementations.
3. **The DriverManager Class**: This is the primary class (not interface) that manages a list of database drivers and establishes the initial connection using a given URL.
4. **The Connection Interface**: Represents a physical session with the database. It is created by the `DriverManager` and serves as a factory for statement objects.
5. **Connection Role**: It acts as the gateway to the database, managing transactions (commit/rollback) and executing stored procedures.
6. **The Statement Interface**: Created by the `Connection` object, it is used to execute standard, static SQL queries (like `SELECT * FROM table`) without parameters.
7. **Statement Execution**: It uses `executeQuery()` for SELECT statements (returning a ResultSet) and `executeUpdate()` for INSERT/UPDATE/DELETE (returning an int).
8. **The PreparedStatement Interface**: An extension of `Statement` used for executing pre-compiled, parameterized SQL queries efficiently and securely.
9. **The CallableStatement Interface**: An extension used specifically to execute complex stored procedures directly within the database engine.
10. **The ResultSet Interface**: Represents the tabular dataset produced by executing a SELECT query. It acts conceptually like a database cursor pointing to the current row.
11. **ResultSet Navigation**: It uses methods like `next()` to move through rows sequentially and `getInt()`, `getString()` to extract data from specific columns.
12. **Resource Management**: All `Connection`, `Statement`, and `ResultSet` objects must be explicitly closed (or used in try-with-resources) to prevent catastrophic memory and connection leaks.

```text
  [DriverManager] 
         | (creates)
   [Connection] 
         | (creates)
   [Statement] / [PreparedStatement]
         | (executes query, returns)
   [ResultSet]
```

```java
// Explanation: Java SQL API Interfaces Demo    //
import java.sql.*;                              // Import SQL package
                                                //
public class ApiDemo {                          // Main class
    public static void main(String[] args) {    // Program entry point
        String url = "jdbc:h2:mem:test";        // In-memory DB URL
        try (                                   // Try-with-resources block
            // 1. Connection Interface          //
            Connection con = DriverManager.getConnection(url, "sa", "");
            // 2. Statement Interface           //
            Statement stmt = con.createStatement(); 
        ) {                                     //
            // Setup dummy data                 //
            stmt.execute("CREATE TABLE Users(ID INT, Name VARCHAR(50))");
            stmt.execute("INSERT INTO Users VALUES(1, 'Alice')");
                                                //
            // 3. ResultSet Interface           //
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
            while (rs.next()) {                 // Loop through records
                int id = rs.getInt("ID");       // Extract ID
                String name = rs.getString("Name");// Extract Name
                System.out.println(id + " : " + name); // Print data
            }                                   //
            rs.close();                         // Explicit close for RS
        } catch (SQLException e) {              // Catch DB errors
            e.printStackTrace();                // Print error details
        }                                       //
    }                                           //
}                                               //
```

---

## Question 19: What is connection pooling? Explain its advantages and how it is implemented using the javax.sql package.

**Topic Introduction: Connection Pooling Using `javax.sql`.**

1. **Topic Introduction**: Connection pooling is an optimization technique where a cache (pool) of pre-established, ready-to-use database connections is maintained in memory.
2. **The Bottleneck**: Creating a fresh database connection is a highly expensive and slow operation involving network handshakes, authentication, and memory allocation.
3. **How Pooling Works**: Instead of closing a connection when an application finishes its task, the connection is returned to the pool, kept alive, and reused by the next user.
4. **Advantage 1: Performance**: Reusing existing connections drastically reduces the time required to execute queries, significantly improving application response times.
5. **Advantage 2: Scalability**: It prevents the database server from being overwhelmed by simultaneous connection requests during high-traffic spikes.
6. **Advantage 3: Resource Management**: Administrators can strictly limit the maximum number of active connections, preventing the database from crashing due to resource exhaustion.
7. **The `javax.sql` Package**: Introduced as a standard extension, this package provides the `DataSource` interface, which is the modern preferred alternative to `DriverManager`.
8. **The `DataSource` Interface**: A `DataSource` object is a factory for connections. When configured for pooling, calling `getConnection()` simply retrieves an existing connection from the pool.
9. **JNDI Integration**: In enterprise applications (Java EE), DataSources are typically configured at the application server level and retrieved dynamically using JNDI (Java Naming and Directory Interface).
10. **Third-Party Implementations**: Java provides the interfaces, but libraries like HikariCP, Apache DBCP, or C3P0 provide the actual high-performance pooling logic.
11. **Connection Lifecycle**: `Pool -> Checkout -> Execute Query -> Close() -> Returns to Pool`. Note: Calling `close()` on a pooled connection does not sever the network link.
12. **Configuration Parameters**: Administrators can tune the pool by setting `maxActive` (maximum connections allowed), `minIdle` (minimum kept alive), and `maxWait` timeouts.

```text
  [App Request 1] --(getConnection)--> [ CONNECTION POOL ]
                                       | [Con 1] (in use)|
  [App Request 2] --(getConnection)--> | [Con 2] (in use)|
                                       | [Con 3] (idle)  |
                                       +-----------------+
  (close() returns connection to pool instead of destroying it)
```

```java
// Explanation: Connection Pooling Concept      //
// Note: HikariCP library required via Maven    //
import javax.sql.DataSource;                    // Standard Java interface
import java.sql.*;                              // SQL imports
                                                //
public class PoolDemo {                         // Main class
    public static void main(String[] args) {    // Program entry point
        // In real project, add HikariCP to pom.xml or build.gradle
        // HikariConfig config = new HikariConfig();
        // config.setJdbcUrl("jdbc:mysql://localhost/db");
        // config.setUsername("root");           // Set username
        // config.setPassword("pwd");            // Set password
        // config.setMaximumPoolSize(10);        // Max 10 connections
        // HikariDataSource ds = new HikariDataSource(config);
                                                //
        // Usage pattern (key concept):         //
        // try (Connection con = ds.getConnection()) { // Borrow
        //     Statement s = con.createStatement();
        //     ResultSet rs = s.executeQuery("SELECT * FROM Emp");
        //     while(rs.next())                 //
        //         System.out.println(rs.getString(1));
        // }                                    // .close() returns to pool!
                                                //
        // Conceptual simulation of pool flow:  //
        System.out.println("1. Pool created (maxSize=10)");
        System.out.println("2. getConnection() -> borrows from pool");
        System.out.println("3. Execute queries using borrowed connection");
        System.out.println("4. .close() -> returns connection to pool");
        System.out.println("5. Next request reuses same connection (fast!)");
    }                                           //
}                                               //
```

---

## Question 20: What is JDBC? Explain its role in connecting Java applications with relational databases.

**Topic Introduction: JDBC and its role in connecting Java to Relational Databases.**

1. **Topic Introduction**: JDBC (Java Database Connectivity) is a standard Java API that provides a uniform interface to connect and execute queries against various relational databases.
2. **Platform Independence**: Just as Java provides "Write Once, Run Anywhere" for code, JDBC provides "Write Once, Access Any Database" for database operations.
3. **The Primary Role**: Its primary role is to act as a universal translator between the object-oriented Java application and the tabular SQL-based relational database management system (RDBMS).
4. **Decoupling Vendor Logic**: By utilizing JDBC, developers write standard SQL and API calls without worrying about the proprietary network protocols used by Oracle, MySQL, or PostgreSQL.
5. **Step 1: Driver Registration**: The process begins by loading the specific database driver, which registers itself with the JDBC `DriverManager`.
6. **Step 2: Connection Establishment**: The application uses `DriverManager.getConnection()` with a connection string (URL) to establish a network session with the database.
7. **Step 3: Statement Creation**: A `Statement` or `PreparedStatement` object is generated from the connection to hold the SQL query.
8. **Step 4: Execution**: The statement executes the SQL query on the database server, triggering data manipulation or retrieval.
9. **Step 5: Result Processing**: If it was a SELECT query, the database returns data mapped into a `ResultSet` object, which Java iterates through.
10. **Step 6: Resource Cleanup**: Finally, the application explicitly closes the `ResultSet`, `Statement`, and `Connection` to release valuable database resources.
11. **Transaction Management**: JDBC plays a critical role in managing transactions, allowing developers to group multiple queries into a single atomic operation using `setAutoCommit(false)`.
12. **Metadata Processing**: JDBC also provides `DatabaseMetaData` and `ResultSetMetaData`, allowing applications to dynamically inspect database structures, table schemas, and column types.

```text
  +-------------+       +---------------+       +------------------+
  |  Java App   |       |   JDBC API    |       |   Relational DB  |
  | (SQL Query) | ----> | (Translation) | ----> | (Executes Query) |
  | (Java Code) | <---- | (ResultSets)  | <---- | (Returns Data)   |
  +-------------+       +---------------+       +------------------+
```

```java
// Explanation: The Role of JDBC (Full Flow)    //
import java.sql.*;                              // Import core package
                                                //
public class JDBCRoleDemo {                     // Main class
    public static void main(String[] args) {    // Program entry point
        String url = "jdbc:mysql://localhost/testdb";// DB URL (Step 1&2)
        String user = "root"; String pass = "pwd";   // Credentials
                                                //
        try (Connection con = DriverManager.getConnection(url, user, pass);
             Statement stmt = con.createStatement(); // Step 3
             ResultSet rs = stmt.executeQuery("SELECT * FROM Emp")) { // Step 4
                                                //
            // Step 5: Process Results          //
            while (rs.next()) {                 // Loop through table rows
                System.out.println("Emp ID: " + rs.getInt("id")); // Extract
            }                                   //
            // Step 6: Cleanup is handled automatically by try-with-resources
        } catch (SQLException e) {              // Handle failures
            e.printStackTrace();                // Print error info
        }                                       //
    }                                           //
}                                               //
```
