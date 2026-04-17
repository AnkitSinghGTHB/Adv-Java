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

**Simple Explanation:**
A **Checked Exception** is a strict rule checked *before* you run the program. It's like a strict guard at the door saying, "You can't go to the beach until you pack an umbrella." Java forces you to prepare for bad weather.
An **Unchecked Exception** happens *while* the program is running. It's like suddenly tripping on a rock while running on the beach. The guard didn't warn you about the rock because you should have been watching your own step!

**Concept Diagram: Checked vs Unchecked**

```text
      [Checked Exception]                [Unchecked Exception]
   (Guarded Before Running)             (Happens While Running)

    [Java Compiler Guard]                      (Running)
             |                                    |
  "Hold on! Did you prepare             *Trips on a logical rock*
   for a missing file?"                      "Ouch! Crash!"
             |                                    |
   MUST use try-catch here.              Your math or logic was bad.
```

**Key Differences (10 Points for Exams):**

| Point | Feature | Checked Exceptions (Stop & Check) | Unchecked Exceptions (Trips & Falls) |
| :--- | :--- | :--- | :--- |
| **1** | **When does it happen?** | **Compile-Time:** Checked *before* running. | **Run-Time:** Happens *while* running. |
| **2** | **Does Java force you?** | Yes. It refuses to start unless you write `try-catch`. | No. Java trusts that your logic is correct. |
| **3** | **Who is at fault?** | Usually outside factors (e.g., missing network, no file). | Usually the programmer's fault (e.g., dividing by zero). |
| **4** | **What do they inherit?** | They directly extend the `Exception` class. | They extend the `RuntimeException` class. |
| **5** | **Can you ignore them?** | Absolutely not. You must handle them immediately. | Yes, but the program will crash if you do. |
| **6** | **Predictability** | High predictability (we know a file might be missing). | Handled by good coding logic, hard to predict completely. |
| **7** | **Examples** | `IOException` (File not found), `SQLException`. | `ArithmeticException` (Math error), `NullPointerException`. |
| **8** | **Handling Approach** | Focuses on catching outside failures cleanly. | Focuses on fixing bad code and checking if variables are valid. |
| **9** | **Required Keywords** | Either `try-catch` block or `throws` declaration. | No keywords required (but highly recommended to fix). |
| **10** | **Recovery** | Highly recoverable (e.g., ask user to pick a new file path). | Harder to recover automatically; usually needs an app fix. |

**Real-World Example:**

*   **Checked Exception (`FileNotFound`):** Your app tries to read a photo from a USB drive, but the user unplugged the drive! This is outside your control, so Java forces you to prepare a generic "USB not found" message.
*   **Unchecked Exception (`Arithmetic`):** Your app calculates the average score by dividing total marks by total students. But the number of students is exactly `0`. Dividing by zero makes the computer panic and crash. You should have written an `if (students > 0)` check!

---

## 4. throw vs throws (Guaranteed 4-5 Mark Question)

**Simple Explanation:**
The **`throw`** word is an action. It's like physically throwing a red flag during a game when you spot a foul. You are forcefully pushing an error out right now.
The **`throws`** word is a warning sign. It's hung outside a room saying, "Warning: If you enter, flying rocks might hit you." It warns anyone calling your code that they must be prepared to catch errors.

**Concept Diagram: throw vs throws**

```text
        [ throw ]                            [ throws ]
  (The Physical Action)                 (The Warning Sign)

   Inside the room:                 Hung on the door: 
   "I caught you speeding!"         "Danger: Speeders inside!"
          |                                  |
     throw new Error();             void drive() throws Error
```

**Key Differences (10 Points for Exams):**

| Point | Feature | `throw` (The Action) | `throws` (The Warning Sign) |
| :--- | :--- | :--- | :--- |
| **1** | **What does it do?** | Actually triggers and pushes an error right now. | Simply warns others that an error might happen here. |
| **2** | **Where is it placed?** | Deep **inside** the curly braces `{}` of your method. | Placed perfectly on the method **header/signature**. |
| **3** | **How many at once?** | Can only throw exactly **one** single error at a time. | Can warn about **multiple** errors using commas (A, B, C). |
| **4** | **What comes after it?** | A brand new object (e.g., `throw new Exception();`). | Just the class name (e.g., `throws Exception`). |
| **5** | **Custom Rules?** | Heavily used to trigger your own custom rules (like Age < 18). | Used to quietly pass built-in Java errors to someone else. |
| **6** | **Execution Flow** | Immediately stops the current line of code and jumps out. | Does not stop code; just sets up the safety net. |
| **7** | **Can it be alone?** | Yes, it physically causes the crash if unattended. | No, it just shifts responsibility to whoever called it. |
| **8** | **Grammar** | Singular action word (throw). | Plural descriptive word (throws). |
| **9** | **Unchecked Errors?** | Yes, you can manually `throw` an unchecked error if you want. | No need to use `throws` for unchecked errors. |
| **10** | **Main Purpose** | Creating the actual physical problem object. | Delegating responsibility to handle the problem to someone else. |

**Real-World Example:**

*   **`throw` Example (Action):** You write a bank app. If a user tries to withdraw $100 but only has $10, you actively write `throw new InsufficientFundsException()`. You are forcefully pulling the alarm.
*   **`throws` Example (Warning):** You write a `readFile()` tool. It has `throws IOException` attached to its name. This explicitly warns every other developer: "Hey, use `try-catch` when using my tool, because the file might be missing!"

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
