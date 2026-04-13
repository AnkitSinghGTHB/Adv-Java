// Package declaration removed — file is not inside a 'mypack' directory

import java.io.*; // Importing IO classes for Serialization

// Inheritance: CustomThread extends Thread
// Serialization: Implements Serializable interface
class CustomThread extends Thread implements Serializable {

    // Array implementation: An array of integers
    private int[] numbers = { 10, 20, 30, 40 }; // Initializing an integer array with 4 elements

    @Override // Annotation indicating a superclass method is being overridden
    public void run() { // Overriding the run method from Thread class (Threading)
        System.out.println("Thread is running..."); // Output to indicate thread execution

        // Exception Handling: Try-catch block
        try { // Starting a try block to catch potential errors
            for (int i = 0; i <= numbers.length; i++) { // Intentionally looping one time too many to trigger an
                                                        // exception
                System.out.println("Number: " + numbers[i]); // Accessing array elements
            }
        } catch (ArrayIndexOutOfBoundsException e) { // Catching the specific out-of-bounds exception
            System.out.println("Exception Caught: Array index is out of bounds!"); // Handling the exception gracefully
        }
    }
}

public class AdvancedJavaConcepts { // Main driver class
    public static void main(String[] args) { // Main method

        CustomThread threadObj = new CustomThread(); // Instantiating the custom thread object

        // Serialization process
        try { // Try block for IO operations
            FileOutputStream fos = new FileOutputStream("threadData.ser"); // Creating an output stream to a file
            ObjectOutputStream oos = new ObjectOutputStream(fos); // Wrapping it in an ObjectOutputStream
            oos.writeObject(threadObj); // Serializing the object state into the file
            oos.close(); // Closing the stream
            System.out.println("Object successfully serialized."); // Confirmation message
        } catch (IOException e) { // Catching IO exceptions
            e.printStackTrace(); // Printing the stack trace if serialization fails
        }

        threadObj.start(); // Starting the thread (invokes the run method)
    }
}