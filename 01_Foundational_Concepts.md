# Module 1: Foundational Java Concepts

This guide covers the fundamental building blocks of Java programming, specifically tailored for 10-12 mark exam questions.

## 1. Features of Java (Buzzwords)
**Definition:** Java is a high-level, object-oriented, secure, and robust programming language.
**Key Features:**
*   **Simple:** Syntax is similar to C++, making it easy to learn. No explicit pointers, automatic garbage collection.
*   **Object-Oriented:** Everything in Java is an object. Uses Classes, Objects, Inheritance, Polymorphism, Abstraction, and Encapsulation.
*   **Platform Independent:** "Write Once, Run Anywhere" (WORA). Java compiler converts source code to bytecode (`.class` file), which can run on any OS using a JVM.
*   **Secured:** Runs inside a virtual machine sandbox. No explicit pointers to memory. Bytecode verifier checks for illegal code.
*   **Robust:** Strong memory management. Automatic garbage collection and exception handling reduce crashes.
*   **Architecture Neutral & Portable:** Size of primitive types is fixed across all architectures (e.g., `int` is always 4 bytes).
*   **Multithreaded:** Supports concurrent execution of multiple threads, maximizing CPU utilization.
*   **Distributed:** Can create distributed applications using RMI and EJB.

---

## 2. Classes and Objects
**Definition:** 
*   **Class:** A blueprint or template from which objects are created. It is a logical entity that defines state (fields/variables) and behavior (methods).
*   **Object:** A physical and logical entity that has state and behavior. It is an instance of a class.

**Key Points:**
*   Objects are created dynamically at runtime using the `new` keyword, which allocates memory in the Heap.
*   An object has three characteristics: State (data/value), Behavior (functionality/methods), and Identity (unique internal ID).

**Example Question: "Explain Classes and Objects with an example."**
*   *Write the definitions above, then provide the basic code example below (from `BasicJavaExamples.java`).*

---

## 3. Abstract Classes vs. Interfaces

### Abstract Class
**Definition:** A class declared with the `abstract` keyword. It cannot be instantiated (you cannot create an object of it). It can have both abstract methods (methods without a body) and concrete methods (methods with a body).
**Usage:** Used to provide a common base class for subclasses to extend and implement specific details. Represents an "is-a" relationship heavily.

### Interface
**Definition:** A completely abstract blueprint that specifies a set of methods without providing implementations. Declared using the `interface` keyword. Variables are implicitly `public static final`, and methods are implicitly `public abstract`.
**Usage:** Achieves total abstraction and multiple inheritance in Java. Represents a "has-a" or "can-do" relationship.

| Feature | Abstract Class | Interface |
| :--- | :--- | :--- |
| **Inheritance** | A class can extend only one abstract class. | A class can implement multiple interfaces. |
| **Methods** | Can have both concrete and abstract methods. | All methods are implicitly abstract (in older Java versions). |
| **Variables** | Can have instance variables, final, non-final, static, non-static. | Variables are only `public static final`. |
| **Keyword** | `abstract` | `interface` / `implements` |

---

## 4. Static and Inner Classes

### Static Keyword
**Definition:** Used for memory management. The `static` keyword means a member (variable or method) belongs to the class itself, rather than to instances of the class.
*   **Static Variable:** A single copy is shared among all instances of the class. It is loaded into memory when the class is loaded.
*   **Static Method:** Can be invoked without creating an instance of the class (e.g., `Math.max()`). Cannot use `this` or `super` keywords. Cannot directly access non-static members.

### Inner Classes
**Definition:** A class defined within another class.
**Advantages:**
*   **Logical Grouping:** Groups classes that are only used in one place.
*   **Access:** Inner classes have direct access to all members of the outer class, including private members.
*   **Encapsulation:** Hides the inner class from the outside world.

---

## 5. Arrays
**Definition:** An array is a collection of similar type variables that are referenced by a common name. In Java, arrays are objects dynamically allocated on the heap.
**Key Points:**
*   Index-based: Starts at index `0` and ends at `length - 1`.
*   Fixed size: Once created, the size of a standard array cannot be changed.
*   To get the length of an array, use the `length` property (e.g., `arr.length`).

---

## 6. Access Modifiers
**Definition:** Modifiers that set the accessibility (visibility) of classes, interfaces, variables, methods, and constructors.

| Modifier | Access within Class | Access within Package | Access outside Package by Subclass | Access outside Package |
| :--- | :---: | :---: | :---: | :---: |
| **Private** | Yes | No | No | No |
| **Default** (no keyword) | Yes | Yes | No | No |
| **Protected** | Yes | Yes | Yes | No |
| **Public** | Yes | Yes | Yes | Yes |

*   **Private:** Most restrictive. Used for encapsulation (hiding data).
*   **Protected:** Useful for allowing child classes to access parent properties while hiding them from the rest of the application.

---

## 7. Packages
**Definition:** A namespace that organizes a set of related classes and interfaces. Works like folders in an operating system.
**Advantages:**
*   **Categorization:** Makes classes easy to maintain.
*   **Access Protection:** Works with access modifiers to restrict visibility.
*   **Avoids Name Collisions:** Allow two classes with the same name to exist in different packages (e.g., `java.util.Date` and `java.sql.Date`).

**Creating a Package:**
Must be the very first line of code in the Java source file: `package mypackagename;`

---

## 📝 Practice Questions for Exams
1.  **Explain the features of Java (Java Buzzwords) in detail. (10 marks)**
2.  **Differentiate between Abstract Classes and Interfaces with appropriate examples. (10 marks)**
3.  **What is the `static` keyword? Explain static variables and static methods. (8 marks)**
4.  **Explain the four access modifiers in Java with a table representing their scope. (10 marks)**
5.  **What is a Package? Explain the advantages of packages and how to create a user-defined package. (10 marks)**
