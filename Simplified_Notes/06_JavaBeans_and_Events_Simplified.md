# Module 6: JavaBeans and Event Delegation (Simplified)

## 1. What is a JavaBean?

**Simple Explanation (Universal Analogy):**
A **JavaBean** is simply a standard Java class, but written strictly following standard rules. Let's think of standard water pipes. Plumbers all agree on universal thread sizes so that any pipe can perfectly fit into any other pipe without trouble. 
A JavaBean is built with strict rules so that other programs (like visual builder tools) can easily plug it in, read it, and use its parts automatically.

**The 5 Golden Rules of a JavaBean (Memorize this for exams!):**
1. The class must be **`public`**.
2. It must have a **public, no-argument (empty) constructor**.
3. All variables (properties) must explicitly be **`private`**.
4. You must use **public Getter and Setter methods** to carefully access the variables.
5. It must implement **`java.io.Serializable`** (so it can be securely saved/loaded to a file).

**Exam Code Snippet:**
```java
import java.io.Serializable;

// 1. Public Class + 5. Implements Serializable
public class EmployeeBean implements Serializable { 
    
    // 3. Private properties
    private String name; 
    
    // 2. Public No-Argument Constructor
    public EmployeeBean() {} 
    
    // 4. Public Getter
    public String getName() {
        return name;
    }
    
    // 4. Public Setter
    public void setName(String name) {
        this.name = name;
    }
}
```

---

## 2. Event Delegation Model

**Simple Explanation:**
In the very old days of Java, the system acted like a single worker trying to process absolutely everything jumping around the shop (every click, drag, type).
The modern **Event Delegation Model** means the system "delegates" (assigns) responsibilities properly. A visual component (like a Button) doesn't process clicks directly. If you click it, it generates a message package and hands it strictly off to a designated watcher called a "Listener". 

**Key Definitions for Exam:**
1.  **Event Source:** The thing you physically interact with (e.g., A Button, a Checkbox).
2.  **Event Object:** The message package created when you interact. It contains details (What time was it clicked? Which button exactly?).
3.  **Event Listener:** The dedicated interface that waits patiently for the message package, and then automatically runs your custom task.

**Concept Diagram:**
```text
  (User Clicks)
        |
        V
  [Event Source] (e.g., JButton)
        |
        |---> creates
        V
  [Event Object] (e.g., ActionEvent)
        |
        |---> sends package to designated watcher
        V
  [Event Listener] (e.g., ActionListener)
        |
        |---> runs
        V
  (Your Custom Code Executes)
```

### How to Answer Exam Questions on this Topic:
*   **JavaBeans (10M):** List all 5 Golden Rules exactly. Write the `EmployeeBean` code. State the advantages: reusability, introspection (tools can read it automatically), and encapsulation.
*   **Event Delegation (8M):** Draw the ASCII flowchart diagram. Explain Source, Object, and Listener clearly in 3 solid bullet points.
