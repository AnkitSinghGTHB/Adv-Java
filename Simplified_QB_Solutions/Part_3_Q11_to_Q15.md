# Advanced Java Solutions - Part 3 (Q11 to Q15)

---

## Question 11: Explain different layout managers used in Java AWT/Swing. Describe the working of any four layout managers with sample code snippets.

**Topic Introduction: Layout Managers in Java AWT/Swing.**

1. **Layout Manager Role**: Layout managers govern the positioning and sizing of GUI components within a container, ensuring adaptability across screen sizes.
2. **Dynamic UI Adaptation**: They automatically calculate bounds, completely removing the need for error-prone, hard-coded pixel coordinate positioning.
3. **FlowLayout Working**: Components are added sequentially in a row. When the row fills up, it wraps to the next line, keeping components at their preferred sizes.
4. **FlowLayout Snippet**: `JPanel panel = new JPanel(new FlowLayout()); panel.add(new JButton("OK"));`
5. **BorderLayout Working**: Divides the screen into five geographical areas (North, South, East, West, Center). The Center area expands to take up all remaining space.
6. **BorderLayout Snippet**: `frame.add(new JButton("Top"), BorderLayout.NORTH); frame.add(new JButton("Mid"), BorderLayout.CENTER);`
7. **GridLayout Working**: Creates a matrix of equal-sized cells defined by a specific number of rows and columns. Every component added stretches to fill its cell.
8. **GridLayout Snippet**: `JPanel grid = new JPanel(new GridLayout(2, 2)); grid.add(btn1); grid.add(btn2);`
9. **BoxLayout Working**: Arranges multiple components either in a single horizontal row (X_AXIS) or a single vertical column (Y_AXIS), respecting preferred sizes differently than GridLayout.
10. **BoxLayout Snippet**: `JPanel box = new JPanel(); box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));`
11. **Nesting Layouts**: The true power of layout managers is unlocked by nesting them—putting a GridLayout panel inside the North region of a BorderLayout frame.
12. **Method calls**: Use `container.setLayout(new LayoutManager())` to set it, and `container.add(component)` to populate the user interface.

```text
  Nesting Example (Border inside Flow):
  +--------------------------------+
  | [FlowLayout Container]         |
  |  +--------------------+        |
  |  |   [BorderLayout]   | [BTN]  |
  |  | NORTH        SOUTH |        |
  |  +--------------------+        |
  +--------------------------------+
```

```java
// Explanation: Layout Manager Snippets         //
import javax.swing.*;                           // GUI Toolkit
import java.awt.*;                              // AWT layouts
                                                //
public class Layouts {                          // Main class
    public static void main(String[] args) {    // Main method
        JFrame f = new JFrame();                // Create frame
        f.setLayout(new BorderLayout());        // Set border layout
                                                //
        JPanel top = new JPanel(new FlowLayout());// Create top panel
        top.add(new JLabel("Flow Top"));        // Add label to top
                                                //
        JPanel mid = new JPanel(new GridLayout(1,2));// Grid panel
        mid.add(new JButton("Grid Left"));      // Add button
        mid.add(new JButton("Grid Right"));     // Add button
                                                //
        f.add(top, BorderLayout.NORTH);         // Add to frame north
        f.add(mid, BorderLayout.CENTER);        // Add to frame center
        f.setSize(300, 200); f.setVisible(true);// Show frame
    }                                           //
}                                               //
```

**Output:**
```
GUI Window with "Flow Top" label at top,
"Grid Left" and "Grid Right" buttons in center.
```

---

## Question 12: Develop a Java GUI program using JFC that accepts personal details (name, age, address) through input fields. Implement a JavaBean to store the data and display it in a formatted way when the user clicks a submit button.

**Topic Introduction: Java GUI with JavaBean for Personal Details.**

1. **Topic Introduction**: This program integrates a Swing (JFC) Graphical User Interface with a JavaBean to capture, store, and display user input effectively.
2. **JavaBean Concept**: A JavaBean is a reusable software component representing data; it must be public, have a no-argument constructor, and use getter/setter methods.
3. **Designing the Bean**: We create a `PersonBean` class encapsulating three private properties: `name` (String), `age` (int), and `address` (String).
4. **Designing the GUI**: The GUI requires `JLabel` for descriptions, `JTextField` for user input, and a `JButton` to trigger the submission event.
5. **Layout Configuration**: A `GridLayout` (e.g., 4 rows, 2 columns) is ideal for aligning the labels side-by-side with their corresponding text fields.
6. **Action Listener Integration**: The Submit button requires an `ActionListener` to capture the click event and extract text from the input fields.
7. **Populating the Bean**: Upon clicking submit, the extracted text values are passed into the `PersonBean` via its setter methods.
8. **Data Conversion**: Since text fields return strings, `Integer.parseInt()` is used to safely convert the age input into an integer for the bean.
9. **Data Retrieval**: Immediately after populating, the data is retrieved back from the bean using its getter methods for verification.
10. **Formatting Output**: The retrieved data is formatted into a clean string and displayed to the user using a `JOptionPane.showMessageDialog`.
11. **Separation of Concerns**: This pattern distinctly separates the UI (Swing components) from the data model (the JavaBean).
12. **Robustness**: In a real-world scenario, proper validation (e.g., checking for empty fields or invalid numbers) should precede bean population.

```text
  [Swing GUI]                  [JavaBean]
  Name: [____] --(Setters)--> +---------+
  Age : [____]                | -name   |
  Addr: [____] <--(Getters)-- | -age    |
  [ SUBMIT ]                  | -address|
                              +---------+
```

```java
// Explanation: GUI with JavaBean Integration   //
import javax.swing.*; import java.awt.*;        // Imports
import java.awt.event.*;                        // Event imports
                                                //
class PersonBean {                              // JavaBean class
    private String name, address; private int age;// Private fields
    public PersonBean() {}                      // No-arg constructor
    public void setName(String n) { name=n; }   // Setter for name
    public String getName() { return name; }    // Getter for name
    public void setAge(int a) { age=a; }        // Setter for age
    public int getAge() { return age; }         // Getter for age
    public void setAddress(String a){address=a;}// Setter for address
    public String getAddress(){return address;} // Getter for address
}                                               //
                                                //
public class FormApp {                          // Main GUI Application
    public static void main(String[] args) {    // Program entry
        JFrame f = new JFrame("Input Form");    // Main window
        f.setLayout(new GridLayout(4, 2));      // 4x2 Grid
        JTextField tName = new JTextField();    // Name input field
        JTextField tAge = new JTextField();     // Age input field
        JTextField tAddr = new JTextField();    // Address input field
        JButton btn = new JButton("Submit");    // Submit button
                                                //
        f.add(new JLabel("Name:")); f.add(tName); // Add name components
        f.add(new JLabel("Age:")); f.add(tAge);   // Add age components
        f.add(new JLabel("Addr:")); f.add(tAddr); // Add address components
        f.add(new JLabel("")); f.add(btn);      // Add button
                                                //
        btn.addActionListener(e -> {            // Click event handler
            PersonBean bean = new PersonBean(); // Create bean instance
            bean.setName(tName.getText());      // Store name
            bean.setAge(Integer.parseInt(tAge.getText())); // Store age
            bean.setAddress(tAddr.getText());   // Store address
            String res = "Name: " + bean.getName() + "\nAge: " + // Format
                         bean.getAge() + "\nAddr: " + bean.getAddress();
            JOptionPane.showMessageDialog(f, res); // Show result dialog
        });                                     //
        f.setSize(300, 200); f.setVisible(true);// Show GUI
    }                                           //
}                                               //
```

**Output (on clicking Submit with Name=John, Age=20, Addr=Delhi):**
```
Dialog Box: "Name: John
Age: 20
Addr: Delhi"
```

---

## Question 13: Explain the architecture and working of the Java Event Delegation Model with a diagram.

**Topic Introduction: Java Event Delegation Model.**

1. **Topic Introduction**: The Event Delegation Model is the standard mechanism in Java for handling GUI interactions, like button clicks or mouse movements.
2. **Core Philosophy**: It separates the event source (the UI component generating the event) from the event listener (the code handling the event).
3. **Event Source**: A source is a component (like a `JButton` or `JTextField`) that changes state and subsequently generates an event object.
4. **Event Object**: An object (like `ActionEvent` or `MouseEvent`) that encapsulates all necessary information about the state change that just occurred.
5. **Event Listener**: An object implementing a specific listener interface (like `ActionListener`) that waits patiently to receive and process event objects.
6. **The Delegation Process**: The source delegates the responsibility of handling the event to one or more registered listener objects.
7. **Registration**: For delegation to work, the listener must explicitly register itself with the source using methods like `addActionListener()`.
8. **Multiple Listeners**: A single event source can multicast; it can register multiple listeners, notifying all of them when the event occurs.
9. **Event Triggering**: When the user interacts (e.g., clicks), the source creates the Event Object and broadcasts it to all registered listeners.
10. **Handler Execution**: The listener receives the object and executes its implemented handler method (like `actionPerformed()`) to execute the business logic.
11. **Efficiency**: It is highly efficient because events are only sent to components that specifically asked to listen, avoiding massive switch-statements.
12. **Decoupling**: This model keeps UI design code entirely separate from application logic code, significantly improving maintainability.

```text
  [USER ACTION] (Click Button)
       |
       v
  [EVENT SOURCE] (JButton)
       | 1. Generates Event Object
       v
  [EVENT OBJECT] (ActionEvent)
       | 2. Delegates to registered Listener
       v
  [EVENT LISTENER] (ActionListener)
       | 3. Executes Handler Method
       v
  [ACTION PERFORMED] (Update UI / DB)
```

```java
// Explanation: Event Delegation Model          //
import java.awt.event.*;                        // Event package
import javax.swing.*;                           // GUI package
                                                //
public class EventDemo {                        // Main class
    public static void main(String[] args) {    // Program entry
        JFrame frame = new JFrame("Event Demo");// Create window
        JButton btn = new JButton("Click Me");  // Create Event Source
                                                //
        // Step 1: Create the Event Listener    //
        ActionListener listener = new ActionListener() { // Anonymous class
            // Step 3: Handler Method Executes  //
            public void actionPerformed(ActionEvent e) { // Event Object 'e'
                System.out.println("Button clicked!"); // Action logic
            }                                   //
        };                                      //
                                                //
        // Step 2: Register Listener to Source  //
        btn.addActionListener(listener);        // Registration
                                                //
        frame.add(btn);                         // Add source to UI
        frame.setSize(200, 100);                // Set dimensions
        frame.setVisible(true);                 // Display window
    }                                           //
}                                               //
```

**Output (on clicking the button):**
```
Button clicked!
```

---

## Question 14: Discuss different types of JavaBean properties (simple, indexed, bound, constrained).

**Topic Introduction: Types of JavaBean Properties.**

1. **Topic Introduction**: A property is a named attribute of a JavaBean whose value can be read or written by invoking its getter and setter methods.
2. **Naming Convention**: A property named `color` is accessed via `getColor()` and `setColor()`; boolean properties often use `isColor()` instead of `get`.
3. **Simple Properties**: A simple property represents a single, independent value, such as an integer, string, or boolean, with standard getter/setter pairs.
4. **Usage of Simple**: Most basic beans use simple properties for data holding, like `Employee.getName()`.
5. **Indexed Properties**: An indexed property represents an array of values rather than a single value, allowing access to individual elements via an integer index.
6. **Indexed Syntax**: It features methods like `getItem(int index)` to read one element, and `setItem(int index, Item value)` to write one element.
7. **Bound Properties**: A bound property notifies interested listener objects (like UI components) whenever its value changes, enabling data binding.
8. **PropertyChangeSupport**: Beans with bound properties manage lists of `PropertyChangeListener` objects and fire `PropertyChangeEvent` objects upon modification.
9. **Usage of Bound**: Crucial in MVC architectures where a View must automatically update when the Model's bound property (like `balance`) changes.
10. **Constrained Properties**: A constrained property is a special bound property where the proposed value change can be rejected (vetoed) by a listener.
11. **VetoableChangeSupport**: Listeners implement `VetoableChangeListener` and can throw a `PropertyVetoException` if the new value is invalid (e.g., negative age).
12. **Complexity**: Simple and Indexed manage raw data; Bound and Constrained manage reactive behavior and strict validation within complex systems.

```text
  Property Types:
  +---------------+---------------------------------+
  | Type          | Characteristics                 |
  +---------------+---------------------------------+
  | Simple        | Single value (get/set)          |
  | Indexed       | Array of values (get/set index) |
  | Bound         | Fires event on change           |
  | Constrained   | Change can be vetoed/rejected   |
  +---------------+---------------------------------+
```

```java
// Explanation: JavaBean Properties Example     //
import java.beans.*;                            // Import beans package
                                                //
class AdvancedBean {                            // Bean class
    private String simpleProp;                  // 1. Simple Property
    private int[] indexedProp = new int[5];     // 2. Indexed Property
    private PropertyChangeSupport pcs =         // Support for Bound
        new PropertyChangeSupport(this);        // initializing helper
                                                //
    // Simple Property Accessors                //
    public String getSimpleProp() {             // Getter
        return simpleProp;                      // Return value
    }                                           //
    public void setSimpleProp(String s) {       // Setter
        simpleProp = s;                         // Update value
    }                                           //
                                                //
    // Indexed Property Accessors               //
    public int getIndexedProp(int index) {      // Indexed Getter
        return indexedProp[index];              // Return array element
    }                                           //
    public void setIndexedProp(int idx, int v) {// Indexed Setter
        indexedProp[idx] = v;                   // Update array element
    }                                           //
}                                               //
```

*(No standalone output — this is a data class. Use getters/setters to test.)*

---

## Question 15: Compare AWT and Swing (JFC) components in terms of features and performance.

**Topic Introduction: Comparison between AWT and Swing (JFC).**

1. **Topic Introduction**: Both AWT (Abstract Window Toolkit) and Swing are Java libraries used to build Graphical User Interfaces, but they have fundamentally different architectures.
2. **Platform Dependency**: AWT components are heavyweight and platform-dependent, meaning an AWT Button looks exactly like a Windows button on Windows, and a Mac button on macOS.
3. **Platform Independence**: Swing components are lightweight and platform-independent, written entirely in Java, ensuring the UI looks identical across all operating systems.
4. **Native Peer Architecture**: AWT relies heavily on native OS peer code to render components, consuming more system memory and restricting flexibility.
5. **Pure Java Architecture**: Swing bypasses native peers, drawing its own components directly onto a blank canvas, making it highly customizable.
6. **Component Prefix**: AWT classes are straightforward (e.g., `Button`, `TextField`), whereas Swing classes are universally prefixed with 'J' (e.g., `JButton`, `JTextField`).
7. **Performance Factors**: Because AWT delegates to the OS, it can sometimes be marginally faster for simple UIs, but Swing's modern optimizations have largely negated this.
8. **Pluggable Look and Feel**: Swing supports the Pluggable Look and Feel (PLAF) architecture, allowing developers to switch the entire application's aesthetic theme at runtime.
9. **Advanced Components**: Swing provides a vastly richer set of complex components out-of-the-box, such as `JTable`, `JTree`, and `JTabbedPane`, which AWT completely lacks.
10. **MVC Architecture**: Swing components are heavily influenced by the Model-View-Controller design pattern, completely separating visual representation from underlying data.
11. **Double Buffering**: Swing incorporates built-in double buffering to eliminate screen flickering during UI updates and animations, a feature missing in AWT.
12. **Current Usage**: AWT is largely obsolete for building full applications, used primarily as the underlying event and layout engine that Swing builds upon.

```text
  +-------------------------------------------------+
  | Feature          | AWT           | Swing        |
  +-------------------------------------------------+
  | Weight           | Heavyweight   | Lightweight  |
  | Platform Dep.    | Dependent     | Independent  |
  | Prefix           | None (Button) | 'J' (JButton)|
  | Look & Feel      | OS Default    | Customizable |
  | MVC Pattern      | No            | Yes          |
  +-------------------------------------------------+
```

```java
// Explanation: AWT vs Swing implementation     //
import java.awt.*;                              // Import AWT library
import javax.swing.*;                           // Import Swing library
                                                //
public class ComparisonDemo {                   // Main class
    public static void main(String[] args) {    // Program entry point
        // AWT Implementation (Heavyweight)     //
        Frame awtFrame = new Frame("AWT");      // AWT Frame
        Button awtBtn = new Button("AWT Btn");  // AWT Button
        awtFrame.add(awtBtn);                   // Add to frame
        awtFrame.setSize(200, 100);             // Set size
        awtFrame.setVisible(true);              // Display (OS renders)
                                                //
        // Swing Implementation (Lightweight)   //
        JFrame swingFrame = new JFrame("Swing");// Swing Frame
        JButton swingBtn = new JButton("Swing");// Swing Button
        swingFrame.add(swingBtn);               // Add to frame
        swingFrame.setSize(200, 100);           // Set size
        swingFrame.setLocation(250, 0);         // Move to see both
        swingFrame.setVisible(true);            // Display (Java renders)
    }                                           //
}                                               //
```

**Output:**
```
Two windows appear side-by-side:
- AWT window with native OS-styled button
- Swing window with Java-rendered button
```
