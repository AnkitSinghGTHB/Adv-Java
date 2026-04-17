# Module 3: Multithreading (Simplified)

## 1. What is Multithreading?

**Simple Explanation (Universal Analogy):**
Imagine you are preparing a meal. Normal programming (Single thread) means you boil water, wait entirely for it to finish, then chop vegetables, wait for it to finish, and then cook. 
**Multithreading** means you boil the water on one stove, while chopping vegetables at the exact same time. It saves time and maximizes how much work you can do simultaneously. Each individual task (boiling, chopping) is called a **Thread**.

**Exam Definitions & Key Points:**
* **Multithreading:** Concurrent (at the same time) execution of two or more parts of a program.
* **Thread:** A lightweight sub-process; the smallest unit of processing.
* Threads share the same memory area, which makes switching between them very fast.

---

## 2. Creating a Thread (10 Mark Guarantee)

There are 2 ways to create a Thread. Implementing `Runnable` is better because "Java doesn't support multiple inheritance of classes, but supports multiple interfaces".

**Exam Code Snippet (Implementing Runnable):**
```java
// 1. Implement Runnable Interface
class MyTask implements Runnable {
    // 2. You MUST override the run() method
    public void run() {
        System.out.println("Thread is running safely!");
    }
}

public class Main {
    public static void main(String[] args) {
        // 3. Create object of your class
        MyTask task = new MyTask();
        
        // 4. Pass it to a Thread object
        Thread t1 = new Thread(task);
        
        // 5. CAUTION: Always call start(), NEVER call run() directly!
        // start() automatically calls run() in a new thread.
        t1.start(); 
    }
}
```

---

## 3. Thread Life Cycle (Draw this diagram!)

**Concept Diagram:**
```text
         [New]
           |
         start()
           v
       [Runnable] <------------+
           |                   | wake up
      scheduler picks it       |
           v                   |
       [Running] ---------> [Blocked / Waiting]
           |                 (sleep, wait for I/O)
     run() finishes
           v
   [Dead / Terminated]
```

**State Explanations:**
1. **New:** Thread object exists, but `start()` is not called yet.
2. **Runnable:** `start()` called; thread is ready and waiting for CPU time.
3. **Running:** Thread is currently executing in the CPU.
4. **Blocked/Waiting:** Thread paused (`sleep()` or waiting for file).
5. **Dead:** Thread finished execution completely.

---

## 4. Thread Scheduling & Priorities

**Simple Explanation:**
Because you usually have 1 CPU but many threads, the "Thread Scheduler" acts like a traffic policeman. It uses **Priorities** (VIPs cross first) or **Time Slicing** (everyone gets 5 seconds to cross) to decide who goes next.

**Key Exam Points:**
* `Thread.MIN_PRIORITY` = 1
* `Thread.NORM_PRIORITY` = 5 (Default)
* `Thread.MAX_PRIORITY` = 10
* Method to set priority: `threadObj.setPriority(10);`

---

## 5. Thread Synchronization (Mutex / Thread Safety)

**Simple Explanation:**
Imagine two people trying to withdraw ₹500 from the exact same bank account from two different branches at the exact same millisecond. If the balance is ₹600, both might get the money and leave the bank in a loss!
**Synchronization** is putting a locked door on the bank account. When Thread 1 enters, it "locks" the door. Thread 2 must wait outside until Thread 1 completely finishes and leaves.

**Key Tools:**
* **`synchronized` keyword:** Used to lock a method or a block of code so only one thread can use it at a time. This uses an internal "Monitor Lock".

```java
// Exam code to show Synchronization
class BankAccount {
    int balance = 600;
    
    // The 'synchronized' lock prevents 2 threads from entering here together
    public synchronized void withdraw(int amount) {
        if(balance >= amount) {
            balance = balance - amount;
            System.out.println("Withdrawal Successful!");
        }
    }
}
```

### How to Answer Exam Questions on this Topic:
* **Creation Question (12M):** Write the exact `Runnable` code from Section 2. Always emphasize that `start()` must be called to create a new thread stack.
* **Lifecycle (8M):** Draw the ASCII flowchart. Explain the 5 boxes in 5 strict bullet points.
* **Synchronization (10M):** Explain the bank analogy. Define "Monitor Lock". Write the `synchronized` function snippet above.
