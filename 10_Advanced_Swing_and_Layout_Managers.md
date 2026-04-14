# Module 4: Advanced Swing and Layout Managers

While AWT acts as the foundation, Java Swing provides a much more flexible and powerful toolkit to build graphical applications.

---

## 1. Overview of Java Swing
**Definition:**
Swing is an extension GUI toolkit for Java that is entirely written in Java itself. 
Because it does not rely on the underlying Operating System's native code for rendering buttons or windows, its components are considered **lightweight** and **platform-independent**.

**Advantages over AWT:**
1.  **Lightweight:** Swing is pure Java. AWT relies heavily on native OS peers.
2.  **Pluggable Look and Feel (PLAF):** You can change how a Swing GUI looks entirely at runtime. You can make an app running on Windows look exactly like a MacOS app.
3.  **Advanced Components:** Provides much more advanced components like Tables, Trees, TabbedPanes, and internal frames.

---

## 2. MVC in Swing (The Delegate Model)
While traditional MVC splits code strictly into three pieces (Model, View, Controller), doing so for a basic button was deemed too complex. 

Instead, Java Swing implements a modified MVC known as the **Delegate Model**.
*   In Swing, the **View** and **Controller** are tightly merged into a single object called a **Delegate** or `UI` object (like `ButtonUI`).
*   The delegate handles drawing the button on the screen AND interpreting the mouse clicks that happen over it.
*   The **Model** (`ButtonModel`) remains completely separate. It stores boolean states like whether the button is currently enabled, pressed, or armed.

### Example: MVC Analysis of a Swing Button
1.  User clicks the visual Button (**View/Delegate** recognizes the action).
2.  The **Controller** aspect of the **Delegate** interprets the click.
3.  It reaches out and updates the `ButtonModel`, setting its state to *pressed*.
4.  The `ButtonModel` fires an update back to the **Delegate**.
5.  The **Delegate** briefly redraws the button graphic so it visually looks pushed down.

---

## 3. Advanced Layout Management
*(Very common 10/12 mark programming question)*

By default, without a Layout Manager, you have to strictly define the X and Y bounds of every single component. Layout Managers algorithmically organize your UI components so they adapt organically when a window is resized. 

### 1. BorderLayout (Default for JFrame)
Arranges components into five specific directional regions: `NORTH`, `SOUTH`, `EAST`, `WEST`, and `CENTER`.
*   You cannot place two components in the same region directly.
*   The `CENTER` region acts greedily and expands to fill all leftover space.
```java
// Example
JFrame frame = new JFrame();
frame.setLayout(new BorderLayout());
frame.add(new JButton("Top"), BorderLayout.NORTH);
frame.add(new JTextArea("Content"), BorderLayout.CENTER);
```

### 2. FlowLayout (Default for JPanel / Applet)
Arranges components linearly from left to right, exactly like words in a paragraph. 
*   If a component exceeds the window's horizontal width, it softly wraps around to the next line down.
*   Great for stacking multiple buttons neatly in a row.

### 3. GridLayout
Places components in a strictly rigid table of rows and columns.
*   Specify rows and columns structurally: `GridLayout(rows, columns)`.
*   **Crucial Rule:** Every single cell in a GridLayout will be forced to be the exactly same uniform size (width and height). It's incredibly useful for building calculators or calendar apps.

### 4. CardLayout
Manages overlapping components. 
*   Unlike others, `CardLayout` only displays one component (or "card") visibly at a single time.
*   Useful when building installers/wizards where the user clicks "Next" or "Back" to navigate through different overlapping panels.

### 5. GridBagLayout
The most complex and universally flexible layout manager in Java.
*   While it superficially aligns components in a grid, it allows components to safely span multiple rows or columns.
*   It supports distinct variable heights/widths and different positional weights for its child components.

---

## 4. Typical Exam Questions ("Test Me")

### 5 Mark Questions:
1. Explain the differences between Swing and AWT. Provide three key differences.
2. What is Pluggable Look and Feel (PLAF) in Swing? How does it tie into MVC?
3. Briefly explain the mechanism of CardLayout with an example use case.

### 10 Mark Questions:
1. Explain the implementation of the MVC architecture within Java Swing components. Why is the View and Controller merged, and what is a Delegate? Support your answer with a detailed analysis of how a Swing Button works structurally.
2. Outline the mechanics of Layout Managers. Describe `BorderLayout`, `FlowLayout`, and `GridLayout` in depth, clearly stating their differences and their default container assignments.
