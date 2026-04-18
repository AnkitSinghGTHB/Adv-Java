# Activity 4: Menus, Dialogs & Component Organizers

---

### **1. Explain the process of menu creation in Swing. Include JMenuBar, JMenu, and JMenuItem.**

**Answer:**
Building a menu in Swing follows a strict **hierarchical composition** model. You construct from the bottom up (Items -> Menu -> Bar -> Frame).

**The Process & Hierarchy:**
1.  **`JMenuItem`:** The actual clickable buttons inside a menu (e.g., "New", "Save", "Exit").
2.  **`JMenu`:** The dropdown container that holds the `JMenuItems` (e.g., "File", "Edit").
3.  **`JMenuBar`:** The horizontal strip at the top of the window that holds the `JMenus`.

**Implementation Example:**
```java
// EXAMINER NOTE: Creating components bottom-up
JMenuItem newItem = new JMenuItem("New");
JMenuItem saveItem = new JMenuItem("Save");

JMenu fileMenu = new JMenu("File");
fileMenu.add(newItem); // Adding items to Menu
fileMenu.addSeparator(); // Adds a visual dividing line
fileMenu.add(saveItem);

JMenuBar menuBar = new JMenuBar();
menuBar.add(fileMenu); // Adding Menu to Bar

// EXAMINER NOTE: You don't use add() for the MenuBar!
frame.setJMenuBar(menuBar); 
```

---

### **2. Explain dialog boxes in Swing. Discuss OptionPane, custom dialogs, file chooser, and color chooser with use cases.**

**Answer:**
Dialog boxes are secondary pop-up windows used to interact with the user outside the main application flow. They are usually **Modal** (block the parent application until closed).

*   **`JOptionPane`:** The quickest way to create standard dialogs.
    *   *Use Case:* Show an error message (`showMessageDialog`), ask a Yes/No question (`showConfirmDialog`), or request a single string input (`showInputDialog`).
*   **`JFileChooser`:** A pre-built file explorer dialog.
    *   *Use Case:* Prompting the user to select an `.mp3` file to open, or choosing a folder to save a `.pdf` report.
*   **`JColorChooser`:** A pre-built color palette dialog.
    *   *Use Case:* Allowing the user to pick a custom theme color for the application text or background.
*   **Custom `JDialog`:** A completely blank dialog frame that the developer populates manually.
    *   *Use Case:* Creating a custom "Login Screen" pop-up that requires both a Username and Password field before granting access.

---

### **3. Explain JSplitPane and JTabbedPane with suitable examples.**

**Answer:**
Both are **Component Organizers**, meaning they don't capture input themselves, but dictate how other components share screen real estate.

**1. `JSplitPane`:**
Splits a viewing area into two resizable halves (either `HORIZONTAL_SPLIT` or `VERTICAL_SPLIT`) separated by a draggable divider.
*   *Example:* A coding IDE like Eclipse where the left side is a Project Explorer and the right side is the Code Editor.
```java
// Divides screen, left component gets 30%, right gets 70% flexibility
JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
splitPane.setDividerLocation(150); 
```

**2. `JTabbedPane`:**
Allows multiple components to share the exact same physical screen space, accessible by clicking file-folder-style tabs at the top or bottom.
*   *Example:* A web browser, or a settings menu having tabs for "General", "Audio", and "Video".
```java
JTabbedPane tabPane = new JTabbedPane();
tabPane.addTab("Home", homePanel);
tabPane.addTab("Profile", profilePanel);
```

---

### **4 & 8. What are JDesktopPane and JInternalFrame? Explain their use in MDI applications.**

**Answer:**
**MDI (Multiple Document Interface)** refers to an architecture where a single parent application window contains multiple, smaller child windows that cannot leave the parent's boundaries (e.g., Photoshop workspace).

*   **`JDesktopPane`:** Acts as the virtual "desktop" background or container. It handles window management (z-indexing, tracking active windows).
*   **`JInternalFrame`:** A lightweight replica of a `JFrame` that lives entirely inside the `JDesktopPane`. It has a title bar, minimize/maximize, and close buttons.

**Why use them in MDI?**
It declutters the user's OS taskbar. Instead of having 15 separate database query windows cluttering the Windows Taskbar, all 15 `JInternalFrames` are contained neatly inside one master Application `JFrame`. 

---

### **5. Explain the use of JList and JTable in Swing applications.**

**Answer:**
*   **`JList`:** Displays a 1-dimensional vertical list of objects where the user can select one or multiple items.
    *   *Use Case:* A list of available Wi-Fi networks, or a playlist of songs.
*   **`JTable`:** Displays complex 2-dimensional structures consisting of rows and columns. It provides built-in headers, column resizing, and cell editing.
    *   *Use Case:* Displaying the results of a `SELECT * FROM Employees` SQL query.

---

### **6. Explain component organizers in Swing: JSplitPane, JTabbedPane, JDesktopPane, and JInternalFrame. Discuss their role in complex GUI applications.**

**Answer:**
As GUI applications scale from simple forms to complex dashboards (like IDEs or ERP systems), screen space becomes the scarcest resource. Component Organizers solve this by maximizing utility:

1.  **`JTabbedPane` (Space Saving):** Condenses dozens of configuration panels into a single square. 
2.  **`JSplitPane` (Flexible Viewing):** Allows the user to dictate what is important based on monitor size by dragging the divider.
3.  **`JDesktopPane` & `JInternalFrame` (Workspace encapsulation):** Creates a contained mini-OS environment inside the app, preventing window-sprawl on the user's actual operating system.

---

### **7. Design a file explorer interface using Swing. Explain how JTree, JTable, and JScrollPane work together.**

**Answer:**
A File Explorer (like Windows File Explorer) is the perfect architectural example of combining advanced Swing components via a `JSplitPane`.

**The Design Diagram:**
```text
+----------------------------------------------------+
| File | Edit | View                 [ JMenuBar ]    |
+-------------------+--------------------------------+
| -> C:\            | Name           | Size | Type   |
|   -> Windows      | -------------- | ---- | ----   |
|   -> Users        | config.sys     | 2 KB | File   |
|     -> admin      | autoexec.bat   | 1 KB | File   |
| [ JTree ]         |        [ JTable ]              |
+-------------------+--------------------------------+
          ^--[ JSplitPane ]--^
```

**How they work together:**
1.  **`JSplitPane`:** Used to divide the application horizontally.
2.  **`JTree` (Left side):** Visualizes the hierarchical, nested directory structure. A user clicks a folder node in the `JTree` to trigger an event.
3.  **`JTable` (Right side):** Listens to the `JTree`. When a folder is clicked, the `JTable` populates its rows with the direct contents (Files) of that specific folder, displaying metadata (Name, Size, Date) in columns.
4.  **`JScrollPane`:** Both the `JTree` and `JTable` are wrapped in `JScrollPanes`. If a folder has 500 files, the table will organically scroll vertically without breaking the `JSplitPane` bounds.
