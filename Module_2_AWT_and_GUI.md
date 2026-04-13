# Module 2: Graphical User Interfaces and AWT

This module explores the fundamentals of writing visual applications using Java Foundation Classes (JFC) and the Abstract Window Toolkit (AWT).

---

## 1. GUI vs CUI Operating Systems

- **CUI (Command User Interface)**: Processes commands in lines of text. Handled by a command-line interpreter. Highly precise but navigation is hard. Examples: Unix Shell, MS-DOS.
- **GUI (Graphical User Interface)**: Interacts with the user through visual elements like images, buttons, and icons. Allows for multitasking with various windows. More flexible and customizable.

### Basic Elements in a GUI
1. **Window**: Displays information on screen. Types include container windows, browser windows, or child windows.
2. **Menu / Toolbar**: Lists choices or commands to execute an action.
3. **Icons**: Small graphical representations of files or programs.
4. **Controls / Widgets**: Input methods like buttons, checkboxes, dialog boxes.
5. **Tabs**: Allows switching between different view panes simultaneously.

---

## 2. Java Foundation Classes (JFC)

To achieve accessibility and a standardized user interface across desktops, Sun Microsystems expanded the core language with **Java Foundation Classes (JFC)**. It is a comprehensive set of GUI components.

### Distinct Features of JFC
- **Swing GUI Components**: A rich set of fully customizable GUI elements like tables and split panes capable of sorting and drag-and-drop.
- **Pluggable Look-and-Feel (PLAF)**: The appearance of the GUI is pluggable. You can dynamically switch the look between Windows, MacOS, or GTK+ structures without changing the core application logic.
- **Accessibility API**: Integrates with assistive technologies (screen readers, braille terminals).
- **Internationalization**: Seamless support for different worldwide cultural conventions and thousands of languages.
- **Drag and Drop (DnD)**: Native functionality bridging Java applications and the host OS.

### JFC vs WFC (Windows Foundation Classes)
| Feature | JFC | WFC |
| :--- | :--- | :--- |
| **Portability** | Highly portable; cross-platform interfaces. | Confined primarily to Windows (32-bit). |
| **Complexity** | Best for building complex interfaces with a lot of controls. | Builds simple interfaces; fewer controls natively. |
| **Performance** | Sometimes consumes more memory / slightly slower. | Consumes less memory / performs faster natively. |

---

## 3. Java Abstract Window Toolkit (AWT)

AWT is part of the JFC used to create window-based GUI applications. It is **platform-dependent**, meaning it uses native OS components for rendering (heavyweight components). As a result, an AWT application looks like a standard Windows app on Windows, and a Mac app on macOS.

### Container Types in AWT
The container holds multiple GUI components inside it. 
1. **Window**: A top-level container without borders or a menu bar.
2. **Panel**: A lightweight container providing space for attaching components. Cannot be attached to a Menu.
3. **Frame**: A top-level container with a title bar, border, and the capacity for menu bars. Highly utilized in standard applications.
4. **Dialog**: A temporary popup window requiring user input.

---

## 4. AWT Core Components

Components define individual widgets within the frames/panels.

### Label (`java.awt.Label`)
A passive control used for placing text or images that the user cannot edit directly. 

```java
import java.awt.*;
import java.awt.event.*;

public class BasicLabelDemo {
    public static void main(String[] args) {
        // Frame to hold elements
        Frame frame = new Frame("My Label Demo");
        
        // Creating the label object
        Label label = new Label("Hello, World!", Label.CENTER);
        
        frame.add(label);
        frame.setSize(300, 200);
        frame.setVisible(true);

        // Required to smoothly close the Frame running
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
    }
}
```
**Expected Terminal Output:** 
```text
(Opens a new OS window with title "My Label Demo", maintaining "Hello, World!" in the direct center.)
```

### TextField (`java.awt.TextField`)
An active control used to collect a single line of text from the user. You can retrieve its value or hide it strictly using `.setEchoChar('*')` to create a password field.

### Button (`java.awt.Button`)
A control component capable of triggering actions via Action Listeners when clicked. When pressed, an instance of `ActionEvent` triggers a registered `ActionListener`.

```java
import java.awt.*;
import java.awt.event.*;

public class ButtonExample {
    public static void main(String[] args) {
        Frame frame = new Frame("AWT Button Demo");

        // TextField output reference
        final TextField tf = new TextField();
        tf.setBounds(50, 50, 150, 20); // x, y, width, height

        // Button Component
        Button btn = new Button("Click Me!");
        btn.setBounds(50, 100, 100, 30);
        btn.setBackground(Color.BLUE);
        btn.setForeground(Color.WHITE);

        // Attaching the action listener
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Changing the state of the TextField on click
                tf.setText("Button was clicked!");
            }
        });

        // Add to Frame without standard Layout Management initially
        frame.add(btn);
        frame.add(tf);
        frame.setSize(300, 250);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
    }
}
```
**Expected Terminal Output:**
```text
(A window will open with a text field and button. Upon clicking "Click Me!", the blank text field updates immediately down to "Button was clicked!")
```

### Checkbox (`java.awt.Checkbox`)
A control allowing users to execute binary choices (on/off). Forms radio buttons when grouped using `CheckboxGroup`. 

> [!NOTE]
> All actions inside basic AWT controls implement their responsiveness using `ActionEvents`. Swing components expand these features largely and rely heavily on MVC patterns which abstract out these constraints.
