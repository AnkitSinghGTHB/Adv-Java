public class StarRectangle { // Declaring the public class StarRectangle
    public static void main(String[] args) { // Main execution method

        int rows = 5; // Initializing a variable for the number of rows (height)
        int columns = 10; // Initializing a variable for the number of columns (width)

        for (int i = 0; i < rows; i++) { // Outer loop iterating from 0 up to the number of rows

            for (int j = 0; j < columns; j++) { // Inner loop iterating from 0 up to the number of columns
                System.out.print("*"); // Printing a star without a newline for each column
            }

            System.out.println(); // Printing a newline character after each row is complete
        }
    }
}