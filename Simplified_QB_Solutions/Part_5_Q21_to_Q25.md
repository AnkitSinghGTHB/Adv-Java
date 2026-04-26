# Advanced Java Solutions - Part 5 (Q21 to Q25)

---

## Question 21: Write a Java program to perform Insert, Select, Update, and Delete operations on a database table using JDBC.

**Topic Introduction: Basic CRUD Operations using JDBC Statement.**

1. **Topic Introduction**: CRUD stands for Create (Insert), Read (Select), Update, and Delete. These form the fundamental data operations for any database application.
2. **JDBC Statements**: The standard `Statement` interface is utilized here to execute raw SQL string commands directly against the connected database.
3. **Execution Methods**: `executeUpdate()` is strictly used for operations that modify data (Insert, Update, Delete) and returns an integer representing affected rows.
4. **Query Methods**: `executeQuery()` is strictly used for Select operations and always returns a `ResultSet` object containing the requested data.
5. **The Setup Phase**: First, the program establishes a connection to the database using `DriverManager.getConnection()` with appropriate credentials.
6. **The Insert Operation**: Using standard SQL syntax (`INSERT INTO table VALUES(...)`), a new record is added. The method returns `1` if successful.
7. **The Update Operation**: Using the `UPDATE` SQL command with a `WHERE` clause, existing data is modified. Missing the `WHERE` clause updates all rows!
8. **The Delete Operation**: The `DELETE FROM` SQL command removes specific records matching a condition provided in the `WHERE` clause.
9. **The Select Operation**: The `SELECT` command retrieves records, which are then sequentially processed using a `while(rs.next())` loop.
10. **Data Extraction**: Inside the ResultSet loop, methods like `getInt("columnName")` and `getString("columnName")` extract the specific column values for that row.
11. **Resource Closure**: It is mandatory to close the `ResultSet`, `Statement`, and `Connection` in a `finally` block or use try-with-resources to prevent memory leaks.
12. **Limitations**: Standard `Statement` objects are susceptible to SQL Injection attacks if concatenating user input directly into the SQL string.

```text
  [JDBC App] ------------ SQL Commands ------------> [Database]
      |                                                  |
      +-- executeUpdate("INSERT...") -> 1 row affected --+
      +-- executeUpdate("UPDATE...") -> 1 row affected --+
      +-- executeUpdate("DELETE...") -> 1 row affected --+
      +-- executeQuery("SELECT...")  -> Returns ResultSet+
```

```java
// Explanation: Basic CRUD with Statement       //
import java.sql.*;                              // Import SQL package
                                                //
public class CrudDemo {                         // Main class
    public static void main(String[] args) {    // Program entry point
        String url = "jdbc:h2:mem:test";        // In-memory DB URL
        try (Connection c = DriverManager.getConnection(url,"sa","");
             Statement s = c.createStatement()) { // Create statement
                                                //
            // Table Setup                      // Create testing table
            s.execute("CREATE TABLE Emp(Id INT, Name VARCHAR(50))");
                                                //
            // 1. INSERT (Create)               //
            s.executeUpdate("INSERT INTO Emp VALUES(1, 'John')");
            s.executeUpdate("INSERT INTO Emp VALUES(2, 'Mary')");
                                                //
            // 2. UPDATE                        //
            s.executeUpdate("UPDATE Emp SET Name='Jane' WHERE Id=1");
                                                //
            // 3. DELETE                        //
            s.executeUpdate("DELETE FROM Emp WHERE Id=2");
                                                //
            // 4. SELECT (Read)                 //
            ResultSet rs = s.executeQuery("SELECT * FROM Emp");
            while(rs.next()) {                  // Iterate results
                System.out.println("ID: " + rs.getInt("Id") + 
                                   ", Name: " + rs.getString("Name"));
            }                                   //
        } catch (SQLException e) {              // Error handling
            e.printStackTrace();                // Print error
        }                                       //
    }                                           //
}                                               //
```

---

## Question 22: Write a Java program to demonstrate database connectivity and perform basic operations like data insertion, retrieval, updation, and deletion.

**Topic Introduction: Database Connectivity and Execution Flow.**

1. **Topic Introduction**: Establishing robust database connectivity involves a strict sequence of logical steps to ensure data integrity and resource safety.
2. **Step 1 - Driver Loading**: Though optional in modern JDBC, historically `Class.forName()` explicitly loaded the driver class into JVM memory.
3. **Step 2 - Connection URL**: A precisely formatted connection string dictates the protocol, sub-protocol, server address, port, and target database name.
4. **Step 3 - Authentication**: Valid credentials (username and password) must be provided to the `DriverManager` to authorize access to the database engine.
5. **Step 4 - Statement Creation**: A bridge for communication, the `Statement` object, is instantiated directly from the active `Connection` object.
6. **Step 5 - Insertion Logic**: We execute `INSERT INTO Students VALUES(101, 'Alice')` to populate our testing environment.
7. **Step 6 - Retrieval Logic**: We execute `SELECT * FROM Students` to fetch the data back, mapping it into a `ResultSet` for processing.
8. **Step 7 - Updation Logic**: We modify existing data executing `UPDATE Students SET Name='Bob' WHERE ID=101`.
9. **Step 8 - Deletion Logic**: We permanently remove the record executing `DELETE FROM Students WHERE ID=101`.
10. **Exception Handling**: All JDBC code must reside within `try-catch` blocks because `SQLException` is a checked exception that must be addressed.
11. **Connection Lifecycle**: Connections represent open network sockets; they are precious resources that should be held for the shortest possible duration.
12. **Idempotency**: Good database design ensures that running operations like `DELETE` multiple times does not crash the system, simply affecting zero rows.

```text
  Connection Lifecycle:
  [Start] -> [Load Driver] -> [Open Connection] -> [Execute Queries]
                                                           |
  [End] <-------------------- [Close Connection] <---------+
```

```java
// Explanation: Database Connectivity Flow      //
import java.sql.*;                              // Import SQL package
                                                //
public class DBConnectDemo {                    // Main class
    public static void main(String[] args) {    // Program entry point
        String url = "jdbc:h2:mem:test2";       // Database URL
        try (Connection con = DriverManager.getConnection(url,"sa","");
             Statement stmt = con.createStatement()) { // Get Statement
                                                //
            stmt.execute("CREATE TABLE Student(Id INT, Name VARCHAR(50))");
                                                //
            // Insertion                        //
            int i = stmt.executeUpdate("INSERT INTO Student VALUES(1,'A')");
            System.out.println(i + " row(s) inserted.");// Status msg
                                                //
            // Updation                         //
            int u = stmt.executeUpdate("UPDATE Student SET Name='B'");
            System.out.println(u + " row(s) updated."); // Status msg
                                                //
            // Retrieval                        //
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");
            while(rs.next()) System.out.println("Read: "+rs.getString(2));
                                                //
            // Deletion                         //
            int d = stmt.executeUpdate("DELETE FROM Student");
            System.out.println(d + " row(s) deleted."); // Status msg
                                                //
        } catch (SQLException e) {              // Catch DB errors
            System.out.println("DB Error: " + e.getMessage());
        }                                       //
    }                                           //
}                                               //
```

---

## Question 23: Create a Java application that performs CRUD operations using PreparedStatement instead of Statement.

**Topic Introduction: CRUD Operations using PreparedStatement.**

1. **Topic Introduction**: `PreparedStatement` extends `Statement` and is used to execute pre-compiled SQL queries with dynamic parameter injection, significantly improving security and performance.
2. **Pre-compilation Advantage**: When the `Connection` creates a `PreparedStatement`, the database engine parses, compiles, and optimizes the query immediately, saving time on repeated executions.
3. **Parameter Placeholders**: Instead of hardcoding values or concatenating strings, the SQL query uses question marks (`?`) as dynamic placeholders for future data.
4. **Setting Parameters**: Developers use strong-typed methods like `setInt(index, value)` or `setString(index, value)` to replace the placeholders. Note: Indexing starts at 1, not 0.
5. **SQL Injection Prevention**: This is the most crucial benefit. `PreparedStatement` automatically escapes dangerous characters in user input, completely neutralizing SQL injection attacks.
6. **Insert Operation**: `String sql = "INSERT INTO table VALUES(?, ?)";` followed by parameter setting and `executeUpdate()`.
7. **Select Operation**: `String sql = "SELECT * FROM table WHERE id = ?";` followed by setting the ID parameter and `executeQuery()`.
8. **Update Operation**: `String sql = "UPDATE table SET name = ? WHERE id = ?";` requires setting two parameters in the correct order.
9. **Delete Operation**: `String sql = "DELETE FROM table WHERE id = ?";` requires setting the ID parameter to target the specific row.
10. **Batch Processing**: Prepared statements excel at batch processing (inserting 1000 records at once) because the query is parsed only once.
11. **Code Readability**: Eliminating complex string concatenations (e.g., `" + name + "`) makes the Java code significantly cleaner and less prone to syntax errors.
12. **Binary Data**: They are uniquely capable of handling raw binary data streams, allowing the storage of files or images (BLOBs) directly in the database.

```text
  [Bad: Statement]
  "SELECT * FROM Users WHERE name = '" + userInput + "';"
   --> Vulnerable to: ' OR 1=1 --
   
  [Good: PreparedStatement]
  "SELECT * FROM Users WHERE name = ?"
   --> Sets placeholder safely: stmt.setString(1, userInput);
```

```java
// Explanation: CRUD with PreparedStatement     //
import java.sql.*;                              // Import SQL package
                                                //
public class PrepDemo {                         // Main class
    public static void main(String[] args) {    // Program entry point
        String url = "jdbc:h2:mem:test3";       // DB URL
        try (Connection c = DriverManager.getConnection(url,"sa","")) {
            c.createStatement().execute("CREATE TABLE T(ID INT, Val VARCHAR(5))");
                                                //
            // 1. INSERT using PreparedStmt     //
            String ins = "INSERT INTO T VALUES(?, ?)"; // SQL with placeholders
            try (PreparedStatement ps = c.prepareStatement(ins)) {
                ps.setInt(1, 101);              // Set 1st parameter (ID)
                ps.setString(2, "Apple");       // Set 2nd parameter (Val)
                ps.executeUpdate();             // Execute pre-compiled
            }                                   //
                                                //
            // 2. UPDATE using PreparedStmt     //
            String upd = "UPDATE T SET Val=? WHERE ID=?";
            try (PreparedStatement ps = c.prepareStatement(upd)) {
                ps.setString(1, "Pear");        // Set Val parameter
                ps.setInt(2, 101);              // Set ID parameter
                ps.executeUpdate();             // Execute update
            }                                   //
                                                //
            // 3. SELECT using PreparedStmt     //
            String sel = "SELECT * FROM T WHERE ID=?";
            try (PreparedStatement ps = c.prepareStatement(sel)) {
                ps.setInt(1, 101);              // Set ID parameter
                ResultSet rs = ps.executeQuery(); // Execute select
                if(rs.next()) System.out.println("Found: " + rs.getString("Val"));
            }                                   //
        } catch (SQLException e) {              // Catch DB errors
            e.printStackTrace();                // Print error
        }                                       //
    }                                           //
}                                               //
```

---

## Question 24: In a product inventory system, a user wants to insert product details (Product ID, Name, Price, Quantity) into a database. The program should accept multiple inputs and display all stored records. Implement this using JDBC and explain the logic.

**Topic Introduction: Product Inventory System using JDBC (Multiple Inputs & Display).**

1. **Topic Introduction**: This scenario simulates a real-world inventory application where an administrator continuously inputs multiple product details dynamically using the Scanner class.
2. **Schema Definition**: The database table requires four specific columns: `ProdID` (Integer), `Name` (String), `Price` (Double), and `Quantity` (Integer).
3. **PreparedStatement Choice**: Because the application accepts continuous user input inside a loop, `PreparedStatement` is strictly required for security and loop performance.
4. **Input Loop Logic**: A `while(true)` loop (or do-while) wraps the Scanner input requests, prompting the user for all four product details sequentially.
5. **Execution in Loop**: After gathering the four details, they are injected into the `PreparedStatement` using setters, and `executeUpdate()` is called to insert the record.
6. **Loop Continuation**: The program asks the user "Add another? (Y/N)". If the user enters 'N', the loop terminates and proceeds to the display logic.
7. **Display Logic**: Once data entry is complete, a standard `Statement` is created to execute `SELECT * FROM Products`.
8. **Result Formatting**: The `ResultSet` is iterated, extracting variables using `getInt()`, `getString()`, and `getDouble()`, formatting them into a clean console table.
9. **Scanner Management**: It is critical to manage the Scanner buffer correctly (e.g., calling `scanner.nextLine()` after `scanner.nextInt()`) to prevent skipping input prompts.
10. **Data Type Mapping**: Java `double` naturally maps to SQL `DECIMAL` or `DOUBLE`, while Java `int` maps to SQL `INT`.
11. **Transaction Considerations**: For high-volume inventory systems, disabling auto-commit and executing inserts in batches is significantly more efficient than individual commits.
12. **Real-world Application**: This pattern forms the core backend logic for Point-of-Sale (POS) systems or warehouse management software.

```text
  [Start Loop] -> Prompt Details -> [Scanner Input] -> Set Parameters
       ^                                                     |
       |                                                     v
   (Yes) <--- Ask: "Add another?" <-------------------- executeUpdate()
       |
     (No) -> [Execute SELECT Query] -> Print Results -> [End]
```

```java
// Explanation: Product Inventory System        //
import java.sql.*; import java.util.Scanner;    // Imports
                                                //
public class Inventory {                        // Main class
    public static void main(String[] args) {    // Program entry point
        String url = "jdbc:h2:mem:inv";         // DB URL
        try (Connection c = DriverManager.getConnection(url,"sa","");
             Scanner sc = new Scanner(System.in)) { // Open Scanner
            c.createStatement().execute(        // Create Table
                "CREATE TABLE Prod(ID INT, Name VARCHAR(50), Price DOUBLE, Qty INT)");
                                                //
            String sql = "INSERT INTO Prod VALUES(?, ?, ?, ?)";
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                String choice = "Y";            // Loop control variable
                while(choice.equalsIgnoreCase("Y")) { // Input loop
                    System.out.print("Product ID: ");  // Prompt user
                    ps.setInt(1, sc.nextInt());  // Read & set ID
                    sc.nextLine();               // Clear buffer
                    System.out.print("Name: ");  // Prompt user
                    ps.setString(2, sc.nextLine()); // Read & set name
                    System.out.print("Price: "); // Prompt user
                    ps.setDouble(3, sc.nextDouble()); // Read & set price
                    System.out.print("Quantity: "); // Prompt user
                    ps.setInt(4, sc.nextInt());  // Read & set qty
                    ps.executeUpdate();         // Save to DB
                    sc.nextLine();               // Clear buffer
                    System.out.print("Add another? (Y/N): ");
                    choice = sc.nextLine();      // User decides loop
                }                               //
            }                                   //
                                                //
            System.out.println("\n--- Inventory List ---");
            ResultSet rs = c.createStatement().executeQuery("SELECT * FROM Prod");
            while(rs.next()) {                  // Display Loop
                System.out.printf("ID:%d | %s | $%.2f | Qty:%d\n",
                    rs.getInt("ID"), rs.getString("Name"), 
                    rs.getDouble("Price"), rs.getInt("Qty"));
            }                                   //
        } catch (Exception e) {                 // Handle errors
            e.printStackTrace();                // Print trace
        }                                       //
    }                                           //
}                                               //
```

---

## Question 25: Develop a Java Swing application using the MVC architecture to manage student details (add, view, update). Clearly separate Model, View, and Controller components.

**Topic Introduction: Java Swing Application using MVC Architecture.**

1. **Topic Introduction**: MVC (Model-View-Controller) is an architectural design pattern that separates an application into three interconnected components, ensuring code modularity.
2. **The Model (Data)**: The Model manages the data, state, and business logic. Here, it is represented by a `Student` JavaBean class (holding ID, Name, Grade).
3. **The View (UI)**: The View manages the visual representation of the Model. Here, it is a Swing `JFrame` containing text fields, a table, and buttons, completely unaware of business logic.
4. **The Controller (Logic)**: The Controller acts as the middleman. It listens for user actions in the View, processes them, updates the Model, and refreshes the View.
5. **Decoupling Benefit**: If we decide to change the GUI from Swing to JavaFX, or change the data storage from Memory to a Database, the other components remain entirely untouched.
6. **Model Implementation**: `Student.java` simply contains private fields, getters, and setters. It holds no UI code and no ActionListeners.
7. **View Implementation**: `StudentView.java` initializes the GUI layout. It exposes its UI components (like returning the text from the name field) via getter methods.
8. **Controller Implementation**: `StudentController.java` is initialized with references to both the View and a List of Models. It attaches listeners to the View's buttons.
9. **The "Add" Workflow**: User clicks "Add" -> Controller detects click -> Controller pulls text from View -> Controller creates new Model -> Controller adds to List -> Controller refreshes View table.
10. **The "View" Workflow**: Usually handled automatically by the Controller reflecting the current state of the Model List directly into a Swing `DefaultTableModel` or `JTextArea`.
11. **Observer Pattern Link**: In advanced MVC, the Model implements the Observer pattern, automatically firing events to the View when its internal state changes.
12. **Maintainability**: This strict separation allows front-end UI designers and back-end logic developers to work concurrently on the same project without stepping on each other's toes.

```text
          (Updates State)
        +-----------------> [ MODEL ]
        |                      | (Notifies / Data)
 [ CONTROLLER ]                v
        ^                   [ VIEW ]
        | (User Events)        |
        +----------------------+
```

```java
// Explanation: Simple MVC Architecture Demo    //
import javax.swing.*; import java.awt.event.*;  // UI & Event imports
import java.util.*;                             // Collection imports
                                                //
class Student {                                 // 1. THE MODEL
    private String name;                        // Data field
    public Student(String n) { name = n; }      // Constructor
    public String getName() { return name; }    // Getter
}                                               //
                                                //
class StudentView {                             // 2. THE VIEW
    JFrame frame = new JFrame("MVC Demo");      // Window
    JTextField nameField = new JTextField(15);  // Input
    JButton addBtn = new JButton("Add");        // Button
    JTextArea display = new JTextArea(5, 20);   // Display area
    public StudentView() {                      // Constructor setup
        JPanel p = new JPanel();                // Panel setup
        p.add(new JLabel("Name:")); p.add(nameField); p.add(addBtn);
        frame.add(p, "North"); frame.add(new JScrollPane(display));
        frame.pack(); frame.setVisible(true);   // Display window
    }                                           //
}                                               //
                                                //
class StudentController {                       // 3. THE CONTROLLER
    private List<Student> modelList;            // Reference to Models
    private StudentView view;                   // Reference to View
                                                //
    public StudentController(StudentView v, List<Student> m) {
        this.view = v; this.modelList = m;      // Initialization
        view.addBtn.addActionListener(e -> {    // Listen to View
            String input = view.nameField.getText(); // Extract data
            modelList.add(new Student(input));  // Update Model
            updateView();                       // Refresh View
        });                                     //
    }                                           //
    private void updateView() {                 // Refresh Logic
        view.display.setText("");               // Clear text
        for(Student s : modelList)              // Loop models
            view.display.append(s.getName() + "\n"); // Append data
    }                                           //
}                                               //
                                                //
public class MVCApp {                           // Main execution class
    public static void main(String[] args) {    // Program entry point
        List<Student> list = new ArrayList<>();  // Create model list
        StudentView view = new StudentView();   // Create view
        new StudentController(view, list);      // Wire controller
    }                                           //
}                                               //
```
