# Module: Multithreading

## 1. Introduction to Multithreading
**Definition:**
Multithreading is a Java feature that allows concurrent execution of two or more parts of a program to maximize the utilization of CPU. Each part of such a program is called a **Thread**. Threads are lightweight sub-processes.

**Key Points:**
* Threads share a common memory area.
* They save memory and improve application performance.
* Useful in multimedia, web servers, and GUI applications.
* Context-switching between threads is fast.

---

## 2. Creating a Thread in Java
*(Extremely Important for Programming Questions)*

There are two ways to create a thread in Java:
1. **By extending the `Thread` class**
2. **By implementing the `Runnable` interface** (Preferred method)

### Extending `Thread` vs Implementing `Runnable`
| Feature | Extending `Thread` Class | Implementing `Runnable` Interface |
| :--- | :--- | :--- |
| **Inheritance** | Cannot extend any other class (Java doesn't support multiple inheritance). | Can extend another class because we only implement an interface. |
| **Resource Sharing** | Each thread creates a unique object and associates with it. | Multiple threads can share the same `Runnable` instance. |
| **Coupling** | tightly coupled, as the task and thread are merged. | loosely coupled, separating the thread from the actual task behavior. |

---

## 3. Thread Life Cycle states
*(Common 5 or 10 Mark Theory Question)*

A thread in Java goes through a lifecycle, which contains 5 states:
1. **New:** A thread is created but the `start()` method has not been invoked yet.
2. **Runnable:** The thread is executing or ready for execution in the CPU.
3. **Blocked:** The thread is blocked waiting for a monitor lock (e.g., trying to access synchronized code).
4. **Waiting / Timed Waiting:** The thread is waiting (indefinitely or for a specified time) for another thread to perform a specific action (e.g., `sleep()` or `wait()`).
5. **Terminated / Dead:** The thread has completed its execution or terminated due to an exception.

---

## 4. Thread Scheduling and Priorities
**Thread Scheduler:**
The Thread Scheduler is part of the JVM that decides which thread should run. It uses two scheduling mechanisms:
*   **Preemptive Scheduling:** The highest priority task executes until it enters a waiting or dead state, or until a higher priority task comes into existence.
*   **Time Slicing:** A task executes for a predefined slice of time and then re-enters the pool of ready tasks.

**Thread Priority:**
Every thread has a priority, represented by an integer from 1 to 10. The thread scheduler uses priorities to decide when each thread should be allowed to run.
*   `Thread.MIN_PRIORITY` (1)
*   `Thread.NORM_PRIORITY` (5) - Default
*   `Thread.MAX_PRIORITY` (10)
Use `t.setPriority(int)` to change a thread's priority.

---

## 5. Important Thread Methods: `sleep()` and `join()`
*   **`sleep(long millis)`**: Causes the currently executing thread to pause (temporarily cease execution) for the specified number of milliseconds. It is a static method and always throws an `InterruptedException` (a Checked Exception).
*   **`join()`**: Waits for a thread to die. If `t.join()` is called, the current thread (the one making the call) will completely pause its execution until thread `t` has finished its execution.

---

## 6. Thread Synchronization
**Definition:**
Synchronization is the capability to control the access of multiple threads to any shared resource. It prevents thread interference and memory consistency errors.

**How it works:**
Every object in Java has a unique lock (monitor). If a thread wants to execute a `synchronized` method or block, it must first acquire the lock of that object.

**Types:**
1. Synchronized Method
2. Synchronized Block
3. Static Synchronization

---

## 7. Typical Exam Questions ("Test Me")

### 2 Mark Questions:
1. What is the difference between `start()` and `run()` methods?
2. What is thread priority? How is it set?
3. What is a Thread in Java?

### 5 Mark Questions:
1. Explain the Thread Life Cycle with a neat diagram description.
2. Differentiate between multiprogramming and multithreading.
3. Explain the `sleep()` and `join()` methods of the Thread class with a simple code snippet.

### 10/12 Mark Questions:
1. Explain two different ways to create a Thread in Java with suitable examples. Which method is preferred and why?
2. What is Thread Synchronization? Write a Java program demonstrating thread interference and how to fix it using the `synchronized` keyword.
3. What is Thread Scheduling? Explain Preemptive vs Time Slicing scheduling.

---

## 6. Important Exam Tips
* When writing multithreading code in an exam, always remember to call `t.start()` instead of `t.run()`. Calling `run()` directly will execute the code in the main thread (normal method call), completely defeating the purpose of multithreading.
* Thread Priorities range from 1 (`MIN_PRIORITY`) to 10 (`MAX_PRIORITY`), with 5 being the `NORM_PRIORITY`.
* It is a good practice to mention that `sleep()` throws an `InterruptedException` which MUST be handled.
