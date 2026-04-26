# Advanced Java Solutions - Part 9 (Q41 to Q45)

---

## Question 41: Design a Java program to accept user inputs (Name, ID, City) and handle user-defined exceptions if any of the fields are empty.

**Topic Introduction: User-Defined Exceptions for Input Validation.**

1. **Topic Introduction**: Java allows developers to create custom Exception classes to enforce specific business rules, making error handling more semantic and readable.
2. **Exception Inheritance**: To create a custom checked exception, a class must inherit from `java.lang.Exception`. For an unchecked exception, inherit from `RuntimeException`.
3. **The Custom Class**: We create `EmptyFieldException(String msg)` which calls the superclass constructor `super(msg)` to pass the custom error message up the chain.
4. **The `throw` Keyword**: The `throw` keyword (singular) is used to physically instantiate and trigger the exception object: `throw new EmptyFieldException("Name is empty!");`.
5. **The `throws` Keyword**: The `throws` keyword (plural) is placed in the method signature to warn the compiler that this method might trigger a specific checked exception.
6. **Input Validation Logic**: After collecting Name, ID, and City via Scanner, we use the `String.trim().isEmpty()` method to check if the user just pressed Enter or typed spaces.
7. **Business Rule Enforcement**: If `isEmpty()` is true, the program immediately aborts the current execution path by throwing our custom `EmptyFieldException`.
8. **Catching the Exception**: The main code block calling the validation method is wrapped in a `try` block, followed by a `catch(EmptyFieldException e)` block.
9. **Graceful Degradation**: Instead of the JVM crashing and printing a massive red stack trace, the `catch` block cleanly prints `e.getMessage()` to the user.
10. **Reusability**: This custom exception can be reused across the entire enterprise application wherever form validation is required.
11. **Best Practice**: Always perform input validation locally before attempting to send bad data to a database or a network server to save resources.
12. **Object Paradigm**: By treating errors as Objects, Java allows us to attach extra data to the error (like the specific field that failed) for complex debugging.

```text
  [Input: Name="", City="NY"]
         |
  [Validation Method checks Name.isEmpty()] --(True)--> throw EmptyFieldException
                                                              |
                                                     [Catch Block triggers]
                                                     Prints: "Field cannot be empty!"
```

```java
// Explanation: Custom Exception Validation     //
import java.util.Scanner;                       // Import Scanner
                                                //
// 1. Create Custom Exception Class             //
class EmptyFieldException extends Exception {   // Inherit Exception
    public EmptyFieldException(String msg) {    // Constructor
        super(msg);                             // Pass to parent
    }                                           //
}                                               //
                                                //
public class InputValidation {                  // Main class
    // Method declares it might throw error     //
    static void check(String s, String field) throws EmptyFieldException {
        if(s == null || s.trim().isEmpty())     // Validation logic
            throw new EmptyFieldException(field + " is empty!"); // Throw
    }                                           //
                                                //
    public static void main(String[] args) {    // Program entry
        Scanner sc = new Scanner(System.in);    // Setup input
        try {                                   // Start monitoring
            System.out.print("Enter Name: ");   // Prompt
            String name = sc.nextLine();        // Read
            check(name, "Name");                // Validate
                                                //
            System.out.print("Enter City: ");   // Prompt
            String city = sc.nextLine();        // Read
            check(city, "City");                // Validate
                                                //
            System.out.println("Data Saved!");  // Success path
        } catch (EmptyFieldException e) {       // Catch custom error
            System.out.println("Error: " + e.getMessage()); // Graceful fail
        }                                       //
    }                                           //
}                                               //
```

---

## Question 42: Write a Java program to handle multiple exceptions using multi-catch block.

**Topic Introduction: Handling Multiple Exceptions (Multi-catch block).**

1. **Topic Introduction**: A single block of code can trigger various types of errors. Java 7 introduced the multi-catch block to handle disparate exceptions cleanly without code duplication.
2. **Traditional Approach**: Prior to Java 7, catching a math error and a null pointer error required writing two entirely separate `catch` blocks, often duplicating the recovery logic.
3. **The Pipe Operator**: The multi-catch block uses the bitwise OR operator (`|`, acting as a pipe) to separate the different Exception classes within a single `catch` statement.
4. **Syntax Structure**: `catch (ArithmeticException | NullPointerException e) { ... }`. Both exceptions are routed to the exact same recovery block.
5. **Final Parameter**: The exception parameter `e` in a multi-catch block is implicitly `final`. You cannot reassign it (e.g., `e = new Exception()`) inside the catch block.
6. **Hierarchy Restriction**: You cannot combine exceptions in a multi-catch block if one is a subclass of the other. For example, `catch (FileNotFoundException | IOException e)` causes a compilation error.
7. **Execution Flow**: When the `try` block hits the first error (e.g., dividing by zero), it instantly aborts the rest of the `try` block and jumps to the multi-catch.
8. **Shared Logic**: This is incredibly useful when logging errors. Instead of writing `logger.error(e)` five times in five catch blocks, you write it once.
9. **General vs Specific**: It encourages developers to catch specific exceptions (good practice) rather than just lazily catching the top-level `Exception` class.
10. **Array Index Error**: `ArrayIndexOutOfBoundsException` occurs when attempting to access an array element outside its initialized bounds (e.g., `arr[10]` in an array of size 5).
11. **Arithmetic Error**: `ArithmeticException` most commonly occurs during integer division by zero (`5 / 0`).
12. **Code Maintainability**: Multi-catch significantly reduces boilerplate code, making the source code much easier to read and maintain.

```text
      [ TRY BLOCK ] -> arr[10] -> [ArrayIndexOutOfBounds] -+
           |                                               |
           +---------> 5 / 0   -> [ArithmeticException] ---+
                                                           |
                                                           v
                     catch(ArrayError | MathError e) -> [ SINGLE RECOVERY ]
```

```java
// Explanation: Multi-catch Block Demo          //
public class MultiCatchDemo {                   // Main class
    public static void main(String[] args) {    // Program entry point
        try {                                   // Start monitoring
            int[] arr = new int[5];             // Create small array
            String text = null;                 // Null reference
                                                //
            // Uncomment ONE line below to test different errors:
            // int ans = 10 / 0;                // Triggers Arithmetic
            arr[10] = 50;                       // Triggers ArrayIndex
            // int len = text.length();         // Triggers NullPointer
                                                //
            System.out.println("This won't print if error occurs.");
                                                //
        // Combine completely different exceptions into one block
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException | 
                 NullPointerException e) {      // The multi-catch syntax
                                                //
            // Single, shared error handling logic
            System.out.println("Exception Caught: " + e.getClass().getName());
            System.out.println("Details: " + e.getMessage());
        }                                       //
        System.out.println("Program continues normally."); // Proof of recovery
    }                                           //
}                                               //
```

---

## Question 43: Create a Java program to demonstrate method overriding and handling exceptions within the overridden method.

**Topic Introduction: Method Overriding and Exception Handling Rules.**

1. **Topic Introduction**: When a child class overrides a method from a parent class, Java enforces strict rules regarding what Exceptions the new overridden method is allowed to throw.
2. **The Core Rule**: The overriding (child) method cannot throw new or broader *Checked Exceptions* than the overridden (parent) method.
3. **No Exception Case**: If the parent method declares no exceptions (e.g., `void display()`), the child method cannot declare *any* checked exceptions (like `throws IOException`).
4. **Unchecked Freedom**: The child method is entirely free to throw any *Unchecked Exception* (like `ArithmeticException` or `NullPointerException`), regardless of the parent's signature.
5. **Same Exception Case**: If the parent throws `IOException`, the child is perfectly allowed to also throw `IOException`.
6. **Subclass Exception Case**: If the parent throws `IOException`, the child is allowed to throw a narrower subclass, such as `FileNotFoundException`.
7. **Fewer Exceptions Case**: The child method is allowed to drop the exception entirely. If the parent throws `IOException`, the child method can choose to throw nothing.
8. **The "Why"**: This preserves polymorphism. If `Parent p = new Child()`, the compiler only forces the programmer to handle the Parent's declared exceptions. If the child threw a new checked exception, it would bypass compiler checks, breaking Java's safety guarantees.
9. **Constructor Exception**: Interestingly, Constructors are the exact opposite. A child constructor *must* throw the same checked exceptions as the parent constructor, or broader ones.
10. **`try-catch` inside Overridden**: If a child method absolutely must perform an operation that throws an `IOException` (and the parent doesn't allow it), the child *must* wrap it in a local `try-catch` block instead of using `throws`.
11. **Static Methods**: Static methods cannot be overridden (they are "hidden"), so these strict exception signature rules do not apply to them.
12. **Interface Implementation**: The exact same overriding rules apply when a class implements an Interface method.

```text
  Parent: void doWork() throws IOException
             |
             +-- Allowed Child: void doWork() throws FileNotFoundException (Narrower)
             +-- Allowed Child: void doWork() (None)
             +-- ILLEGAL Child: void doWork() throws Exception (Broader)
```

```java
// Explanation: Overriding Exception Rules      //
import java.io.*;                               // Import IO
                                                //
class Parent {                                  // Parent Class
    // Parent declares a broad checked exception
    void processFile() throws IOException {     //
        System.out.println("Parent Processing.");
    }                                           //
}                                               //
                                                //
class Child extends Parent {                    // Child Class
    // Overriding method: allowed to throw narrower or no exception
    @Override                                   // Annotation check
    void processFile() throws FileNotFoundException { // Subclass of IOException
        System.out.println("Child Processing.");// Custom logic
        // throw new Exception(); <-- ILLEGAL (broader than IOException)
    }                                           //
}                                               //
                                                //
public class OverrideDemo {                     // Main class
    public static void main(String[] args) {    // Entry point
        Parent p = new Child();                 // Polymorphism
        try {                                   //
            p.processFile();                    // Calls Child's method
        } catch (IOException e) {               // Must catch Parent's declaration
            e.printStackTrace();                // Error handling
        }                                       //
    }                                           //
}                                               //
```

---

## Question 44: Explain the lifecycle of a Java Applet and write a basic applet to display "Welcome to Applet Lifecycle".

**Topic Introduction: Java Applet Lifecycle and Basic Display.**

1. **Topic Introduction**: An Applet's lifecycle is fundamentally different from a standalone Java application. It is managed by the browser or AppletViewer, executing specific methods at specific times.
2. **The `init()` Method**: Called exactly once when the Applet is first loaded into memory. It is used to initialize variables, load images, or construct GUI components.
3. **The `start()` Method**: Called immediately after `init()`, and also every time the user revisits the web page containing the Applet. It starts or resumes execution.
4. **The `paint()` Method**: Called automatically whenever the Applet needs to be redrawn on the screen (e.g., when the window is maximized or brought to the front).
5. **The `stop()` Method**: Called when the user navigates away from the web page or minimizes the browser. It pauses execution (like stopping an animation thread) to save CPU cycles.
6. **The `destroy()` Method**: Called exactly once when the browser is completely closed or the Applet is permanently removed from memory, releasing system resources.
7. **No `main()` method**: Applets completely bypass the `public static void main(String[] args)` entry point. The browser creates the Applet instance using a default no-argument constructor.
8. **Applet Class**: All custom Applets must inherit from `java.applet.Applet` (or `javax.swing.JApplet` for Swing UI components).
9. **Graphics Context**: The `paint(Graphics g)` method receives a `Graphics` object from the host OS, which is required to draw text or shapes.
10. **Browser Security**: The Applet lifecycle operates entirely within a strict "Sandbox", preventing the code from reading local files or executing system commands maliciously.
11. **Embedding**: The lifecycle only begins when an HTML `<applet>` tag points to the compiled `.class` file.
12. **Obsolescence**: Modern web standards (HTML5/Canvas) have completely replaced this lifecycle architecture in practical software development.

```text
  [Browser Loads Page] -> init() -> start() -> paint()
                                      ^           |
                                      |           | (User leaves page)
                                      +-- stop()<-+
                                            |
  [Browser Closes] -----------------> destroy()
```

```java
// Explanation: Applet Lifecycle Demo           //
import java.applet.Applet;                      // Import Applet
import java.awt.Graphics;                       // Import Graphics
                                                //
/* HTML required to run:                        // Embedded HTML
<applet code="LifeCycle.class" width="300" height="100"></applet>
*/                                              //
                                                //
public class LifeCycle extends Applet {         // Extend Applet
    String msg = "";                            // State variable
                                                //
    public void init() {                        // Step 1: Born
        msg += "init() -> ";                    // Update state
        System.out.println("Applet Initialized");
    }                                           //
    public void start() {                       // Step 2: Wakes up
        msg += "start() -> ";                   // Update state
        System.out.println("Applet Started");   //
    }                                           //
    public void paint(Graphics g) {             // Step 3: Draws
        msg += "paint()";                       // Update state
        g.drawString(msg, 20, 50);              // Render to screen
        // Also draw the requested welcome message
        g.drawString("Welcome to Applet Lifecycle", 20, 80);
    }                                           //
    public void stop() {                        // Step 4: Sleeps
        System.out.println("Applet Stopped");   // Console log only
    }                                           //
    public void destroy() {                     // Step 5: Dies
        System.out.println("Applet Destroyed"); // Console log only
    }                                           //
}                                               //
```

---

## Question 45: Develop a Java program to read data from a file and write it to another file, handling file-related exceptions properly.

**Topic Introduction: File I/O Operations and Exception Handling.**

1. **Topic Introduction**: Java handles File Input and Output (I/O) using stream classes. Reading from one file and writing to another is the core mechanic of data backup or format conversion.
2. **Byte Streams vs Character Streams**: `FileInputStream` reads raw bytes (images, audio), whereas `FileReader` reads human-readable text characters. For text files, `FileReader`/`FileWriter` is correct.
3. **The BufferedReader**: Wrapping a `FileReader` inside a `BufferedReader` provides the `readLine()` method, significantly improving performance by reading chunks of text into RAM rather than one character at a time.
4. **The BufferedWriter**: Similarly, wrapping a `FileWriter` inside a `BufferedWriter` allows efficient chunk-writing and provides the `newLine()` method.
5. **File Paths**: File objects require paths. If a file doesn't exist during a read operation, a `FileNotFoundException` is immediately thrown.
6. **The Reading Loop**: We read the file using a `while ((line = reader.readLine()) != null)` loop. It returns `null` when it hits the End of File (EOF).
7. **The Writing Logic**: Inside that loop, we immediately pass the `line` variable to `writer.write(line)`, followed by `writer.newLine()` to preserve the line breaks.
8. **The `try-with-resources` Block**: Introduced in Java 7, putting the reader/writer declarations inside `try(...)` guarantees they will be automatically closed, even if an exception occurs.
9. **Handling `IOException`**: All stream operations (open, read, write, close) can fail due to OS restrictions (e.g., disk full, permission denied), requiring an `IOException` catch block.
10. **The `finally` Block**: If not using `try-with-resources`, developers must manually call `.close()` inside a `finally` block to prevent resource leaks and OS file locks.
11. **Flushing Buffers**: `BufferedWriter` holds data in RAM. It only writes to the hard drive when full, or when `.flush()` or `.close()` is called. Failing to close streams results in empty destination files.
12. **File Creation**: When using `FileWriter`, if the destination file does not exist, Java automatically asks the OS to create it.

```text
  [Source File (HDD)]             [RAM Buffer]               [Dest File (HDD)]
        |                              |                             |
        +-- FileReader.read() -------->+                             |
                                       |                             |
                                       +--- FileWriter.write() ----->+
```

```java
// Explanation: File Copy with IO Exceptions    //
import java.io.*;                               // Import IO classes
                                                //
public class FileCopier {                       // Main class
    public static void main(String[] args) {    // Program entry
        // Use try-with-resources for auto-closing streams
        try (                                   //
            BufferedReader br = new BufferedReader(
                new FileReader("input.txt"));   // Open Source File
            BufferedWriter bw = new BufferedWriter(
                new FileWriter("output.txt"))   // Open/Create Target File
        ) {                                     //
            String line;                        // Buffer variable
            System.out.println("Copying file...");
                                                //
            // Read line by line until End of File (null)
            while ((line = br.readLine()) != null) {
                bw.write(line);                 // Write to target
                bw.newLine();                   // Add line break
            }                                   //
            System.out.println("Copy successful!"); // Success msg
                                                //
        } catch (FileNotFoundException e) {     // Handle missing source
            System.out.println("Error: input.txt not found!");
        } catch (IOException e) {               // Handle read/write errors
            System.out.println("I/O Error: " + e.getMessage());
        }                                       //
    }                                           //
}                                               //
```
