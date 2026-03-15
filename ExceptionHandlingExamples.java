/**
 * This class covers common exam programming questions related to Exception Handling.
 * 
 * 1. Simple try-catch
 * 2. Unchecked Exception Handling (ArithmeticException)
 * 3. Checked Exception Handling & throws keyword (InterruptedException)
 * 4. Custom User-Defined Exception
 */

// Custom Exception Class (Important for 10-mark questions)
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        // Pass the error message to the parent Exception class
        super(message);
    }
}

public class ExceptionHandlingExamples {

    // Method demonstrating "throws" for a Checked Exception
    // Thread.sleep() throws InterruptedException which represents a checked exception
    public static void pauseExecution() throws InterruptedException {
        System.out.println("Pausing for 1 second...");
        Thread.sleep(1000); 
    }

    // Method demonstrating custom exception throwing
    public static void checkVotingEligibility(int age) throws InvalidAgeException {
        if (age < 18) {
            // Throw custom exception
            throw new InvalidAgeException("Age is less than 18. Not eligible to vote.");
        } else {
            System.out.println("Welcome to voting.");
        }
    }

    public static void main(String[] args) {
        
        System.out.println("=== 1. ArithmeticException Example ===");
        try {
            // This will cause an ArithmeticException (Unchecked Exception)
            int result = 10 / 0; 
            System.out.println("Result: " + result); // This line is skipped
        } catch (ArithmeticException e) {
            System.out.println("Error caught: Cannot divide by zero! (" + e.getMessage() + ")");
        } finally {
            // The finally block always executes
            System.out.println("Finally block executed for arithmetic operation.\n");
        }

        
        System.out.println("=== 2. Checked Exception with throws Example ===");
        try {
            // Calling a method that declares 'throws InterruptedException'
            pauseExecution(); 
            System.out.println("Resumed execution.");
        } catch (InterruptedException e) {
            System.out.println("Error caught: Thread was interrupted.");
        }


        System.out.println("\n=== 3. Custom/User-Defined Exception Example ===");
        try {
            // Testing with an invalid age to trigger the exception
            checkVotingEligibility(15); 
        } catch (InvalidAgeException e) {
            System.out.println("Custom Exception caught: " + e.getMessage());
            // Helpful to show stack trace in exams if requested
            // e.printStackTrace(); 
        }
        
        try {
            // Testing with a valid age
            checkVotingEligibility(21); 
        } catch (InvalidAgeException e) {
            System.out.println("Custom Exception caught: " + e.getMessage());
        }
        
    }
}

/*
EXPECTED OUTPUT:
=== 1. ArithmeticException Example ===
Error caught: Cannot divide by zero! (/ by zero)
Finally block executed for arithmetic operation.

=== 2. Checked Exception with throws Example ===
Pausing for 1 second...
Resumed execution.

=== 3. Custom/User-Defined Exception Example ===
Custom Exception caught: Age is less than 18. Not eligible to vote.
Welcome to voting.

EXAM EXPLANATION:
1. `ArithmeticException` is a runtime (unchecked) exception. The `catch` block catches it, preventing a crash.
2. The `finally` block demonstrates that cleanup code runs regardless of exceptions.
3. `pauseExecution()` uses the `throws` keyword because `Thread.sleep` throws a Checked Exception (`InterruptedException`). Checked exceptions MUST be handled or declared.
4. `InvalidAgeException` is a custom Exception (Checked because it extends `Exception`). We use `throw new` to trigger it manually when our business logic (age < 18) fails.
*/
