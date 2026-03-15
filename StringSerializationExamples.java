import java.io.*;

/**
 * StringSerializationExamples.java
 * 
 * This file contains typical 10-mark exam programming answers for:
 * 1. StringBuffer methods
 * 2. Wrapper classes (Autoboxing/Unboxing)
 * 3. Serialization and Deserialization
 * 4. Synchronization (Mutex) concepts
 */

// ==========================================
// 1. Serialization Target Class
// ==========================================
// The class MUST implement java.io.Serializable (Marker Interface)
class StudentData implements Serializable {
    // transient keyword means this variable WON'T be serialized
    transient int passcode;

    String name;
    int rollNo;

    public StudentData(String name, int rollNo, int passcode) {
        this.name = name;
        this.rollNo = rollNo;
        this.passcode = passcode;
    }

    public void display() {
        System.out.println("Roll No: " + rollNo + ", Name: " + name + ", Passcode: " + passcode);
    }
}

// ==========================================
// 2. Main Examples Class
// ==========================================
public class StringSerializationExamples {

    public static void main(String[] args) {

        System.out.println("=== 1. String vs StringBuffer ===");
        // String is immutable
        String s1 = "Hello";
        s1.concat(" World"); // Returns a new string, but s1 remains unchanged
        System.out.println("String after concat: " + s1); // Prints "Hello"

        // StringBuffer is mutable
        StringBuffer sb = new StringBuffer("Hello");
        sb.append(" World"); // Modifies the original object
        System.out.println("StringBuffer after append: " + sb);

        System.out.println("Other StringBuffer methods:");
        sb.insert(5, " Java");
        System.out.println("Insert: " + sb);
        sb.reverse();
        System.out.println("Reverse: " + sb);
        sb.reverse(); // put it back
        sb.delete(5, 10);
        System.out.println("Delete: " + sb);

        System.out.println("\n=== 2. Wrapper Classes ==="); // Autoboxing and Unboxing
        int primitiveInt = 50;

        // Autoboxing: Primitive to Wrapper Object automatically
        Integer wrapperInt = primitiveInt;
        System.out.println("Autoboxed Integer Object: " + wrapperInt);

        // Unboxing: Wrapper Object to Primitive automatically
        int newPrimitive = wrapperInt;
        System.out.println("Unboxed primitive int: " + newPrimitive);

        // Utility method example
        String numStr = "100";
        int parsedInt = Integer.parseInt(numStr);
        System.out.println("Parsed integer from string: " + parsedInt);

        System.out.println("\n=== 3. Serialization & Deserialization ===");
        StudentData student1 = new StudentData("Alice", 101, 9999);
        System.out.print("Original Object State: ");
        student1.display();

        String filename = "student.ser";

        // A. Serialization
        try {
            // FileOutputStream creates a file stream
            FileOutputStream fileOut = new FileOutputStream(filename);
            // ObjectOutputStream writes objects to the stream
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(student1); // Save the object

            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StudentData deserializedStudent = null;

        // B. Deserialization
        try {
            // FileInputStream reads the file stream
            FileInputStream fileIn = new FileInputStream(filename);
            // ObjectInputStream recreates the object
            ObjectInputStream in = new ObjectInputStream(fileIn);

            deserializedStudent = (StudentData) in.readObject(); // Read and cast the object

            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.print("Deserialized Object State: ");
        deserializedStudent.display();
        System.out.println("Notice that 'passcode' is 0, because it was marked 'transient'!");

        System.out.println("\n=== 4. Mutex via Synchronized Block ===");
        Object mutex = new Object();
        // A simple demonstration of acquiring a monitor (Mutex)
        synchronized (mutex) {
            System.out.println("Thread acquired the lock (mutex) for the object.");
            System.out.println("Executing critical section...");
            System.out.println("Thread released the lock.");
        }
    }
}
