# Module 1: Java Basics

This module covers the core concepts of Java programming, its execution architecture, fundamental Object-Oriented Principles, and advanced mechanisms like Exception Handling and Multithreading. 

---

## 1. Introduction to Java and its Features

Java is a platform-independent, object-oriented programming language designed to have as few implementation dependencies as possible. It runs on the principle of **WORA (Write Once, Run Anywhere)**.

### Key Features (Java Buzzwords)
- **Simple**: Syntax is based on C++ but with removed complexities like explicit pointers. It has automatic garbage collection.
- **Object-Oriented**: Organizes software as a combination of objects incorporating both data (state) and behavior (methods).
- **Platform Independent**: Java code is compiled into *bytecode*, which the Java Virtual Machine (JVM) interprets. A software-based platform running atop hardware.
- **Secured**: Operates inside a virtual machine sandbox with mechanisms like Classloader, Bytecode Verifier, and Security Manager.
- **Robust**: Strong memory management, automatic garbage collection, exception handling, and lack of pointers.
- **Architecture-neutral & Portable**: Primitive data types have fixed sizes regardless of the underlying OS architecture.
- **High Performance**: Use of Just-In-Time (JIT) compilers enables high performance.
- **Multithreaded**: Deals with multiple tasks concurrently using threads, sharing a common memory area.
- **Distributed**: Supports RMI and EJB to create applications mapped across networks.

---

## 2. Java Platform Architecture

The Java architectural process involves both **compilation** and **interpretation**.

1. **Compiler**: Takes the `.java` source code and converts it into a machine-independent encoding known as **Bytecode** (`.class` files).
2. **Interpreter (JVM)**: Translates the Bytecode into machine code line-by-line or uses a JIT compiler.

### JVM, JRE, and JDK

| Feature | JDK (Java Development Kit) | JRE (Java Runtime Environment) | JVM (Java Virtual Machine) |
| :--- | :--- | :--- | :--- |
| **Purpose** | Used to develop and execute Java applications. | Creates an environment to execute the code. | An abstract machine providing the runtime environment. |
| **Components** | JRE + Development Tools (compiler `javac`, debugger). | JVM + Class Libraries. | Class Loader, Bytecode Verifier, Execution Engine, JIT. |
| **Dependency** | Platform-dependent. | Platform-dependent. | Platform-independent. |

### Environment Variables: PATH vs CLASSPATH
- **PATH**: Environment setup for the Operating System to locate binary executable files (`java.exe`, `javac.exe`). 
- **CLASSPATH**: Setup specifically for the Java Compiler/JVM to locate compiled `.class` files or `.jar` files.

---

## 3. Object-Oriented Programming (OOP) Principles

A **Class** is a blueprint/template that defines the state and behavior of basic program components (Objects). An **Object** is an instance of a class.

```java
// Defining a class with properties and a method
class Circle {
    // Instance variables defining the "state"
    double radius;

    // Method defining the "behavior"
    double calculateArea() {
        return Math.PI * radius * radius;
    }
}
```

### Core OOP Concepts
- **Encapsulation**: Wrapping data (variables) and code acting on the data (methods) together as a single unit. It protects data from direct external access.
- **Inheritance**: Acquiring properties and behaviors from a parent class to promote code reusability.
- **Polymorphism**: The ability of an object to take on many forms (e.g., method overloading and overriding).
- **Abstraction**: Hiding internal implementation details and showing only necessary functionalities.

---

## 4. Exception Handling

An exception is an unwanted or unexpected event occurring during the execution of a program which disrupts the normal flow. To gracefully handle errors, Java provides five keywords:

- **try-catch:** We use try-catch block for exception handling in our code. `try` is the start of the block and `catch` is at the end of try block to handle the exceptions. We can have multiple catch blocks with a try and try-catch block can be nested also. `catch` block requires a parameter that should be of type Exception.
- **finally:** finally block is optional and can be used only with try-catch block. Since exception halts the process of execution, we might have some resources open that will not get closed, so we can use finally block. finally block gets executed always, whether exception occurred or not.
- **throw:** keyword is used to actively throw exception to the runtime to handle it.
- **throws:** We can provide multiple exceptions in the throws clause and it can be used with main() method also to declare exceptions a method might throw.

### Types of Exceptions
- **Checked Exceptions:** A checked exception is an exception that occurs at the compile time. These exceptions cannot simply be ignored at the time of compilation; the programmer should take care of (handle) these exceptions. Examples: `ClassNotFoundException`, `InterruptedException`.
- **Unchecked Exceptions:** An unchecked exception is an exception that occurs at the time of execution. These are also called as Runtime Exceptions. These include programming bugs, such as logic errors or improper use of an API. Runtime exceptions are ignored at the time of compilation. Examples: `ArithmeticException`, `ArrayIndexOutOfBoundsException`.

### 📌 Activity Question: Bank Account Management System
**Question:** Develop a Bank Account Management System performing deposit/withdrawal operations. Create a custom exception `InsufficientBalanceException`. Use `try-catch-finally` blocks and the `throws` keyword. Ensure a transaction summary is printed in the `finally` block.

```java
// Custom Exception Class
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        // Pass message to superclass Exception
        super(message);
    }
}

// Bank Account Class
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Deposit method
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

    // Withdraw method declaring it 'throws' the custom exception
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            // Actively throwing the exception when condition is met
            throw new InsufficientBalanceException("Error: Withdrawal amount exceeds available balance.");
        }
        balance -= amount;
        System.out.println("Withdrawn: $" + amount);
    }

    public double getBalance() {
        return balance;
    }
}

// Main Execution Class
public class BankSystem {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount(500.0);
        
        try {
            acc.deposit(200.0);
            
            // This withdrawal will succeed
            acc.withdraw(150.0);
            
            // This withdrawal will trigger our custom exception
            acc.withdraw(1000.0);
        } catch (InsufficientBalanceException e) {
            // Handling the exception gracefully
            System.out.println(e.getMessage());
        } finally {
            // Code in finally always executes, making it ideal for summaries or cleanup
            System.out.println("--- Transaction Summary ---");
            System.out.println("Final Balance: $" + acc.getBalance());
        }
    }
}
```
**Expected Terminal Output:**
```text
Deposited: $200.0
Withdrawn: $150.0
Error: Withdrawal amount exceeds available balance.
--- Transaction Summary ---
Final Balance: $550.0
```

---

## 5. Multithreading

**Multithreading** is a process of executing multiple threads simultaneously. A thread is a lightweight sub-process representing a separate path of execution. It saves memory (threads share a common memory area) and maximizes CPU utilization.

### Thread Synchronization
When multiple threads try to access shared resources concurrently, it causes a **Race Condition**. Synchronization resolves this using `wait()`, `notify()`, and `notifyAll()` methods for inter-thread communication.

### 📌 Activity Question: Producer-Consumer Problem
**Question:** Implement the Producer-Consumer problem using Java threads. Use a shared buffer with a fixed size, implement synchronization using `wait()` and `notify()`, and ensure thread safety.

```java
import java.util.LinkedList;

// Shared resource class
class SharedBuffer {
    // Shared list serving as a buffer
    private LinkedList<Integer> buffer = new LinkedList<>();
    // Fixed buffer capacity
    private int capacity = 3;

    // Synchronized method for producing items
    public synchronized void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            // Wait if the buffer is full
            while (buffer.size() == capacity) {
                wait();
            }

            System.out.println("Producer produced: " + value);
            buffer.add(value++); // Add item to buffer
            
            // Notify consumer that item is available
            notify(); 
            // Delay to simulate work and observe output
            Thread.sleep(1000);
        }
    }

    // Synchronized method for consuming items
    public synchronized void consume() throws InterruptedException {
        while (true) {
            // Wait if the buffer is empty
            while (buffer.size() == 0) {
                wait();
            }

            // Remove item from front of buffer
            int val = buffer.removeFirst();
            System.out.println("Consumer consumed: " + val);

            // Notify producer that space is available
            notify(); 
            // Delay to simulate work and observe output
            Thread.sleep(1500); 
        }
    }
}

// Thread Implementation
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        final SharedBuffer sharedBuffer = new SharedBuffer();

        // Creating Producer Thread
        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sharedBuffer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Creating Consumer Thread
        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sharedBuffer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start threads
        producerThread.start();
        consumerThread.start();
    }
}
```
**Expected Terminal Output:**
```text
Producer produced: 0
Consumer consumed: 0
Producer produced: 1
Producer produced: 2
Consumer consumed: 1
Producer produced: 3
Consumer consumed: 2
Producer produced: 4
...
```
*(The threads continue executing concurrently, avoiding race conditions due to synchronized data access).*
