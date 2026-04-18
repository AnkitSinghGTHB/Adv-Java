# Activity 1: Exception Handling & Multithreading

---

### **Q1) Develop a Bank Account Management System that performs deposit and withdrawal operations.**
*   **Create a custom exception `InsufficientBalanceException`.**
*   **Throw the exception when a withdrawal amount exceeds the available balance.**
*   **Use `try-catch-finally` blocks to handle exceptions gracefully.**
*   **Use the `throws` keyword to propagate exceptions from methods.**
*   **Ensure that a transaction summary is printed in the `finally` block.**

**Answer:**

**1. Custom Exception Class**
In Java, a **Custom Exception** is created by extending the `Exception` class. This allows us to create domain-specific error conditions, like when an account withdrawal exceeds the balance.

**2. The System Implementation**
The program demonstrates **Graceful Error Handling** by propagating the exception using `throws` at the method level, and handling it using `try-catch` at the caller level. The `finally` block **guarantees execution** regardless of whether an exception occurs, which makes it perfect for printing a summary or closing resources.

```java
// 1. Defining the Custom Exception
// EXAMINER NOTE: Extends Exception (Checked Exception)
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message); // Pass message to superclass constructor
    }
}

// 2. The Bank Account Class
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited Rs. " + amount);
    }

    // EXAMINER NOTE: 'throws' keyword used to propagate the exception
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            // EXAMINER NOTE: 'throw' keyword used to trigger exception
            throw new InsufficientBalanceException("Failed: Insufficient balance. Current Balance is Rs. " + balance);
        }
        balance -= amount;
        System.out.println("Withdrawn Rs. " + amount);
    }

    public double getBalance() {
        return balance;
    }
}

// 3. The Main Execution
public class BankAccountManagementSystem {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(5000); // Initial balance 5000
        
        System.out.println("--- Starting Transaction ---");
        
        // EXAMINER NOTE: The try-catch-finally structure
        try {
            account.deposit(2000); // Becomes 7000
            account.withdraw(10000); // Will trigger an Exception
            // Lines below won't execute if exception occurs
            account.withdraw(500); 
        } 
        catch (InsufficientBalanceException e) {
            // Graceful handling of the thrown exception
            System.err.println("Transaction Error: " + e.getMessage());
        } 
        finally {
            // EXAMINER NOTE: The finally block ALWAYS executes, perfect for summaries
            System.out.println("--- Transaction Summary ---");
            System.out.println("Final Account Balance: Rs. " + account.getBalance());
            System.out.println("---------------------------");
        }
    }
}
```

---

### **Q2) Implement the Producer–Consumer problem using Java threads.**
*   **Use a shared buffer with a fixed size.**
*   **Implement synchronization using `wait()`, `notify()`, or `notifyAll()`.**
*   **Ensure thread safety and avoid race conditions.**
*   **Demonstrate correct inter-thread communication.**

**Answer:**

**1. Inter-Thread Communication Concept**
The Producer-Consumer problem is a classic example of a **multi-process synchronization problem**. 
*   The **Producer** generates data and places it into a shared buffer. 
*   The **Consumer** removes data from the buffer.
*   **Race Conditions** are avoided by making methods `synchronized`.
*   **`wait()`** is used to pause a thread if the buffer is full (for producer) or empty (for consumer).
*   **`notify()`** is used to wake up a waiting thread once a condition changes.

**Flow Diagram:**
```text
  [Producer Thread] ---> (If Full: wait()) ---> [ Buffer ] <--- (If Empty: wait()) <--- [Consumer Thread]
                             |                     ^
                          notify()              notify()
```

**2. The SharedBuffer Class and Threads Implementation**

```java
import java.util.LinkedList;
import java.util.Queue;

// 1. The Shared Resource
class SharedBuffer {
    private Queue<Integer> buffer = new LinkedList<>();
    private final int MAX_SIZE = 5; // Fixed size buffer

    // EXAMINER NOTE: synchronized keyword ensures thread safety
    public synchronized void produce(int item) throws InterruptedException {
        // If buffer is full, the producer must wait
        while (buffer.size() == MAX_SIZE) {
            System.out.println("Buffer is full. Producer waiting...");
            wait(); // Temporarily releases the lock and waits
        }
        
        buffer.add(item);
        System.out.println("Produced: " + item);
        
        // Notify the consumer that an item is now available
        notify(); // Wakes up one waiting thread (the consumer)
    }

    public synchronized void consume() throws InterruptedException {
        // If buffer is empty, the consumer must wait
        while (buffer.isEmpty()) {
            System.out.println("Buffer is empty. Consumer waiting...");
            wait(); // Temporarily releases the lock and waits
        }
        
        int item = buffer.remove();
        System.out.println("Consumed: " + item);
        
        // Notify the producer that space is now available
        notify(); // Wakes up one waiting thread (the producer)
    }
}

// 2. The Producer Thread
class Producer extends Thread {
    SharedBuffer buffer;

    Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 1; i <= 8; i++) {
                buffer.produce(i);
                Thread.sleep(100); // Simulate time taken to produce
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// 3. The Consumer Thread
class Consumer extends Thread {
    SharedBuffer buffer;

    Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 1; i <= 8; i++) {
                buffer.consume();
                Thread.sleep(300); // Consume slower than produce to force waiting
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// 4. Main Class Execution
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        SharedBuffer sharedBuffer = new SharedBuffer();
        
        Producer p = new Producer(sharedBuffer);
        Consumer c = new Consumer(sharedBuffer);
        
        p.start();
        c.start();
    }
}
```

**Why this code is optimal for an exam:**
*   Uses a **bounded queue (`LinkedList`)** with a maximum size parameter.
*   **`while` loop** is used with `wait()` to repeatedly check the condition upon awakening (Spurious wakeup prevention).
*   **`Thread.sleep`** makes it easy to trace the console output and prove synchronization behavior.
