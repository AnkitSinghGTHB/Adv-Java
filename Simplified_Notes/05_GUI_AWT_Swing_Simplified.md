# Module 5: GUI, AWT & Swing (Simplified)

## 1. GUI vs CUI

**Simple Explanation:**
*   **CUI (Command User Interface):** Like an old-fashioned typewriter. You have to type exact text commands without seeing any visual buttons (e.g., typing `open folder`). It is fast but very hard for normal people to memorize all the commands.
*   **GUI (Graphical User Interface):** What you use every day today. You use a mouse, click on visual buttons, and drag simple pictures (icons). It is extremely easy to use because it is fully visual.

---

## 2. AWT vs Swing (Crucial Comparison)

**Simple Explanation:**
Java allows you to build desktop visual applications.
*   **AWT (Abstract Window Toolkit):** The older way. If you build a button in AWT, Java asks the Operating System (like Windows or Mac) to draw *its own* version of a button. So it looks completely different on every computer. It is **Platform-Dependent** and **Heavyweight**.
*   **Swing (JFC):** The modern way. Java draws the buttons purely by itself using Java code, without asking the operating system for help. It is **Platform-Independent**, looks the exact same everywhere, and is **Lightweight**. (All Swing component names start with a 'J', e.g., `JButton`).

**Concept Diagram: Swing Implementation Architecture**
*(Draw this to show how Swing handles drawing things separately!)*
```text
  [Your Java Swing Code] (JFrame, JButton)
            |
            V
  [Java Pluggable Look and Feel (PLAF)]
            |
            V
  [Java 2D Graphics Engine]
            |
            V
  (Draws pure dots/pixels directly on any OS screen)
```

---

## 3. Important Components & Layout Managers

When you build a window (`Frame`), you can't just throw buttons inside randomly. You need a **Layout Manager** to tell Java exactly where to place them securely (like a strict grid, or pushed to the window borders).

**Top 3 Layout Managers for Exams:**
1.  **`FlowLayout` (Default for Applet/Panel):** Places items in a straight horizontal row (left to right) exactly like writing words on a paper page. If space runs out, it simply drops to the next empty line.
2.  **`GridLayout`:** Cuts the screen into a neat table of rows and columns (e.g., a simple Calculator keypad). Every box is forced to be the exact same size.
3.  **`BorderLayout` (Default for Frame/Window):** Gives you exactly 5 fixed slots: `North`, `South`, `East`, `West`, and `Center`. 

**Key Input Component:**
*   **`Checkbox`:** A tiny square tick box. You can select multiple items at once.
*   **`CheckboxGroup` with `Checkbox`:** Turns square checkboxes into **Radio Buttons** (circular dots). If you safely put them in a group, you can strictly only select *one* dot at a time (e.g., Gender: Male/Female).

---

## 4. Event Handling (Making the buttons work!)

**Simple Explanation:**
A drawn button on a screen is just a dead picture; it does absolutely nothing by itself. When you click it, it generates a signal ("I was clicked!"). This is the **Event**. You must assign an **Event Listener** (like a dedicated watchman) waiting to notice that exact signal so it can perform an action.

**Exam Code Snippet (AWT Frame + Button Click Event):**
```java
import java.awt.*;
import java.awt.event.*; // Need this for Event Handling

// 1. Extend Frame and Implement ActionListener
class MyWindow extends Frame implements ActionListener {
    TextField txt;
    Button btn;

    public MyWindow() {
        // 2. Setup the GUI
        txt = new TextField("Hello...");
        txt.setBounds(50, 50, 150, 20); // rx, ry, width, height
        
        btn = new Button("Click Me!");
        btn.setBounds(50, 100, 100, 30);
        
        // 3. Attach the Listener to the Button!
        btn.addActionListener(this); 

        add(txt); 
        add(btn);
        setSize(300, 300);
        setLayout(null); // Manual layout
        setVisible(true); // Always needed to show the window!
    }

    // 4. This method runs when the button is successfully clicked
    public void actionPerformed(ActionEvent e) {
        txt.setText("Button was Clicked!");
    }

    public static void main(String args[]) {
        new MyWindow();
    }
}
```

### How to Answer Exam Questions on this Topic:
*   **Layout Managers (10M):** Describe Flow, Grid, and Border. Emphasize that Border has 5 fixed regions. If asked to write code, always wrap it in `setLayout(new BorderLayout())`.
*   **Event Handling Code (12M):** Memorize the snippet above. The 3 critical steps are implementing `ActionListener`, calling `btn.addActionListener(this)`, and overriding `actionPerformed()`.
