# Module 2: Exception Handling (Simplified)

## 1. What is an Exception?

**Simple Explanation (Universal Analogy):**
Imagine you are walking to school. Suddenly, a tree falls blocking the road! This is an "Exception"—an unwanted, unexpected event that stops your normal journey. "Exception Handling" is simply having a backup plan (like taking a detour through a side street) so you can still reach school instead of just stopping in the middle of the road.

**Exam Definitions & Key Points:**
Write these exact words to get marks:
* An **Exception** is a runtime error that disrupts the normal flow of the program.
* **Exception Handling** ensures a program does not crash abruptly; it provides alternative execution flow.
* All exceptions in Java inherit from the `java.lang.Throwable` superclass.

**Concept Diagram: Exception Hierarchy**
*(Draw this to secure marks!)*
```text
                  [Throwable]
                       |
        +--------------+--------------+
        |                             |
   [Exception]                    [Error]
  (Recoverable)               (Non-Recoverable)
        |                     e.g. OutOfMemory
  +-----+-------------+
  |                   |
[Checked]        [Unchecked]
(Compile-Time)   (Run-Time)
e.g. IOException  e.g. ArithmeticException
```

---

## 2. The 5 Magic Keywords

**Simple Explanation:**
* You `try` to do something risky.
* If it breaks, you `catch` the problem.
* You `finally` cleanup the mess (like washing your hands), no matter what happens.
* If you find a problem yourself, you `throw` it out.
* If you don't want to handle it, your method `throws` it to whoever called it to let them deal with it.

**Key Definitions for Exam:**
* `try`: Block enclosing risky code.
* `catch`: Block that handles the exact exception thrown by the try block.
* `finally`: Block that *always* executes (used for closing files/database).
* `throw`: Used *inside* a method to manually throw a single exception object.
* `throws`: Used in a *method signature* to declare it might throw exceptions.

---

## 3. Checked vs Unchecked Exceptions

| Feature | Checked Exceptions | Unchecked Exceptions |
| :--- | :--- | :--- |
| **When are they checked?** | Compile-time (Java forces you to handle it) | Runtime (Java doesn't force you) |
| **Cause** | Outside your control (e.g. File not found) | Developer/Logic errors (e.g. Divide by zero, bad array index) |
| **Examples** | `IOException`, `SQLException` | `ArithmeticException`, `NullPointerException` |

---

## 4. throw vs throws (Guaranteed 4-5 Mark Question)

| `throw` keyword | `throws` keyword |
| :--- | :--- |
| Used **inside** the method body. | Used in the method **signature** (header). |
| Used to throw a **single** exception manually. | Can declare **multiple** exceptions (comma-separated). |
| Followed by an exception **object/instance** (e.g., `new Exception()`). | Followed by exception **class names** (e.g., `IOException`). |

---

## 5. Custom (User-Defined) Exceptions

**Simple Explanation:**
Sometimes Java's built-in errors aren't enough. If your application only allows users above 18, entering age 16 shouldn't break the computer, but it *is* an error for your specific rule. We create a "Custom Exception" to handle this custom rule.

**Exam Code Snippet:**
*(Memorize this snippet for 10-12 mark coding questions!)*
```java
// 1. Create a custom exception by extending 'Exception'
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message); // Pass message to parent class
    }
}

// 2. Main logic
public class VotingSystem {
    
    // Method that DECLARES it 'throws' the error
    static void checkAge(int age) throws InvalidAgeException {
        if(age < 18) {
            // Manually 'throw' the error
            throw new InvalidAgeException("Not eligible to vote!"); 
        } else {
            System.out.println("Welcome to voting!");
        }
    }

    public static void main(String[] args) {
        try {
            checkAge(16); // This is risky, put in try-catch
        } catch(InvalidAgeException e) {
            System.out.println("Caught Error: " + e.getMessage());
        } finally {
            System.out.println("System cleanup complete.");
        }
    }
}
```

---

### How to Answer Exam Questions on this Topic:
* **For 8-12 marks on Custom Exception:** Write the code snippet above exactly as is. Draw a simple `try-catch` flow if you have time.
* **For Checked vs Unchecked:** *Always* draw a tabular difference and give 2 clear examples of each. Mention compile-time vs run-time heavily.
