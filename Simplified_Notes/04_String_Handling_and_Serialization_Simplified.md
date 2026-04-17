# Module 4: String Handling & Serialization (Simplified)

## 1. String vs StringBuffer

**Simple Explanation (Universal Analogy):**
A **`String`** is like text carved forcefully into stone. Once carved, you *cannot* change it without fetching a brand new stone (Immutable).
A **`StringBuffer`** is like text written on a classroom blackboard. You can easily erase, append, and change it over and over again on the exact same board (Mutable).

**Key Differences for Exam (Tabular Format):**
| Feature | `String` (Carved Stone) | `StringBuffer` (Blackboard) |
| :--- | :--- | :--- |
| **Mutability** | **Immutable:** Cannot be modified. | **Mutable:** Can be modified. |
| **Memory usage** | Slower for frequent appending (creates useless objects). | Fast for frequent modifications. |
| **Thread Safety** | Inherently thread-safe (cannot be changed). | Thread-safe internally (methods are `synchronized`). |

**Exam Code Snippet:**
```java
// Testing mutability
String s1 = new String("Hello");
s1.concat(" World"); // 'Hello World' is created, but s1 still points to just 'Hello'
System.out.println(s1); // Prints: Hello (because it's immutable!)

StringBuffer sb = new StringBuffer("Hello");
sb.append(" World"); // Modified on the same blackboard
System.out.println(sb); // Prints: Hello World
```

---

## 2. Wrapper Classes (Autoboxing / Unboxing)

**Simple Explanation:**
Java loves "Objects", but it has basic Primitive types like `int`, `float`, and `char` that are just plain numbers, not objects. A **Wrapper Class** is like putting a plain coin (`int`) inside a nice small box so it behaves like a respectable Object.

**Key Definitions:**
* **Autoboxing:** Java automatically putting the primitive number into the box. (`int` -> `Integer`)
* **Unboxing:** Java automatically taking the primitive number out of the box. (`Integer` -> `int`)
* **Why do we need them?** Data structures (like `ArrayList`) and Serialization only work with *Objects*, not basic primitives!

```java
int a = 10;                     // Primitive
Integer box = Integer.valueOf(a); // Manual Boxing
Integer autoBox = 20;           // Autoboxing (automatic)
int b = autoBox;                // Unboxing (automatic)
```

---

## 3. Java Serialization

**Simple Explanation:**
Imagine you are filling out a long paper form but you have to leave. You "Save" your progress by keeping the paper safely in a folder. When you return, you "Load" the paper and perfectly resume your work.
Taking a live object from computer memory and saving it as actual data in a file is **Serialization**. 
Loading that file back into the computer's memory as a live object is **Deserialization**.

**4 Magic Rules of Serialization (Exam Must-Haves):**
1. **`Serializable` Interface:** Your class MUST implement `java.io.Serializable`. It is a "Marker Interface" (has no methods inside it, just marks the class as strictly allowed to be saved).
2. **`ObjectOutputStream`:** The class used to properly save the object (uses `.writeObject()`).
3. **`ObjectInputStream`:** The class used to read the object safely (uses `.readObject()`).
4. **`transient` Keyword:** If you have a secret variable like `password`, put `transient` before it. Java will skip saving this exact variable into the file to protect it!

**Exam Code Snippet (10 Mark Guarantee):**
```java
import java.io.*;

// 1. Must implement Serializable
class Student implements Serializable {
    int id = 101;
    String name = "Ankit";
    transient String password = "123"; // Will NOT be saved!
}

public class SerializationDemo {
    public static void main(String args[]) {
        try {
            // SERIALIZATION (Saving Object)
            Student s1 = new Student();
            FileOutputStream fout = new FileOutputStream("student.txt");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            
            out.writeObject(s1); // Save it!
            out.close();
            System.out.println("Object state saved to file.");
            
            // DESERIALIZATION (Loading Object)
            FileInputStream fin = new FileInputStream("student.txt");
            ObjectInputStream in = new ObjectInputStream(fin);
            
            // Read it and cast it back to Student
            Student loadedStudent = (Student) in.readObject();
            in.close();
            System.out.println("Loaded student name: " + loadedStudent.name);
            System.out.println("Loaded password: " + loadedStudent.password); // Prints 'null' because of transient!
            
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
```

### How to Answer Exam Questions on this Topic:
* **String vs StringBuffer:** Always draw the 3-point table, and show the `.concat()` vs `.append()` example.
* **Serialization (12M):** Write the definition, explicitly mention the `transient` keyword to impress the examiner, and write the simple code snippet above.
