# Module 2: Graphical User Interface (GUI), AWT, and Swing

## 1. Introduction to GUI
**Definition:**
GUI (Graphical User Interface) is a visual representation of communication presented to the user for easy interaction with the machine. Instead of command-line text, users interact with visual elements like icons, buttons, menus, and windows.

**Key Elements of GUI:**
* **Window:** A container that displays information on the screen.
* **Menu:** A list of choices offered to the user.
* **Icons:** Small graphical representations of programs or files.
* **Controls/Widgets:** Elements like checkboxes, radio buttons, and text fields used to manipulate data.
* **Tabs:** Allow switching between different views within the same window.

**Advantages of GUI:**
* Highly intuitive and easy to use for average users.
* Visual cues (drag-and-drop, trash bin) make operations self-descriptive.
* Immediate feedback for user actions.

---

## 2. Default GUI OS vs CUI
*(Often asked as a 5-mark comparison)*

| Feature | GUI (Graphical User Interface) | CUI (Command User Interface) |
| :--- | :--- | :--- |
| **Interaction** | Graphics (images, icons, buttons) | Text-based commands |
| **Navigation** | Easy (using mouse/touch) | Difficult (requires typing commands) |
| **Ease of Use** | Very easy, highly intuitive | Difficult, requires expertise |
| **Speed** | Relatively slower | High speed |
| **Memory** | Requires high memory | Requires low memory |
| **Peripherals** | Keyboard + Mouse / Pointing device | Keyboard only |

---

## 3. Java Foundation Classes (JFC)
**Definition:**
JFC is a rich and comprehensive set of GUI components and services that simplify the development of desktop applications. It is a superset that contains AWT and extends it by adding many features.

**Key Components of JFC:**
1. **Swing GUI Components:** From basic buttons to complex tables and split panes.
2. **Pluggable Look-and-Feel (PLAF):** Allows swapping the appearance of the UI (e.g., Windows look, Default Java look).
3. **Accessibility API:** Supports assistive technologies like screen readers.
4. **Java 2D API:** For high-quality 2D graphics, text, and images.
5. **Internationalization:** Allows building apps in different languages and cultural conventions.

---

## 4. Java AWT (Abstract Window Toolkit)
**Definition:**
AWT is an API to develop GUI or window-based applications in Java. It provides **platform-dependent** and **heavyweight** components because it relies on the native OS components for rendering.

**Types of AWT Containers:**
* **Window:** A top-level container without borders or menu bars.
* **Frame:** A top-level container with a title bar, border, and support for menu bars (most commonly used).
* **Panel:** A lightweight container used for grouping other components inside a Window/Frame.
* **Dialog:** A temporary window used to retrieve user input.

### Key AWT Components:
1.  **`Label`:** A simple component to display a single line of read-only text.
2.  **`Button`:** A clickable component used to trigger an action event.
3.  **`TextField`:** A single-line text entry area where the user can type text.
4.  **`Checkbox`:** A component that can be either in an "on" (true) or "off" (false) state. Used for multi-selection options (e.g., selecting hobbies).
5.  **`CheckboxGroup`:** Used to group multiple `Checkbox` objects together to act as **Radio Buttons**. In a `CheckboxGroup`, only one checkbox can be selected at a time (e.g., selecting male or female).

---

## 5. Layout Managers in AWT
*(Frequently asked in 5-mark and 10-mark questions)*

**Definition:**
Layout Managers are used to arrange components in a specific manner within a container. If you don't use a layout manager, you have to manually specify the absolute `x, y` coordinates and sizes of every component, which is difficult across different screen sizes.

**Common Layout Managers:**
1.  **`FlowLayout` (Default for Panel/Applet):**
    *   Arranges components in a line, one after another (left to right).
    *   If the space on a line is filled, it moves the next component to the next line.
2.  **`BorderLayout` (Default for Frame/Window):**
    *   Arranges components into 5 different regions: `North`, `South`, `East`, `West`, and `Center`.
    *   Each region can contain only ONE component.
3.  **`GridLayout`:**
    *   Arranges components in a rectangular grid of equally-sized cells (rows and columns).
    *   Useful for creating forms or calculator-like interfaces.
4.  **`CardLayout`:**
    *   Manages two or more components (usually `Panel`s) that share the same display space.
    *   Only one component is visible at a time, acting like a stack of playing cards where you flip between them.

---

---

## 6. Event Handling in GUI
*(Crucial for Programming Questions)*

When a user interacts with a GUI component (e.g., clicking a button), an Event is generated. To handle this event, Java uses Listeners.

**Steps to handle an event (e.g., Button Click):**
1. Implement the `ActionListener` interface.
2. Override the `actionPerformed(ActionEvent e)` method with the logic to execute.
3. Register the listener with the component using `button.addActionListener(this)`.

---

## 7. Typical Exam Questions ("Test Me")

### 2 Mark Questions:
1. What is the difference between AWT and Swing?
2. What are the 4 types of containers in Java AWT?
3. What is a Layout Manager? Name any four layout managers in Java.

### 5 Mark Questions:
1. Explain the difference between GUI and CUI Operating Systems.
2. What is JFC? List and briefly explain its key components.
3. Explain the architecture of Java AWT and explain the use of Checkbox and CheckboxGroup.
4. Explain any three Layout Managers used in Java with their default containers.

### 10/12 Mark Questions:
1. What is Java AWT? Write a complete Java program using AWT to create a Frame with a Label, TextField, and a Button. Using Event Handling, update the label when the button is clicked.
2. What are Layout Managers? Discuss `FlowLayout`, `BorderLayout`, and `GridLayout` in detail with their typical use cases.

---

## 8. Important Exam Tips
* **AWT vs Swing:** AWT is platform-dependent and heavyweight. Swing is platform-independent (built entirely in Java) and lightweight.
* Always remember to use `frame.setVisible(true)` at the end of your GUI code; otherwise, the window will not appear.
* Mentioning `WindowAdapter` or `WindowListener` to close the frame using `System.exit(0)` is a great way to show expertise in programming answers.
