# Module: Exception Handling

## 1. Introduction to Exception Handling
**Definition:**
An **Exception** is an unwanted or unexpected event occurring during the execution of a program (at runtime) that disrupts the normal flow of instructions. Exception Handling is a mechanism to handle runtime errors such as `ClassNotFoundException`, `IOException`, `SQLException`, `ArithmeticException`, etc.

**Key Points:**
* It is a core feature of robust programming.
* Ensures the program does not terminate abruptly.
* Restores normal program execution flow.
* All exception classes are subtypes of `java.lang.Throwable`.

---

## 2. Keywords used in Exception Handling
| Keyword | Description |
| :--- | :--- |
| `try` | Used to enclose the code that might throw an exception. Must be followed by either `catch` or `finally`. |
| `catch` | Used to handle the exception. It must be preceded by a `try` block. |
| `finally` | Used to execute important code (like closing files, database connections) regardless of whether an exception occurs or not. |
| `throw` | Used to explicitly throw an exception object. |
| `throws` | Used in the method signature to declare that multiple exceptions can occur. |

### Deep Dive: `throw` vs `throws`
*(Very frequently asked as a direct comparison question)*

| Feature | `throw` keyword | `throws` keyword |
| :--- | :--- | :--- |
| **Definition** | Used to explicitly throw a single exception from within a method. | Used in the method signature to declare that the method might throw exceptions. |
| **Location** | Used *inside* the method body. | Used with the method signature (header). |
| **Quantity** | Can only throw *one* exception at a time (e.g., `throw new IOException();`). | Can declare *multiple* exceptions, separated by commas (e.g., `throws IOException, SQLException`). |
| **Instance vs Class** | Followed by an *instance* of an Exception class. | Followed by Exception *class names*. |
| **Usage** | Primarily used to throw custom/user-defined exceptions or explicitly trigger built-in exceptions based on logic (e.g., input validation). | Used to delegate the responsibility of exception handling to the caller method (usually used for Checked Exceptions). |

**Example of `throw`:**
```java
void checkAge(int age) {
    if(age < 18) {
        throw new ArithmeticException("Not eligible"); // Explicitly throwing an exception instance
    }
}
```

**Example of `throws`:**
```java
void readFile() throws IOException {
    // Method might throw an IOException, passing responsibility to the caller
    FileReader fr = new FileReader("file.txt"); 
}
```

---

## 3. Checked vs. Unchecked Exceptions
*(Important for 5 / 10 Mark Questions)*

| Feature | Checked Exceptions | Unchecked Exceptions |
| :--- | :--- | :--- |
| **Verification** | Checked at compile-time by the compiler. | Checked at runtime, not at compile-time. |
| **Cause** | Conditions outside the control of the program (e.g., File I/O). | Programming bugs, logical errors (e.g., dividing by zero). |
| **Handling Requirement** | Must be handled using `try-catch` or declared with `throws`. | Handling is optional (though best practice). |
| **Class Hierarchy** | Direct subclasses of `Exception` (except `RuntimeException`). | Subclasses of `RuntimeException`. |
| **Examples** | `IOException`, `SQLException`, `ClassNotFoundException` | `ArithmeticException`, `NullPointerException`, `ArrayIndexOutOfBoundsException` |

---

## 4. User-Defined (Custom) Exceptions
*(Frequently asked in 10 Mark Programming Questions)*

**Definition:**
Java allows developers to create their own exceptions by extending the `Exception` class (for checked exceptions) or `RuntimeException` class (for unchecked exceptions).

**Steps to create a custom exception:**
1. Create a class that extends `Exception` or `RuntimeException`.
2. Provide a parameterized constructor that calls the superclass constructor `super(message)`.
3. Use the `throw` keyword to throw instances of this custom exception based on your logic.

---

## 5. Typical Exam Questions ("Test Me")

### 2 Mark Questions:
1. What is the difference between `throw` and `throws`?
2. What is the purpose of the `finally` block?
3. What is an Exception in Java?

### 5 Mark Questions:
1. Explain the Exception class hierarchy in Java with a neat diagram description.
2. Differentiate between Checked and Unchecked Exceptions with examples.
3. Explain the use of multiple `catch` blocks with an appropriate code snippet.

### 10/12 Mark Questions:
1. What is Exception Handling? Explain the 5 keywords used in exception handling with suitable code examples.
2. What are User-Defined Exceptions? Write a Java program to accept user age. If the age is less than 18, throw a custom exception `InvalidAgeException` with a proper message. Otherwise, print "Welcome to voting".

---

## 6. Important Exam Tips
* When asked about Custom Exceptions, ALWAYS write a full Java class for the exception and another class to test it.
* A `finally` block will always execute, except when `System.exit(0)` is called in the `try` or `catch` block. This is a favorite trick question.
* Be sure to memorize the hierarchy: `Object -> Throwable -> Exception -> RuntimeException`.
