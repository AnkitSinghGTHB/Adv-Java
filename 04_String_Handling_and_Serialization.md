# Module 1: String Handling, Wrapper Classes & Serialization

This study guide covers String manipulation, Wrapper classes, Mutex (Synchronization concept), and Java Serialization for 10-12 mark exam questions.

---

## 1. String vs StringBuffer

In Java, strings are treated as objects. The `java.lang` package provides two heavily used classes for string manipulation: `String` and `StringBuffer`.

| Feature | `String` | `StringBuffer` |
| :--- | :--- | :--- |
| **Mutability** | **Immutable**: Once a String object is created, its data cannot be changed. Any modification creates a brand new String object. | **Mutable**: The data within the object can be changed without creating a new object. |
| **Performance** | Slower when performing many concatenations, as it creates many objects in the string pool. | Faster for string manipulations (appending, inserting). |
| **Thread Safety** | Immutable, so it is inherently thread-safe. | Thread-safe (methods are synchronized). Only one thread can call its methods at a time. |
| **Syntax** | `String s = "Hello";` | `StringBuffer sb = new StringBuffer("Hello");` |

### Common Methods
*   **String:** `length()`, `charAt(index)`, `substring(begin, end)`, `concat(str)`, `equals(Object anObject)`, `compareTo(String anotherString)`.
*   **StringBuffer:** `append(String str)`, `insert(offset, str)`, `replace(start, end, str)`, `delete(start, end)`, `reverse()`.

---

## 2. Wrapper Classes
**Definition:**
Wrapper classes provide a way to use primitive data types (`int`, `char`, `float`, etc.) as **objects**. 

**Need for Wrapper Classes:**
1.  **Collections Framework:** Data structures like `ArrayList`, `Vector`, etc., only store objects, not primitive types.
2.  **Multithreading:** Synchronization works with objects.
3.  **Utility Methods:** Wrapper classes provide helpful utility methods (e.g., converting a String to an int using `Integer.parseInt(str)`).

**Autoboxing and Unboxing:**
*   **Autoboxing:** The automatic conversion of primitive types to their corresponding object wrapper classes (e.g., `int` to `Integer`).
*   **Unboxing:** The automatic conversion of an object of a wrapper class back to its corresponding primitive type (e.g., `Integer` to `int`).

**Example Classes:** `Integer`, `Float`, `Double`, `Character`, `Boolean`, `Byte`, `Short`, `Long`.

---

## 3. Mutex (Mutual Exclusion) & Synchronization Concepts
*(Often asked alongside Multithreading or Serialization streams)*

**Definition:**
A Mutex (Mutual Exclusion Object) is a synchronization mechanism used to control access to a shared resource in a concurrent (multithreaded) environment. It ensures that only one thread can access the critical section at a given time.

**In Java:**
Java implements Mutex inherently using the **`synchronized`** keyword and the concept of **Monitors** (or Locks). 
*   Every object in Java has an associated monitor. 
*   When a thread enters a `synchronized` method or block, it acquires the monitor lock (mutex) for that object. 
*   Other threads attempting to execute the synchronized code on the same object are blocked until the lock is released.

---

## 4. Java Serialization
**Definition:**
Serialization is a mechanism of converting the state of an object into a byte stream. 
*   **Deserialization** is the reverse process where the byte stream is used to recreate the actual Java object in memory.

**Keys to Serialization:**
1.  **`Serializable` Interface:** A class must implement the `java.io.Serializable` interface to be serialized. This is a **marker interface** (it has no methods or fields).
2.  **`ObjectOutputStream`:** Contains the `writeObject(Object)` method for serializing an object to an output stream (e.g., a file).
3.  **`ObjectInputStream`:** Contains the `readObject()` method for deserializing a stream back into an object.
4.  **`transient` Keyword:** If you don't want to save the state of a specific variable (e.g., a password), mark it as `transient`. It will not be serialized.

**Why Serialize?**
*   To save the state of an object to a file, database, or network.
*   To travel an object's state across a network (essential for Remote Method Invocation - RMI).

---

## 5. Typical Exam Questions ("Test Me")

### 5 Mark Questions:
1. What are Wrapper Classes? Why are they needed in Java? Give two examples.
2. Differentiate between `String` and `StringBuffer`. 
3. What is Autoboxing and Unboxing in Java?
4. What is a Mutex in the context of Java multithreading?

### 10/12 Mark Questions:
1. Explain the concepts of `String` and `StringBuffer` classes. Write a Java program demonstrating at least 5 methods of the `StringBuffer` class.
2. What is Java Serialization? Write a Java program to serialize and deserialize an object of a `Student` class. Explain the role of the `Serializable` marker interface.
