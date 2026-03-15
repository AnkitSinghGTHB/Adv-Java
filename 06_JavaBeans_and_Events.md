# Module 2: JavaBeans and Event Delegation Model

This section covers the core concepts of software component moduling in Java using JavaBeans, and how events are handled using the Event Delegation Model.

---

## 1. Introduction to JavaBeans
**Definition:**
A JavaBean is a specially constructed Java class written in a specific format to act as a reusable software component. They encapsulate many objects into a single object (the bean), so they can be passed around rather than handled individually.

**Advantages of JavaBeans:**
*   **Reusability:** Write once, run and reuse anywhere.
*   **Encapsulation:** Properties are hidden and accessed strictly via getter/setter methods.
*   **Customization:** Visual builders (like BDK - Bean Development Kit) can customize the appearance/behavior of beans.
*   **Introspection:** Allows tools to analyze and discover the properties, events, and methods of a bean dynamically.

---

## 2. Properties of a JavaBean
To qualify as a JavaBean, a class MUST follow these strict conventions:
1.  **Public Class:** The class must be `public`.
2.  **No-Arg Constructor:** It must have a public, parameterless (default) constructor.
3.  **Private Properties:** Its attributes must be `private`.
4.  **Getter/Setter Methods:** It must provide `public` getter and setter methods to access and modify the private properties.
5.  **Serializable:** It must implement the `java.io.Serializable` interface to save its state.

### Property Accessor Naming Conventions:
*   **Getter:** `public <Type> get<PropertyName>()` (e.g., `public String getName()`). For boolean properties, `is<PropertyName>()` is also valid (e.g., `public boolean isAdult()`).
*   **Setter:** `public void set<PropertyName>(<Type> value)` (e.g., `public void setName(String name)`).

---

## 3. JavaBean Lifecycle (in BDK)
When a JavaBean is loaded into an application builder (like the Bean Development Kit - BDK), it goes through several lifecycle stages:
1.  **Creation / Instantiation:** The bean is instantiated using its no-argument constructor.
2.  **Customization:** The developer uses a properties sheet (Introspection) to modify the initial state of the bean's properties.
3.  **Connection:** The bean is wired to other beans via events in the builder tool.
4.  **Serialization:** The customized and connected bean is serialized (saved) to a file (often a `.ser` file or inside a `.jar`).
5.  **Deployment / Execution:** At runtime, the application deserializes the bean and it executes its programmed logic.

---

## 4. The Event Delegation Model
**Definition:**
The Event Delegation Model is the modern approach to handling GUI events in Java. Instead of a single massive method handling every possible event, the component that generates the event **delegates** the responsibility of handling it to a dedicated object called a **Listener**.

**Key Components in the Model:**
1.  **Event Source:** The component that generates the event (e.g., a `Button` being clicked or a `TextField` being typed in).
2.  **Event Object:** An object that encapsulates the state change that occurred (e.g., an `ActionEvent` object).
3.  **Event Listener:** An interface that receives the event object and processes it (e.g., the `ActionListener` interface).

**How it works (The Workflow):**
1.  The User interacts with a Component (Source).
2.  The Source generates an Event Object.
3.  The Source sends the Event Object to all globally registered Listeners using a callback method (like `actionPerformed()`).
4.  The Listener code executes and handles the event appropriately.

---

## 5. Typical Exam Questions ("Test Me")

### 2/5 Mark Questions:
1. What is a JavaBean? What are its primary advantages?
2. List the mandatory conventions a Java class must follow to be considered a JavaBean.
3. What is the Event Delegation Model?
4. Briefly explain the lifecycle of a JavaBean in a building tool.

### 10/12 Mark Questions:
1. What are JavaBeans? Explain the naming conventions for Getter and Setter methods. Write a complete program that implements a proper `Employee` JavaBean class.
2. Discuss the Event Delegation Model in detail. Explain the roles of Event Source, Event Object, and Event Listener with a code snippet or diagram.
