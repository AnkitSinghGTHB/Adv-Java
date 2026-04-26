# Advanced Java Solutions - Part 10 (Q46 to Q50)

---

## Question 46: Write a Java program that defines a method to throw a custom exception if a given number is negative and demonstrate catching it.

**Topic Introduction: Custom Exception for Negative Numbers.**

1. **Topic Introduction**: Business logic often dictates that certain numerical inputs (like age, salary, or physical quantities) cannot mathematically be negative.
2. **The Custom Exception**: We create a class named `NegativeNumberException` that extends `Exception`. This forces it to be a Checked Exception, demanding explicit handling.
3. **Constructor Mapping**: The custom exception class requires a constructor `public NegativeNumberException(String msg)` that immediately calls `super(msg)` to pass the error text to the core Java Throwable class.
4. **The Validation Method**: We define a separate utility method, e.g., `static void checkNumber(int num) throws NegativeNumberException`.
5. **The `throw` Execution**: Inside this method, the logic is simply `if (num < 0) throw new NegativeNumberException("Negative not allowed!");`.
6. **Program Flow**: The `main` method prompts the user for an integer using a `Scanner`. It captures the input and passes it to the `checkNumber()` method.
7. **The Monitor Block**: Because `checkNumber` declares a checked exception, the call *must* be surrounded by a `try` block.
8. **The Recovery Block**: Immediately following the `try` block is the `catch (NegativeNumberException e)` block.
9. **Graceful Notification**: Inside the catch block, we use `System.out.println(e.getMessage())` to print the exact string we passed during the `throw` execution.
10. **State Preservation**: If the user enters a positive number, the `if` condition fails, no exception is thrown, the `catch` block is entirely skipped, and the program proceeds linearly.
11. **Testing**: Testing requires running the program twice: once with `-5` to ensure the catch block fires, and once with `5` to ensure it doesn't.
12. **Defensive Programming**: This specific pattern is the cornerstone of "Defensive Programming," where methods actively defend themselves against receiving invalid, corrupting data.

```text
  [Input: -10] ---> checkNumber(-10)
                          |
                    (Is -10 < 0?) -> YES
                          |
             throw new NegativeNumberException()
                          |
                  [Catch Block executes]
```

```java
// Explanation: Negative Number Validation      //
import java.util.Scanner;                       // Import Scanner
                                                //
// 1. Define the Exception                      //
class NegativeNumberException extends Exception { // Must extend Exception
    public NegativeNumberException(String msg) {  // Constructor
        super(msg);                             // Parent initialization
    }                                           //
}                                               //
                                                //
public class NegativeDemo {                     // Main class
    // 2. Define the risky method               //
    static void checkNumber(int n) throws NegativeNumberException {
        if(n < 0) {                             // Business logic check
            throw new NegativeNumberException("Error: Negative value!");
        }                                       //
        System.out.println("Valid number: " + n); // Success path
    }                                           //
                                                //
    public static void main(String[] args) {    // Program entry
        Scanner sc = new Scanner(System.in);    // Setup input
        System.out.print("Enter a number: ");   // Prompt
        int userInput = sc.nextInt();           // Read integer
                                                //
        try {                                   // 3. Monitor execution
            checkNumber(userInput);             // Call risky method
        } catch (NegativeNumberException e) {   // 4. Handle failure
            System.out.println(e.getMessage()); // Print custom message
        }                                       //
        System.out.println("Program continues...");// Proof of survival
    }                                           //
}                                               //
```

---

## Question 47: Discuss the hierarchy of exception classes in Java and explain the difference between checked and unchecked exceptions.

**Topic Introduction: Exception Hierarchy and Types (Checked vs Unchecked).**

1. **Topic Introduction**: Java treats all errors and exceptions as Objects. Understanding their inheritance hierarchy is crucial for robust error handling.
2. **The Root Class**: The absolute top of the hierarchy is `java.lang.Throwable`. Only objects inheriting from this class can be thrown using the `throw` keyword.
3. **The Two Branches**: `Throwable` immediately splits into two distinct main branches: `Error` and `Exception`.
4. **The `Error` Branch**: `Error` represents catastrophic system failures that a normal application should *never* try to catch (e.g., `OutOfMemoryError`, `StackOverflowError`).
5. **The `Exception` Branch**: `Exception` represents issues that a well-written application *should* anticipate and recover from.
6. **The Runtime Exception**: Under `Exception`, there is a highly special subclass called `RuntimeException`.
7. **Checked Exceptions**: These are any classes inheriting from `Exception` *except* those under `RuntimeException` (e.g., `IOException`, `SQLException`).
8. **The Compiler's Role (Checked)**: Checked exceptions are verified by the compiler at compile-time. If a method might throw one, the programmer is absolutely forced to write a `try-catch` block, or compilation fails.
9. **Unchecked Exceptions**: These are classes inheriting from `RuntimeException` (e.g., `NullPointerException`, `ArithmeticException`).
10. **The Compiler's Role (Unchecked)**: The compiler completely ignores unchecked exceptions. The programmer is not forced to catch them, as they usually indicate logical programming bugs rather than environmental issues.
11. **Design Philosophy**: Checked exceptions represent events outside the program's control (like a network cable being unplugged). Unchecked exceptions represent poor coding (like trying to access an empty array).
12. **Custom Creation**: By default, extending `Exception` creates a Checked exception, enforcing safety. Extending `RuntimeException` creates an Unchecked exception, offering flexibility.

```text
                  [ Throwable ]
                 /             \
            [ Error ]      [ Exception ] (Checked)
            (Fatal)         /         \
                           /       [ RuntimeException ] (Unchecked)
                 IOException          /              \
                 SQLException  NullPointerExc  ArithmeticExc
```

```java
// Explanation: Hierarchy Concepts Demo         //
import java.io.*;                               // IO imports
                                                //
public class HierarchyDemo {                    // Main class
    public static void main(String[] args) {    // Program entry
        // 1. UNCHECKED EXCEPTION (RuntimeException)
        // The compiler does NOT force us to catch this.
        // It happens at runtime due to a logic flaw.
        int[] arr = new int[2];                 // Size 2
        // arr[5] = 10; <-- Throws ArrayIndexOutOfBoundsException
                                                //
        // 2. CHECKED EXCEPTION                 //
        // The compiler FORCES us to catch this.
        // If we remove the try-catch, the code will NOT compile.
        try {                                   //
            FileReader fr = new FileReader("missing.txt");
        } catch (FileNotFoundException e) {     // Inherits from IOException
            System.out.println("Checked Exception: File not found.");
        }                                       //
                                                //
        // 3. ERROR (Do not catch these)        //
        // Example: public void loop(){ loop(); } // Causes StackOverflowError
    }                                           //
}                                               //
```

---

## Question 48: What is the `finally` block in Java exception handling? Explain its importance with a suitable programming example.

**Topic Introduction: The `finally` Block in Exception Handling.**

1. **Topic Introduction**: The `finally` block is an optional concluding block attached to a `try-catch` structure, designed to execute critical cleanup code regardless of what happened previously.
2. **Execution Guarantee**: The primary rule of the `finally` block is that it is guaranteed to execute, whether an exception was thrown or not, and whether an exception was successfully caught or not.
3. **Resource Leak Prevention**: Its most vital purpose is to close finite operating system resources like Database Connections, Network Sockets, and File Streams.
4. **The Success Path**: If the `try` block executes perfectly, it skips the `catch` block and jumps straight into the `finally` block before continuing.
5. **The Caught Path**: If an exception occurs and is successfully handled by the `catch` block, execution flows from `catch` directly into the `finally` block.
6. **The Uncaught Path**: Crucially, if an exception occurs but there is *no* matching catch block, the JVM will still pause the crash, execute the `finally` block, and *then* crash the program.
7. **The `return` Bypass**: Even if there is a `return` statement inside the `try` or `catch` block forcing the method to exit, the `finally` block will execute right before the actual return happens.
8. **Syntax Constraints**: A `finally` block cannot exist on its own. It must be preceded by either a `try` block or a `catch` block (e.g., `try-finally` is valid).
9. **The Only Exception**: The only scenario where a `finally` block does *not* execute is if `System.exit(0)` is explicitly called, or if the physical computer loses power.
10. **Variable Scope**: Variables declared inside the `try` block are not visible in the `finally` block. Streams must be declared *before* the `try` block to be closed in `finally`.
11. **Modern Alternative**: Since Java 7, the `try-with-resources` syntax automatically generates a hidden `finally` block to close resources, reducing boilerplate code.
12. **Best Practice**: Never put business logic in a `finally` block; reserve it strictly for teardown and cleanup operations.

```text
  [ TRY BLOCK ]
       |---> (No Error) ----+
       |                    |
       |---> (Error) ---> [ CATCH BLOCK ]
                            |
  [ FINALLY BLOCK ] <-------+ (Executes no matter what)
  (Close Files/DBs)
```

```java
// Explanation: The finally Block Execution     //
import java.util.Scanner;                       // Scanner import
                                                //
public class FinallyDemo {                      // Main class
    public static void main(String[] args) {    // Program entry
        Scanner sc = null;                      // Declare outside try
        try {                                   //
            sc = new Scanner(System.in);        // Initialize
            System.out.println("Try Block started.");
            int result = 10 / 0;                // Triggers ArithmeticException
            System.out.println("This will not print.");
                                                //
        } catch (NullPointerException e) {      // WRONG CATCH BLOCK!
            // This won't catch the Arithmetic error!
            System.out.println("Caught Null Pointer.");
                                                //
        } finally {                             //
            // THIS WILL EXECUTE despite the uncaught exception!
            System.out.println("Finally Block executed.");
            if (sc != null) {                   // Safe resource closure
                sc.close();                     // Close scanner
                System.out.println("Scanner closed cleanly.");
            }                                   //
        }                                       //
        // This will NOT print because the unhandled error crashes the app
        System.out.println("Program finished.");//
    }                                           //
}                                               //
```

---

## Question 49: Write a Java program that demonstrates the use of `throw` and `throws` keywords in handling exceptions.

**Topic Introduction: Differences: `throw` vs `throws` Keywords.**

1. **Topic Introduction**: The `throw` and `throws` keywords are the fundamental mechanics for transferring error control in Java, but they serve entirely different syntactic and logical purposes.
2. **The `throw` Keyword**: `throw` (singular) is an action verb. It is used inside a method body to physically launch an Exception object into the runtime environment.
3. **The `throws` Keyword**: `throws` (plural) is a warning label. It is attached to a method's signature to warn the compiler that the method *might* eject a specific exception.
4. **Location Difference**: `throw` is used *inside* a method block. `throws` is used at the end of the method declaration line, right before the opening curly brace `{`.
5. **Count Difference**: You can only `throw` one single Exception object at a time. However, a method can declare multiple exceptions using `throws`, separated by commas (e.g., `throws IOException, SQLException`).
6. **Object vs Class**: `throw` is followed by an actual instantiated object (`throw new Exception()`). `throws` is followed by a Class name (`throws Exception`).
7. **Use Case (`throw`)**: It is primarily used to enforce custom business logic, artificially generating an error when conditions are unacceptable (e.g., age < 18).
8. **Use Case (`throws`)**: It is used to delegate responsibility. It essentially tells the compiler: "I don't want to handle this error here, make the calling method deal with it."
9. **Checked Exceptions**: If you use `throw` to launch a checked exception, you absolutely *must* also use `throws` in the method signature to warn callers.
10. **Unchecked Exceptions**: If you `throw` a `RuntimeException`, using `throws` in the signature is optional (but occasionally done for documentation purposes).
11. **Catching**: A method that uses `throws` forces the parent method calling it to wrap the call in a `try-catch` block.
12. **Together**: They work in tandem. `throws` builds the warning system, while `throw` executes the actual penalty.

```text
  Method Signature:   void myMethod() throws IOException, SQLException {
                                        ^ (Plural, Multiple Classes, Warning)
  
  Method Body:            if(error) {
                              throw new IOException("Failed!");
                                ^ (Singular, One Object, Action)
                          }
                      }
```

```java
// Explanation: throw vs throws Keywords        //
import java.io.IOException;                     // Import exception
                                                //
public class KeywordDemo {                      // Main class
    // 'throws' in signature warns the caller   //
    static void checkAge(int age) throws IOException {
        if (age < 18) {                         // Business logic
            // 'throw' physically creates and launches the error
            throw new IOException("Access Denied: Under 18");
        } else {                                //
            System.out.println("Access Granted!");//
        }                                       //
    }                                           //
                                                //
    public static void main(String[] args) {    // Program entry
        try {                                   //
            // We MUST use try-catch because checkAge 'throws' IOException
            checkAge(15);                       // Call the method
        } catch (IOException e) {               // Handle the 'thrown' error
            System.out.println("Error Caught: " + e.getMessage());
        }                                       //
    }                                           //
}                                               //
```

---

## Question 50: Explain how the `try-catch` mechanism works in Java with an example demonstrating multiple catch blocks.

**Topic Introduction: The `try-catch` Mechanism and Multiple Catch Blocks.**

1. **Topic Introduction**: The `try-catch` mechanism is Java's primary safety net, preventing unhandled exceptions from cascading up the call stack and crashing the JVM.
2. **The `try` Block**: The `try` block acts as a monitor. You place "risky" code inside it. If an exception occurs, the JVM instantly halts execution at that exact line.
3. **The `catch` Block**: The `catch` block acts as a rescue net. If an exception is generated in the `try` block, the JVM searches for a `catch` block matching that specific exception type.
4. **Multiple Catch Blocks**: A single `try` block can be followed by multiple sequential `catch` blocks, allowing different recovery strategies for different types of errors.
5. **Sequential Evaluation**: When an error occurs, the JVM evaluates the `catch` blocks from top to bottom, stopping at the very first one that matches the error type.
6. **The Ordering Rule**: Because of sequential evaluation, catch blocks *must* be ordered from the most specific exception (subclass) to the most general exception (superclass).
7. **Compilation Error**: If you put `catch(Exception e)` at the top, it acts as a universal net, catching everything. Any specific catch blocks below it become unreachable, causing a compile-time error.
8. **Execution Guarantee**: Only one single `catch` block will ever be executed per exception event, regardless of how many are defined.
9. **Resumption of Flow**: Once a matching `catch` block finishes executing, the program continues running normally starting from the line immediately below the final `catch` block.
10. **The Universal Catch**: It is common practice to put `catch(Exception e)` at the very bottom of the sequence as a "catch-all" failsafe for unforeseen errors.
11. **Performance**: Entering a `try` block has virtually zero performance cost. The CPU overhead only occurs when an exception is actually thrown and the stack trace is built.
12. **Variable Isolation**: Any variables declared *inside* the `try` block disappear the moment the block exits and cannot be accessed inside the `catch` blocks.

```text
  [ TRY BLOCK ] -> Generates NullPointerException
       |
  [ CATCH (ArithmeticExc e) ] -> No Match, Skipped
       |
  [ CATCH (NullPointerExc e)] -> MATCH! Executes recovery code.
       |
  [ CATCH (Exception e)     ] -> Ignored (already caught)
       |
  [ Normal Program Flow Continues Here ]
```

```java
// Explanation: Multiple Catch Block Flow       //
public class TryCatchDemo {                     // Main class
    public static void main(String[] args) {    // Program entry
        try {                                   // Start monitoring
            int[] arr = new int[5];             // Array of size 5
            String s = null;                    // Null string
                                                //
            // TEST 1: Uncomment to trigger Arithmetic Exception
            // int x = 10 / 0;                  //
                                                //
            // TEST 2: Uncomment to trigger Array Index Exception
            arr[10] = 50;                       //
                                                //
            // TEST 3: Uncomment to trigger Null Pointer Exception
            // int len = s.length();            //
                                                //
        // Catch blocks MUST go from specific to general
        } catch (ArithmeticException e) {       // Specific match 1
            System.out.println("Caught Math Error!");
        } catch (ArrayIndexOutOfBoundsException e) {// Specific match 2
            System.out.println("Caught Array Bounds Error!");
        } catch (Exception e) {                 // Universal failsafe (Superclass)
            System.out.println("Caught an unknown Exception!");
        }                                       //
                                                //
        System.out.println("Program gracefully continues."); // Post-recovery
    }                                           //
}                                               //
```
