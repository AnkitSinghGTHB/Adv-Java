import java.sql.*;

/**
 * This class covers common exam programming questions related to JDBC.
 * It demonstrates the 7 essential steps for establishing a connection,
 * executing queries, and handling results.
 */
public class JDBCExamples {

    // Database credentials for exam demonstration purposes
    static final String DB_URL = "jdbc:mysql://localhost:3306/university";
    static final String USER = "root";
    static final String PASS = "password";

    public static void main(String[] args) {

        System.out.println("=== Demonstrating JDBC Best Practices ===");

        // Step 1: Initialize Connection and Statement as null for finally block
        Connection conn = null;
        Statement stmt = null;

        try {
            // Step 2: Register JDBC driver
            System.out.println("1. Registering JDBC driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 3: Open a connection
            System.out.println("2. Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Step 4: Execute a query
            System.out.println("3. Creating statement...");
            stmt = conn.createStatement();

            String sqlCheck = "SELECT roll_no, name, grades FROM students";
            System.out.println("4. Executing query: " + sqlCheck);

            // Step 5: Process the ResultSet
            ResultSet rs = stmt.executeQuery(sqlCheck);

            System.out.println("5. Retrieving Results:");
            while (rs.next()) {
                // Retrieve by column name corresponding to your database schema
                int rollNo = rs.getInt("roll_no");
                String name = rs.getString("name");
                String grade = rs.getString("grades");

                // Displaying values
                System.out.print("ID: " + rollNo);
                System.out.print(", Name: " + name);
                System.out.println(", Grade: " + grade);
            }

            // Step 6: Close ResultSet
            rs.close();

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Step 7: Finally block used to safely close resources
            System.out.println("6. Closing resources safely (Best Practice)...");
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                // Nothing we can do
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        System.out.println("Goodbye!");
    }
}

/*
 * EXAM EXPLANATION (Best Practices to Mention):
 * 1. Import `java.sql.*` to use all JDBC interfaces (`Connection`, `Statement`,
 * `ResultSet`).
 * 2. Register the driver using `Class.forName()`. (Note: In newer JDBC
 * versions, this is done automatically, but mentioning it shows fundamental
 * knowledge).
 * 3. Open a connection using `DriverManager.getConnection()` by passing URL,
 * user, and password.
 * 4. Always wrap DB logic in a `try-catch` block properly to handle
 * `SQLException`.
 * 5. Iterate over the `ResultSet` using a `while(rs.next())` loop. Extract data
 * by specifying column names using methods like `getInt()`, `getString()`.
 * 6. Use a `finally` block to explicitly close the `Statement` and `Connection`
 * objects. This prevents memory leaks and connection exhaustion, a core Best
 * Practice for programming databases.
 */
