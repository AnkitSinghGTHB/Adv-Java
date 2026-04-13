import java.io.BufferedReader; // Importing BufferedReader class for character-based input
import java.io.Console; // Importing Console class for console-based input
import java.io.IOException; // Importing IOException to handle input/output errors
import java.io.InputStreamReader; // Importing InputStreamReader to bridge byte and character streams
import java.util.Scanner; // Importing Scanner class for parsing primitive types and strings

public class InputDemo { // Declaring the main public class named InputDemo
    public static void main(String[] args) throws IOException { // Main method entry point; declares possible
                                                                // IOException

        System.out.println("Hello world"); // Prints the standard greeting to the console

        // 1. Using Scanner Class
        System.out.print("Enter text using Scanner: "); // Prompts the user for input
        Scanner scanner = new Scanner(System.in); // Creates a Scanner object bound to standard input (keyboard)
        String scannerInput = scanner.nextLine(); // Reads an entire line of text entered by the user
        System.out.println("Scanner output: " + scannerInput); // Prints the captured string

        // 2. Using Console Class
        Console console = System.console(); // Obtains the unique Console object associated with the current JVM
        if (console != null) { // Checks if the console is available (might be null in some IDEs)
            String consoleInput = console.readLine("Enter text using Console: "); // Prompts and reads a line of text
            System.out.println("Console output: " + consoleInput); // Prints the captured string
        } else { // Fallback if Console is unavailable
            System.out.println("Console class is not available in this environment."); // Informs the user
        }

        // 3. Using InputStreamReader and BufferedReader Class
        System.out.print("Enter text using BufferedReader: "); // Prompts the user for input
        InputStreamReader isr = new InputStreamReader(System.in); // Creates a reader that decodes bytes from standard
                                                                  // input into characters
        BufferedReader br = new BufferedReader(isr); // Wraps the InputStreamReader for efficient buffering
        String brInput = br.readLine(); // Reads a line of text, potentially throwing an IOException
        System.out.println("BufferedReader output: " + brInput); // Prints the captured string

        scanner.close(); // Closes the scanner to prevent resource leaks
    }
}