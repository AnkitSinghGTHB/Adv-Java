# Advanced Java Solutions - Part 2 (Q6 to Q10)

---

## Question 6: Define an interface in Java. Create an interface Employee with methods calculateSalary() and displayDetails(). Implement it in classes FullTimeEmployee and PartTimeEmployee. Write a program to demonstrate the functionality and explain the execution.

**Topic Introduction: Interface in Java and Implementation.**

1. **Introduction to Interface**: An interface in Java is a reference type, similar to a class, that can contain only constants, method signatures, default methods, static methods, and nested types.
2. **Abstract Blueprint**: It serves as an abstract blueprint that specifies *what* a class must do, but not *how* it should do it, leaving the implementation details to the classes that implement it.
3. **The `interface` Keyword**: Interfaces are declared using the `interface` keyword, and all methods inside are implicitly `public` and `abstract` (prior to Java 8).
4. **The `implements` Keyword**: A class commits to providing concrete implementations for the interface's methods by using the `implements` keyword in its declaration.
5. **Multiple Inheritance**: While Java classes cannot extend more than one class, a single class can implement multiple interfaces, effectively achieving multiple inheritance.
6. **Contractual Obligation**: If a class implements an interface but fails to provide a concrete implementation for all its methods, the class itself must be declared `abstract`.
7. **Employee Interface Scenario**: An interface `Employee` acts as the contract, defining essential behaviors like `calculateSalary()` and `displayDetails()`.
8. **FullTimeEmployee Implementation**: The `FullTimeEmployee` class implements `Employee` and defines salary calculation based on a fixed monthly rate.
9. **PartTimeEmployee Implementation**: The `PartTimeEmployee` class implements the same interface but calculates salary dynamically based on hours worked and an hourly rate.
10. **Polymorphism in Action**: An interface reference variable can point to any object of a class that implements it, enabling runtime polymorphic behavior.
11. **Program Execution Flow**: In the `main` method, we create instances of both employee types and call the overridden interface methods.
12. **Method Resolution**: At runtime, Java's dynamic method dispatch ensures that the specific `calculateSalary()` version belonging to the actual object type is executed.

```text
      <<interface>>
       Employee
    +calculateSalary()
    +displayDetails()
          ^
          | (implements)
  +-------+-------+
  |               |
FullTime      PartTime
Employee      Employee
```

```java
// Explanation: Interface Implementation        //
interface Employee {                            // Interface declaration
    void calculateSalary();                     // Abstract method 1
    void displayDetails();                      // Abstract method 2
}                                               //
                                                //
class FullTimeEmployee implements Employee {    // FullTime class
    public void calculateSalary() {             // Providing implementation
        System.out.println("Salary: $5000/mo"); // Fixed salary logic
    }                                           //
    public void displayDetails() {              // Providing implementation
        System.out.println("Type: Full-Time");  // Display details logic
    }                                           //
}                                               //
                                                //
class PartTimeEmployee implements Employee {    // PartTime class
    public void calculateSalary() {             // Providing implementation
        System.out.println("Salary: $20/hr");   // Hourly salary logic
    }                                           //
    public void displayDetails() {              // Providing implementation
        System.out.println("Type: Part-Time");  // Display details logic
    }                                           //
}                                               //
                                                //
public class EmployeeDemo {                     // Main execution class
    public static void main(String[] args) {    // Program entry point
        Employee e1 = new FullTimeEmployee();   // Polymorphic reference
        Employee e2 = new PartTimeEmployee();   // Polymorphic reference
        e1.displayDetails(); e1.calculateSalary(); // Calls FullTime methods
        e2.displayDetails(); e2.calculateSalary(); // Calls PartTime methods
    }                                           //
}                                               //
```

**Output:**
```
Type: Full-Time
Salary: $5000/mo
Type: Part-Time
Salary: $20/hr
```

---

## Question 7: Explain the concept of interfaces in Java. Design an interface Animal with methods sound() and eat(). Implement this interface in classes Dog and Cat. Write a complete program and describe how it works.

**Topic Introduction: Concept of Interfaces and Animal Implementation.**

1. **Concept of Interfaces**: Interfaces provide a powerful mechanism to achieve 100% abstraction in Java, separating method definition from method implementation.
2. **Total Decoupling**: By programming to an interface rather than a concrete class, developers decouple different parts of the system, making code more flexible and modular.
3. **Loose Coupling**: Interfaces enable loose coupling; you can swap out one implementation (e.g., swapping a MySQL database driver for a PostgreSQL one) without altering the dependent code.
4. **Constants**: Variables declared inside an interface are implicitly `public`, `static`, and `final`, acting as universal constants across all implementing classes.
5. **No Instantiation**: Like abstract classes, an interface cannot be instantiated directly using the `new` keyword because it does not contain a complete implementation.
6. **Animal Interface Design**: We design an `Animal` interface that enforces two fundamental behaviors: `sound()` and `eat()`.
7. **Dog Implementation**: The `Dog` class implements `Animal` and provides specific logic: returning "Woof" for sound and "Bones" for eating behavior.
8. **Cat Implementation**: The `Cat` class implements `Animal` with its own unique logic: returning "Meow" for sound and "Fish" for eating behavior.
9. **How it Works**: The `main` method demonstrates the flexibility of interfaces by using an `Animal` reference to hold different object types sequentially.
10. **Dynamic Binding**: When `animal.sound()` is called, the JVM determines exactly which implementation to execute based on whether the reference points to a `Dog` or a `Cat`.
11. **Code Reusability**: This pattern allows new animals (e.g., `Bird`) to be added later without changing the core application logic that expects an `Animal`.
12. **Real-world Analogy**: A universal remote control (Interface) defines buttons (methods) like Power or Volume, but the actual device (TV or DVD Player) determines how it responds.

```text
      <<interface>>
         Animal
      +sound()
      +eat()
          ^
          | (implements)
    +-----+-----+
    |           |
   Dog         Cat
 +sound()    +sound()
 +eat()      +eat()
```

```java
// Explanation: Animal Interface Concept        //
interface Animal {                              // Interface definition
    void sound();                               // Abstract method for sound
    void eat();                                 // Abstract method for eating
}                                               //
                                                //
class Dog implements Animal {                   // Dog implementation
    public void sound() {                       // Overriding sound method
        System.out.println("Dog says Woof");    // Dog specific behavior
    }                                           //
    public void eat() {                         // Overriding eat method
        System.out.println("Dog eats bones");   // Dog specific behavior
    }                                           //
}                                               //
                                                //
class Cat implements Animal {                   // Cat implementation
    public void sound() {                       // Overriding sound method
        System.out.println("Cat says Meow");    // Cat specific behavior
    }                                           //
    public void eat() {                         // Overriding eat method
        System.out.println("Cat eats fish");    // Cat specific behavior
    }                                           //
}                                               //
                                                //
public class AnimalDemo {                       // Main execution class
    public static void main(String[] args) {    // Program entry point
        Animal a1 = new Dog();                  // Polymorphic reference
        Animal a2 = new Cat();                  // Polymorphic reference
        a1.sound(); a1.eat();                   // Dog methods execute
        a2.sound(); a2.eat();                   // Cat methods execute
    }                                           //
}                                               //
```

**Output:**
```
Dog says Woof
Dog eats bones
Cat says Meow
Cat eats fish
```

---

## Question 8: Write a Java program illustrating the scope and visibility of methods with different access modifiers.

**Brief Explanation:**

- Java has 4 access modifiers: `public`, `protected`, `default` (no keyword), and `private`.
- Each modifier controls which classes can call a method or access a variable.
- `private` = same class only; `default` = same package; `protected` = same package + subclasses; `public` = everywhere.

```text
  Visibility Summary:
  +-----------+-------+---------+----------+-------+
  | Modifier  | Class | Package | Subclass | World |
  +-----------+-------+---------+----------+-------+
  | public    |  YES  |   YES   |   YES    |  YES  |
  | protected |  YES  |   YES   |   YES    |  NO   |
  | default   |  YES  |   YES   |   NO     |  NO   |
  | private   |  YES  |   NO    |   NO     |  NO   |
  +-----------+-------+---------+----------+-------+
```

```java
// Explanation: Method Visibility Scope         //
class AccessDemo {                              // Class containing methods
    public void pubMethod() {                   // Public method declaration
        System.out.println("Public visible");   // Accessible anywhere
    }                                           //
    protected void proMethod() {                // Protected method declaration
        System.out.println("Protected visible");// Package & Subclass access
    }                                           //
    void defMethod() {                          // Default method declaration
        System.out.println("Default visible");  // Package level access only
    }                                           //
    private void priMethod() {                  // Private method declaration
        System.out.println("Private hidden");   // Class level access only
    }                                           //
    public void testPrivate() {                 // Public helper method
        priMethod();                            // Private method called inside
    }                                           //
}                                               //
                                                //
public class AccessTest {                       // Main execution class
    public static void main(String[] args) {    // Program entry point
        AccessDemo obj = new AccessDemo();      // Create object
        obj.pubMethod();                        // OK: public access
        obj.proMethod();                        // OK: same package
        obj.defMethod();                        // OK: same package
        // obj.priMethod();                     // ERROR: private access
        obj.testPrivate();                      // OK: calls private internally
    }                                           //
}                                               //
```

**Output:**
```
Public visible
Protected visible
Default visible
Private hidden
```

---

## Question 9: Write a Java program to test accessibility of class members using different access modifiers. Include both variables and methods, and verify access rules across packages. Represent the results in a comparative table.

**Brief Explanation:**

- This program tests how access modifiers behave across package boundaries.
- `public` and `protected` (via inheritance) are accessible across packages.
- `default` and `private` members cannot be accessed from a different package.

```text
  [PACKAGE 1]                  [PACKAGE 2]
  +------------------+         +------------------+
  | ClassA           |         | ClassB extends A |
  | + publicVar      |<========| (Access OK)      |
  | # protectedVar   |<========| (Access OK)      |
  | ~ defaultVar     |<-- X ---| (Access Denied)  |
  | - privateVar     |<-- X ---| (Access Denied)  |
  +------------------+         +------------------+
```

```java
// ===== FILE 1: pack1/A.java =====             //
package pack1;                                  // Package 1 declaration
public class A {                                // Public class A
    public int pub = 1;                         // Public variable
    protected int pro = 2;                      // Protected variable
    int def = 3;                                // Default variable
    private int pri = 4;                        // Private variable
                                                //
    public void pubMethod() {                   // Public method
        System.out.println("Public method");    //
    }                                           //
    private void priMethod() {                  // Private method
        System.out.println("Private method");   //
    }                                           //
}                                               //
                                                //
// ===== FILE 2: pack2/B.java =====             //
package pack2;                                  // Package 2 declaration
import pack1.A;                                 // Import class A
public class B extends A {                      // Inherits A (cross-package)
    public void testAccess() {                  // Method to test rules
        System.out.println("pub = " + pub);     // OK: public
        System.out.println("pro = " + pro);     // OK: protected via child
        // System.out.println(def);             // ERROR: default denied
        // System.out.println(pri);             // ERROR: private denied
        pubMethod();                            // OK: public method
        // priMethod();                         // ERROR: private method
    }                                           //
                                                //
    public static void main(String[] args) {    // Program entry point
        B obj = new B();                        // Create object of B
        obj.testAccess();                       // Run access test
    }                                           //
}                                               //
```

**Output:**
```
pub = 1
pro = 2
Public method
```

---

## Question 10: Define layout manager in Java. Explain any four types of layout managers and write example programs to demonstrate their usage.

**Topic Introduction: Layout Managers in Java.**

1. **Introduction to Layout Managers**: A Layout Manager in Java is an interface that automatically positions and sizes components (like buttons and text fields) within a container (like a window or panel).
2. **Why they are Needed**: Without a layout manager, absolute positioning is required, which fails miserably when a window is resized or run on screens with different resolutions.
3. **Platform Independence**: Layout managers ensure that the Graphical User Interface (GUI) looks consistent and responsive across multiple operating systems.
4. **FlowLayout**: `FlowLayout` is the default manager for Applets and Panels. It arranges components in a simple line, one after another from left to right, wrapping to the next line if space runs out.
5. **BorderLayout**: `BorderLayout` is the default manager for Frames. It divides the container into five distinct regions: North, South, East, West, and Center.
6. **GridLayout**: `GridLayout` organizes components into a rigid rectangular grid of equally sized rows and columns, like a checkerboard.
7. **BoxLayout**: `BoxLayout` aligns components in either a single row (horizontally) or a single column (vertically), allowing different component sizes unlike GridLayout.
8. **CardLayout**: `CardLayout` stacks components on top of each other like a deck of cards, where only one card (component) is visible at any given time.
9. **GridBagLayout**: `GridBagLayout` is the most powerful and complex layout, placing components in a grid but allowing them to span multiple rows/columns and have different sizes.
10. **Setting a Layout**: A layout is applied to a container using the `setLayout()` method, passing an instance of the desired layout manager class.
11. **Adding Components**: Once the layout is set, components are added using the `add()` method, often passing additional constraints (like `BorderLayout.NORTH`) depending on the layout type.
12. **Responsive Design**: By combining and nesting different layout managers within multiple panels, developers can build complex, highly responsive, and robust user interfaces.

```text
  [BorderLayout]         [GridLayout 2x2]        [FlowLayout]
  +---+------+---+       +-------+-------+       +----------+
  | W |  N   | E |       | Btn 1 | Btn 2 |       | [1] [2]  |
  +---+------+---+       +-------+-------+       | [3]      |
  |   |  C   |   |       | Btn 3 | Btn 4 |       +----------+
  +---+------+---+       +-------+-------+
      |  S   |
      +------+
```

```java
// Explanation: Layout Manager Examples         //
import java.awt.*;                              // Import AWT package
import javax.swing.*;                           // Import Swing package
                                                //
public class LayoutDemo {                       // Main class declaration
    public static void main(String[] args) {    // Program entry point
        JFrame f = new JFrame("Layout Demo");   // Create main window
        f.setLayout(new BorderLayout());        // Set BorderLayout
                                                //
        f.add(new JButton("North"),             // Adding button to North
              BorderLayout.NORTH);              // Constraint specification
                                                //
        JPanel p = new JPanel();                // Create a sub-panel
        p.setLayout(new GridLayout(1, 2));      // Set GridLayout 1x2
        p.add(new JButton("Grid 1"));           // Add button to grid
        p.add(new JButton("Grid 2"));           // Add second button
                                                //
        f.add(p, BorderLayout.CENTER);          // Add panel to Center region
                                                //
        f.setSize(300, 200);                    // Set window size
        f.setVisible(true);                     // Make window visible
    }                                           //
}                                               //
```

**Output:**
```
GUI Window appears with "North" button at top,
"Grid 1" and "Grid 2" buttons side-by-side in center.
```
