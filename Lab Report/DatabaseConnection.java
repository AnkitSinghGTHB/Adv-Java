import java.sql.Connection; // Importing Connection interface
import java.sql.DriverManager; // Importing DriverManager class to establish connections
import java.sql.SQLException; // Importing SQLException class to handle database errors

public class DatabaseConnection { // Declaring the main class
    public static void main(String[] args) { // Main method

        // Database credentials and URL (Modify these as per local setup)
        String url = "jdbc:mysql://localhost:3306/mydatabase"; // JDBC URL format: jdbc:subprotocol:subname
        String user = "root"; // Database username
        String password = "password123"; // Database password

        try { // Try block for database operations
              // Loading the MySQL JDBC Driver (Optional in newer JDBC versions, but good
              // practice)
            Class.forName("com.mysql.cj.jdbc.Driver"); // Dynamically loads the driver class

            // Establishing the connection
            Connection conn = DriverManager.getConnection(url, user, password); // Attempts to connect to the DB

            if (conn != null) { // Checking if connection was successfully established
                System.out.println("Successfully connected to the database!"); // Success message
                conn.close(); // Closing the connection immediately to release resources
            }

        } catch (ClassNotFoundException e) { // Catch block if Driver class is not found in classpath
            System.out.println("JDBC Driver not found!"); // Error message
            e.printStackTrace(); // Stack trace for debugging
        } catch (SQLException e) { // Catch block for SQL/Connection errors
            System.out.println("Database connection failed!"); // Error message
            e.printStackTrace(); // Stack trace for debugging
        }
    }
}