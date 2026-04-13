# Module 4: MVC Architecture and Advanced Swing

Swing components expand largely on AWT, being entirely written in Java, rendering them **lightweight**. Unlike AWT's OS-native dependencies, Swing implements the **Pluggable Look and Feel (PLAF)** paradigm and follows the Model-View-Controller framework.

---

## 1. The MVC Design Pattern 

The MVC Design Pattern relies on the software engineering principle of "Separation of Concerns". 
- **Model**: Encapsulates the core data and business logic. Independent of the visual layout.
- **View**: Encapsulates the visual presentation of data. A single model can have multiple varying views interacting with it.
- **Controller**: Processes user input events (clicks, keystrokes) and makes requests to the model to update states.

### 📌 Activity Question: MVC in Swing vs Enterprise
**Question:** Explain the MVC Design Pattern in Java Swing and discuss its advantages for GUI applications. Illustrate how it is implemented in components like a Swing Button.

**Solution/Explanation:**
- **Advantages**: 
  - **Modularity**: Data representation is handled completely independently from user inputs.
  - **Scalability**: Multiple views can dynamically connect to the same model.
  - **Maintainability**: You can alter the UI layout without ever breaking the core code logic.
- **MVC within Swing**: Swing components slightly merge the View and Controller into a single object known as a **Delegate** (`ComponentUI`).
- **Button Illustration**: 
  - *Model* (`ButtonModel`): Defines the button's behavior and internal state (pressed/unpressed).
  - *View & Controller* (`ButtonUI` via PLAF): Draws the button's graphics on screen and inherently processes mouse clicks, mapping those directly to update the `ButtonModel`.

---

## 2. Layout Management

Unlike absolute positioning, Layout Managers dynamically control the layout algorithm of a `Container`, allowing applications to be resize-resistant.

### 📌 Activity Question: Choosing Layout Managers
**Question:** A GUI freezes or becomes distorted when resized. Explain how layout managers solve this and design a layout for a calculator form, justifying your choices.

**Solution/Explanation:**
- **Solving Freezes/Distortions**: Hardcoding coordinates via `setBounds(x, y, w, h)` ignores screen resolutions or window resizes, breaking layouts. Layout Managers natively reposition and resize components dynamically in respect to boundaries, preventing distortion.
- **Calculator Layout Design**:
  - The highest level frame can use a **BorderLayout**. 
  - **NORTH**: A `JTextField` acting as the display screen.
  - **CENTER**: A `JPanel` containing the number/operator buttons. 
  - The `JPanel` inside the CENTER should use a **GridLayout(4,4)** to align standard calculator buttons as an evenly spaced mathematical grid.

### Common Layout Managers
1. **FlowLayout**: Arranges components linearly (left-to-right, then down). Default for `JPanel`.
2. **BorderLayout**: Arranges components along five regions: `NORTH`, `SOUTH`, `EAST`, `WEST`, and `CENTER`. Default for `JFrame`.
3. **GridLayout**: Components are placed in predefined rectangular grids (`rows` x `columns`). Every cell maintains exactly the same dimensions.
4. **CardLayout**: Allows managing multiple components overlapping each other, showing only one card/component at a time (like a setup wizard).
5. **GridBagLayout**: Extends standard grids by allowing flexible component alignment, variable widths, and specific weighting parameters.

---

## 3. Swing Components: Inputs and Choices

### 📌 Activity Question: Text and Choice Components
**Question:** Explain all text input and choice components in Swing with examples and use cases.

**Solution/Explanation:**
- **Text Components**:
  - `JTextField`: Small, single-line data entry (e.g., username).
  - `JPasswordField`: Functions identical to `JTextField` but masks inputs with echo characters.
  - `JTextArea`: Multi-line large inputs (e.g., bio or address inputs). Usually wrapped in a `JScrollPane` to provide scrollbars.
- **Choice Components**:
  - `JCheckBox`: Binary toggle that permits selecting multiple independent choices explicitly.
  - `JRadioButton`: Circular selector. Grouped via `ButtonGroup` to force mutually-exclusive binary choices (e.g., Male/Female).
  - `JComboBox`: Drop-down menu that minimizes vertical space while preserving dozens of choices (e.g., Country Selector).
  - `JSlider`: Drag-slider allowing numerical inputs across a continuous visual spectrum (e.g., Volume control). 
  > *Note: For a music player application needing volume control and mode selection, a `JSlider` is perfectly suited for volume, while a `JComboBox` or `JRadioButton` matches mode selections securely.*

---

## 4. Component Organizers (MDI Applications)

For complex interfaces displaying robust data sets, standard panels are insufficient. 

### 📌 Activity Question: Advanced Organizers and MDI
**Question:** A desktop application needs multiple internal windows. Explain component organizers in Swing and how `JDesktopPane` and `JInternalFrame` implement MDI.

**Solution/Explanation:**
- **JTabbedPane**: Stacks multiple panels directly atop one another using small clickable tabs at the top of the pane.
- **JSplitPane**: Divides two components within a single container side-by-side or top-and-bottom with a draggable visual divider.
- **Multiple Document Interface (MDI)**: Operating environments where multiple sub-windows reside under a single parent application window (e.g., Photoshop or IDEs).
  - `JDesktopPane`: The invisible background container bounding everything.
  - `JInternalFrame`: Standardized, fully-functional sub-windows (containing minimizes, closes, and titles) structurally mapped inside the `JDesktopPane`.

---

## 5. Menus, Dialogs, and Data Visualization

### Menus
- **JMenuBar**: Anchored to the top of the `JFrame`.
- **JMenu**: Drop-down topics positioned heavily across the `JMenuBar`.
- **JMenuItem**: Actionable sub-topics lying inherently inside `JMenu` blocks.

### Dialog Boxes
Popup windows demanding user awareness.
- **JOptionPane**: Static convenience class generating standard alerts, warnings, and confirmations seamlessly.
- **JFileChooser / JColorChooser**: Specialized GUI dialogs to pick directories natively or customize colors graphically.

### 📌 Activity Question: Data Visualization (File Explorer)
**Question:** Design a file explorer interface using Swing. Explain how `JTree`, `JTable`, and `JScrollPane` work together.

**Solution/Explanation:**
1. **Container Setup**: Utilize a `JSplitPane` separating the window horizontally.
2. **Left Pane (`JTree`)**: Represents directory structures logically as hierarchical collapsible nodes. Users click folders here to inspect contents.
3. **Right Pane (`JTable`)**: Represents multi-dimensional data grids displaying the interior of the selected directory (Columns: Name, Size, Date Modified). 
4. **Scrolling (`JScrollPane`)**: Since folders hold thousands of items, wrap both `JTree` and `JTable` inside distinct `JScrollPane` instances to enable automatic scrollbar management without shrinking the visualizations vertically.

```java
import javax.swing.*;

public class BasicExplorerDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("File Explorer Concept");

        // Hierarchy visualization
        JTree tree = new JTree(); 
        // Metric data visualization
        String[][] data = {{"report.pdf", "150KB", "2026"}, {"picture.png", "2MB", "2026"}};
        String[] cols = {"File Name", "Size", "Date"};
        JTable table = new JTable(data, cols);

        // Splitting into Dual Panes wrapped natively with Scrollbars
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(tree), new JScrollPane(table));
        
        // Initial setup for the divider 
        splitPane.setDividerLocation(200);

        frame.add(splitPane);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
```
**Expected Terminal Output:** 
```text
(A full-frame window launches split down the middle. The left half maintains a Java hierarchy tree. The right half maintains an Excel-esque table layout showing distinct file mappings. Draggable scrollbars populate dynamically.)
```
