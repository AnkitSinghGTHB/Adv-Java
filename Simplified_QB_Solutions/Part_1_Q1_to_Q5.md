# Advanced Java Solutions - Part 1 (Q1 to Q5)

---

## Question 1: Define a class and an object in Java. Explain how to create classes and objects with a suitable example. Also discuss different types of access modifiers and their significance.

**Topic Introduction: Classes, Objects, and Access Modifiers in Java.**

1. **Introduction to Classes**: A class is a blueprint or template in Java that defines the structure, properties (attributes), and behaviors (methods) of a specific type of entity.
2. **Introduction to Objects**: An object is a real-world instance of a class, occupying memory and representing actual data based on the blueprint provided by its class.
3. **Class Declaration**: Classes are declared using the `class` keyword followed by the class name, encapsulating fields and methods inside curly braces `{}`.
4. **Object Creation**: Objects are instantiated dynamically using the `new` keyword, which allocates memory and calls the class constructor to initialize the object.
5. **Memory Allocation**: While a class is a logical construct consuming no memory space, objects are physical entities created in the heap memory during runtime.
6. **Concept of Access Modifiers**: Access modifiers control the visibility and scope of classes, variables, and methods, ensuring data security and encapsulation.
7. **Default Access**: When no modifier is specified, it defaults to package-private, meaning the member is accessible only within the same package.
8. **Private Modifier**: The `private` modifier restricts access strictly to within the same class, providing the highest level of data hiding.
9. **Protected Modifier**: The `protected` modifier allows access within the same package and to subclasses in different packages, facilitating secure inheritance.
10. **Public Modifier**: The `public` modifier makes members accessible from anywhere in the program, offering the widest visibility.
11. **Significance**: Access modifiers form the backbone of encapsulation by preventing unauthorized external modification of sensitive internal object states.
12. **Diagram Representation**: A class acts as a factory producing multiple identical but distinct objects.

```text
  +------------------+         +------------------+
  |  CLASS (Blueprint) |   ==>   | OBJECT 1 (Instance)|
  |  - Attributes    |         |  - State 1       |
  |  - Methods       |         +------------------+
  +------------------+         +------------------+
                               | OBJECT 2 (Instance)|
                               |  - State 2       |
                               +------------------+
```

```java
// Explanation: Creating Class and Object       //
class Car {                                     // Class declaration
    public String model;                        // Public accessible attribute
    private int speed;                          // Private restricted attribute
    
    public void setSpeed(int s) {               // Public method to set speed
        speed = s;                              // Assigning private variable
    }                                           //
    
    public void display() {                     // Public method to display
        System.out.println("Speed: " + speed);  // Printing speed value
    }                                           //
}                                               //
                                                //
public class Main {                             // Main execution class
    public static void main(String[] args) {    // Program entry point
        Car myCar = new Car();                  // Object creation using 'new'
        myCar.model = "Sedan";                  // Accessing public member
        myCar.setSpeed(100);                    // Updating via public method
        myCar.display();                        // Calling behavior method
    }                                           //
}                                               //
```

---

## Question 2: What is inheritance in Java? Explain its types with examples. Additionally, describe the concept of packages and their benefits in Java programming.

**Topic Introduction: Inheritance and Packages.**

1. **Introduction to Inheritance**: Inheritance is an OOP mechanism where a new class (child) acquires the properties and methods of an existing class (parent), promoting code reuse.
2. **Key Keywords**: The `extends` keyword is used to inherit from a class, while `implements` is used to inherit from an interface in Java.
3. **Single Inheritance**: Involves a single child class inheriting from exactly one parent class, establishing a direct one-to-one hierarchical relationship.
4. **Multilevel Inheritance**: A derived class serves as a base class for another new class, creating a chain of inheritance (e.g., A -> B -> C).
5. **Hierarchical Inheritance**: Multiple derived classes inherit from a single common parent class, sharing its foundational properties (e.g., B -> A, C -> A).
6. **Multiple Inheritance Support**: Java does not support multiple inheritance with classes to avoid ambiguity (the Diamond Problem), but achieves it through interfaces.
7. **Introduction to Packages**: A package is a namespace that groups related classes and interfaces together, similar to folders in a file directory.
8. **Types of Packages**: Java provides built-in packages (like `java.util`, `java.io`) and allows developers to create custom user-defined packages using the `package` keyword.
9. **Naming Conventions**: Package names are conventionally written in all lowercase to prevent conflicts with class names and follow reverse domain structures.
10. **Benefit 1 - Organization**: Packages neatly organize large codebases into manageable, modular components, making navigation and maintenance significantly easier.
11. **Benefit 2 - Name Clashes**: Packages prevent naming collisions, allowing two classes with the exact same name to coexist if they reside in different packages.
12. **Benefit 3 - Access Protection**: Packages provide a boundary for access control, allowing the use of default and protected modifiers to hide internal implementations.

```text
  [SINGLE]        [MULTILEVEL]      [HIERARCHICAL]
   Parent             Parent             Parent
     |                  |                 /  \
   Child              Child 1        Child 1  Child 2
                        |
                      Child 2
```

```java
// Explanation: Single Inheritance Demo         //
class Animal {                                  // Parent class declaration
    public void eat() {                         // Common behavior method
        System.out.println("Eating...");        // Printing message
    }                                           //
}                                               //
                                                //
class Dog extends Animal {                      // Child class inherits Parent
    public void bark() {                        // Specific child behavior
        System.out.println("Barking...");       // Printing message
    }                                           //
}                                               //
                                                //
public class Main {                             // Main execution class
    public static void main(String[] args) {    // Program entry point
        Dog myDog = new Dog();                  // Creating child object
        myDog.eat();                            // Inherited method call
        myDog.bark();                           // Child's own method call
    }                                           //
}                                               //
```

---

## Question 3: Discuss the concept of multithreading in Java. Explain the thread life cycle and demonstrate how to create a multithreaded program. Also explain object serialization with an example.

**Topic Introduction: Multithreading, Thread Life Cycle, and Serialization.**

1. **Introduction to Multithreading**: Multithreading is a core Java feature that allows concurrent execution of two or more parts of a program to maximize CPU utilization.
2. **Concept of Thread**: A thread is the smallest independent unit of processing within a program. Multiple threads run simultaneously within a single process.
3. **Thread Creation**: Threads are created either by extending the `Thread` class or by implementing the `Runnable` interface and overriding the `run()` method.
4. **New State**: When a thread object is instantiated but the `start()` method hasn't been invoked yet, it remains in the newly created state.
5. **Runnable State**: Once `start()` is called, the thread becomes runnable, waiting in a queue for the Thread Scheduler to allocate CPU time.
6. **Running State**: The thread scheduler selects a thread from the runnable pool, and its `run()` method begins executing instructions actively on the CPU.
7. **Blocked/Waiting State**: A thread temporarily pauses execution, waiting for I/O operations, a lock, or a specified time interval (via `sleep()` or `wait()`).
8. **Terminated State**: The thread gracefully exits when its `run()` method completes execution or is abruptly stopped due to an unhandled exception.
9. **Introduction to Serialization**: Object Serialization is the process of converting an object's state in memory into a byte stream for storage or transmission.
10. **Deserialization**: The reverse process, Deserialization, reconstructs the object in memory from the serialized byte stream without invoking the constructor.
11. **Serializable Interface**: To make an object serializable, its class must implement the `java.io.Serializable` marker interface, which contains no methods.
12. **Transient Keyword**: If a class contains sensitive or non-serializable fields (like passwords), marking them with `transient` prevents them from being serialized.

```text
  [NEW] ---> [RUNNABLE] <---> [RUNNING] ---> [TERMINATED]
                 ^               |
                 |               v
                 +-- [BLOCKED] --+
```

```java
// Explanation: Simple Multithreading Demo      //
class MyThread extends Thread {                 // Extending Thread class
    public void run() {                         // Overriding run method
        try {                                   // Exception handling block
            System.out.println("Thread Runs");  // Thread task execution
            Thread.sleep(500);                  // Pausing thread execution
        } catch (InterruptedException e) {      // Catching interrupt issue
            System.out.println("Interrupted");  // Error message
        }                                       //
    }                                           //
}                                               //
                                                //
public class Main {                             // Main execution class
    public static void main(String[] args) {    // Program entry point
        MyThread t1 = new MyThread();           // Creating thread object
        t1.start();                             // Moving to Runnable state
    }                                           //
}                                               //
```

---

## Question 4: Develop a Java program where one thread produces data and another consumes it using some methods. Include proper exception handling mechanisms.

**Topic Introduction: Inter-thread Communication (Producer-Consumer).**

1. **Topic Introduction**: The Producer-Consumer problem is a classic synchronization scenario where two threads share a common, fixed-size buffer or queue.
2. **The Producer**: The producer thread's role is to generate data and place it into the shared buffer continuously at a specific rate.
3. **The Consumer**: The consumer thread's role is to remove and process data from the shared buffer continuously at its own processing rate.
4. **The Core Problem**: A critical issue arises if the producer tries to add data when the buffer is full, or the consumer tries to remove data when it is empty.
5. **Thread Communication**: Java resolves this using inter-thread communication methods provided by the `Object` class: `wait()`, `notify()`, and `notifyAll()`.
6. **The wait() Method**: When called, `wait()` immediately releases the lock on the shared object and puts the thread into a waiting state until notified.
7. **The notify() Method**: When called, `notify()` wakes up a single arbitrary thread that is waiting on the object's monitor, allowing it to resume execution.
8. **Handling Full Buffer**: If the buffer is full, the producer thread invokes `wait()`, pausing its operation until the consumer removes an item.
9. **Handling Empty Buffer**: If the buffer is empty, the consumer thread invokes `wait()`, pausing its operation until the producer adds a new item.
10. **Synchronized Block**: All inter-thread communication (`wait`/`notify`) must occur strictly within a `synchronized` block or method to guarantee mutual exclusion.
11. **Exception Handling**: The `wait()` and `sleep()` methods explicitly throw `InterruptedException`, forcing the developer to handle thread interruptions gracefully.
12. **Program Logic**: A simple boolean flag (`hasData`) can effectively track whether the shared buffer is currently empty or contains an unconsumed item.

```text
  [PRODUCER] ---> ( Puts Item ) ---> [SHARED BUFFER]
                                            |
  [CONSUMER] <--- ( Gets Item ) <-----------+
  
  Condition: Wait if Full (Producer) | Wait if Empty (Consumer)
```

```java
// Explanation: Producer Consumer Problem       //
class SharedData {                              // Shared resource class
    private int data;                           // Variable to store data
    private boolean hasData = false;            // Flag indicating data state
    
    public synchronized void produce(int d) {   // Synchronized producer method
        try {                                   // Exception handling start
            while (hasData) wait();             // Wait if data is not consumed
            data = d;                           // Put new data in buffer
            System.out.println("Produced: "+d); // Print produced item
            hasData = true;                     // Update flag state
            notify();                           // Wake up consumer thread
        } catch (InterruptedException e) {      // Catch thread interrupt
            System.out.println("Interrupted");  // Error message
        }                                       //
    }                                           //
                                                //
    public synchronized void consume() {        // Synchronized consumer method
        try {                                   // Exception handling start
            while (!hasData) wait();            // Wait if buffer is empty
            System.out.println("Consumed: "+data);// Print consumed item
            hasData = false;                    // Update flag state
            notify();                           // Wake up producer thread
        } catch (InterruptedException e) {      // Catch thread interrupt
            System.out.println("Interrupted");  // Error message
        }                                       //
    }                                           //
}                                               //
```

---

## Question 5: Create a multithreaded Java program to illustrate thread synchronization. Ensure that exceptions are handled gracefully.

**Topic Introduction: Thread Synchronization.**

1. **Topic Introduction**: Thread synchronization is a mechanism in Java that ensures that two or more concurrent threads do not simultaneously execute a specific critical section.
2. **The Race Condition**: When multiple threads attempt to read, modify, and write shared data concurrently without coordination, the final outcome becomes unpredictable.
3. **Data Inconsistency**: Without synchronization, threads might overwrite each other's updates, leading to corrupt or inaccurate data within the application state.
4. **Object Locks**: Java implements synchronization based on the concept of monitors (or locks), where every object has a single internal, intrinsic lock associated with it.
5. **Synchronized Keyword**: The `synchronized` keyword acts as a gatekeeper, allowing only the thread that acquires the object's lock to enter the designated block.
6. **Synchronized Methods**: When an entire method is declared as `synchronized`, the thread automatically acquires the lock of the invoking object (`this`).
7. **Synchronized Blocks**: For finer control, a `synchronized` block allows locking a specific object for only a tiny section of code, improving overall performance.
8. **Thread Blocking**: If Thread B tries to enter a synchronized method already locked by Thread A, Thread B is blocked and forced to wait until A finishes.
9. **Releasing the Lock**: The thread automatically releases the lock when it successfully exits the synchronized method or block, allowing the next waiting thread to proceed.
10. **Graceful Exception Handling**: It is critical to wrap synchronized logic in `try-catch` blocks; even if an exception occurs, Java guarantees the lock is safely released.
11. **Deadlock Risk**: While synchronization solves data inconsistency, poor design can lead to deadlocks, where threads wait endlessly for locks held by each other.
12. **Real-world Example**: Synchronization is vital in scenarios like bank transactions, where a joint account cannot be debited simultaneously by two ATM machines.

```text
  [Thread 1] ---> | LOCK (Acquired) | ---> [CRITICAL SECTION]
                                                    |
  [Thread 2] ---> | LOCK (Waiting)  | <-------------+ (Must Wait)
  
  Only one thread operates on the shared resource at a time.
```

```java
// Explanation: Thread Synchronization Demo     //
class Counter {                                 // Shared counter resource
    private int count = 0;                      // Initialized count variable
                                                //
    public synchronized void increment() {      // Synchronized critical section
        try {                                   // Start exception handling
            count++;                            // Increment count value
            System.out.println("Count: "+count);// Print current count
            Thread.sleep(100);                  // Simulate delay
        } catch (InterruptedException e) {      // Catch potential interrupts
            System.out.println("Error details");// Handle gracefully
        }                                       // Lock released automatically
    }                                           //
}                                               //
                                                //
class CounterThread extends Thread {            // Thread class definition
    Counter counter;                            // Reference to shared counter
    CounterThread(Counter c) { counter = c; }   // Constructor initialization
    public void run() {                         // Thread task entry point
        for (int i = 0; i < 3; i++)             // Repeat 3 times per thread
            counter.increment();                // Call synchronized method
    }                                           //
}                                               //
                                                //
public class SyncDemo {                         // Main execution class
    public static void main(String[] args) {    // Program entry point
        Counter shared = new Counter();         // Shared resource object
        CounterThread t1 = new CounterThread(shared); // Thread 1
        CounterThread t2 = new CounterThread(shared); // Thread 2
        t1.start();                             // Start Thread 1
        t2.start();                             // Start Thread 2
    }                                           //
}                                               //
```
