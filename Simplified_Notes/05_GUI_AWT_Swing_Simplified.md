# Module 5: GUI, AWT & Swing (Simplified)

## 1. GUI vs CUI

**Simple Explanation:**
*   **CUI (Command User Interface):** Like communicating with an old typewriter. You must type secret code words to make the computer do anything (like typing `open_folder`). It's very fast for experts, but terrible for beginners because there is nothing to click.
*   **GUI (Graphical User Interface):** Exactly like using your modern smartphone. You use a finger or a mouse to click on colorful pictures (icons) and buttons. It relies on seeing things rather than memorizing codes.

**Concept Diagram: GUI vs CUI**

```text
         [ GUI ]                            [ CUI ]
  (Graphical User Interface)        (Command User Interface)

   [Folder Icon]   [X] Button               C:\> _
   [Click Here]    (Mouse)                  C:\> open folder
         |                                        |
  Visuals and Mouse clicks.         Only a completely black screen
  Easy for normal people.           where you type words.
```

**Key Differences (10 Points for Exams):**

| Point | Feature | GUI (Modern Visuals) | CUI (Old Black Screen) |
| :--- | :--- | :--- | :--- |
| **1** | **How you interact** | You use a Mouse to click on pictures and buttons. | You use a Keyboard strictly to type text commands. |
| **2** | **Ease of Use** | Extremely easy for everyone to learn naturally. | Very hard; you must memorize exact secret commands. |
| **3** | **Memory Usage** | High memory (pictures and colors take up space). | Extremely low memory (just plain text). |
| **4** | **Speed to work** | Slower for experts (have to move the mouse around). | Very fast for experts (just type rapidly). |
| **5** | **Multitasking** | Easy (you can see many distinct windows open at once). | Very difficult to handle multiple things. |
| **6** | **Graphics & Colors** | Heavily supported (High resolution, 3D buttons). | Not supported at all (just basic green/white text). |
| **7** | **Feedback** | Immediate visual feedback (button turns blue when clicked). | Only text-based error messages. |
| **8** | **Precision required** | Low precision (you just point and click roughly). | High precision (one spelled mistake and the command fails). |
| **9** | **User Base** | Designed for completely normal, everyday people. | Designed mostly for system admins and programmers. |
| **10** | **Devices used** | Mouse, Touchscreen, Keyboard, Joystick. | Strictly only the Keyboard. |

**Real-World Example:**

*   **GUI Example:** Using the modern **Windows 11** desktop. You double-click the yellow folder icon to open it, then drag a photo into the trash bin.
*   **CUI Example:** Using the old **MS-DOS** black screen (or Command Prompt). To delete a photo, you must correctly type `del C:\Photos\my_trip.jpg` and hit Enter.

---

## 2. AWT vs Swing (Crucial Comparison)

**Simple Explanation:**
When building a visual desktop app in Java, you need buttons and windows.
*   **AWT (Abstract Window Toolkit):** The old lazy way. Java asks the computer's Operating System (OS) to draw a button for it. So, a button looks like a Windows button on a PC, and a Mac button on a Mac. It relies heavily on the OS (Platform Dependent).
*   **Swing:** The new smart way. Java draws its own beautiful buttons directly using pure Java code. It never asks the OS for help. Because Java controls it 100%, the app will comfortably look identical everywhere!

**Concept Diagram: AWT vs Swing**

```text
       [ AWT ]                             [ Swing ]
    (Asks OS for help)                  (Does it all alone)

 [Java App] --> "Hey Windows,       [Java App] --> "I will draw it 
                 draw a Button!"                    myself."
                       |                                |
  Button looks different everywhere.    Button looks identical on ALL
  (Platform Dependent / Heavy)          computers! (Lightweight)
```

**Key Differences (10 Points for Exams):**

| Point | Feature | AWT (The Old Way) | Swing (The Modern Way) |
| :--- | :--- | :--- | :--- |
| **1** | **Who draws it?** | The Operating System (Windows/Mac) draws the buttons. | Java draws the buttons purely by itself. |
| **2** | **Look and Feel** | Changes depending on the computer you use. | Looks exactly the same on every single computer. |
| **3** | **Weight / Speed** | **Heavyweight** (runs slower because it talks to the OS). | **Lightweight** (runs extremely fast purely in Java). |
| **4** | **Platform Dependency** | Platform Dependent (relies strictly on the OS). | Platform Independent (Java handles it alone). |
| **5** | **Component Names** | Uses normal names (e.g., `Button`, `TextField`). | Names always start with a 'J' (e.g., `JButton`, `JTextField`). |
| **6** | **MVC Pattern** | Does NOT comfortably support Model-View-Controller. | Strictly built fully on the Model-View-Controller design. |
| **7** | **Advanced Features** | Very basic set of tools (few components). | Extremely rich toolset (has tabs, trees, advanced tables). |
| **8** | **Package Name** | Located strictly in the `java.awt.*` package. | Located strictly in the `javax.swing.*` package. |
| **9** | **Pluggable Look** | Impossible (stuck with the OS look). | Possible (you can completely change the visual theme). |
| **10** | **Parent Hierarchy** | `java.awt.Component` | `javax.swing.JComponent` |

**Real-World Example:**

*   **AWT Example:** You build a calculator app using AWT. You run it on Windows, it looks perfectly like Windows. You send it to your friend on an Apple Mac, and suddenly all the buttons structurally shift and look like Apple buttons!
*   **Swing Example:** You build a custom-branded music player with exactly orange buttons using Swing. It doesn't matter who runs it—it will always forcefully look exactly like your custom orange player, everywhere!

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
