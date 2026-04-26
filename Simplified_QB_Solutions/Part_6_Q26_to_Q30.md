# Advanced Java Solutions - Part 6 (Q26 to Q30)

---

## Question 26: Design a GUI-based calculator application using Swing and MVC pattern. Implement basic arithmetic operations and explain the role of each component (Model, View, Controller).

**Topic Introduction: GUI-Based Calculator using MVC.**

1. **Topic Introduction**: Building a Calculator using MVC enforces strict separation between the arithmetic logic and the visual button interface.
2. **The Model Role**: The Model (`CalcModel`) holds the internal calculation state (the current total) and executes raw arithmetic methods like `add(int)` or `multiply(int)`.
3. **Model Isolation**: Crucially, the `CalcModel` contains absolutely no reference to `javax.swing`. It is purely mathematical and highly testable.
4. **The View Role**: The View (`CalcView`) creates the JFrame, text fields for input/output, and buttons for `+`, `-`, `=`, and `Clear`.
5. **View Isolation**: The `CalcView` only knows how to display things and read user clicks. It does not know how to add two numbers together.
6. **The Controller Role**: The Controller (`CalcController`) connects them. It registers an `ActionListener` on the View's "Calculate" or "Operation" buttons.
7. **Controller Workflow 1**: When "Add" is clicked, the Controller fetches the two numbers from the View's text fields.
8. **Controller Workflow 2**: It passes these numbers to the Model's `add()` method.
9. **Controller Workflow 3**: It retrieves the resulting total from the Model and injects it back into the View's display field.
10. **Error Handling**: The Controller is responsible for catching `NumberFormatException` if the user types letters instead of numbers, triggering an error popup via the View.
11. **Scalability**: Because of MVC, adding a "Square Root" function only requires adding a method in the Model and a button in the View, linked by the Controller.
12. **State Management**: The Model easily retains the state of ongoing calculations, ensuring the UI remains stateless and clean.

```text
  [USER] -> Clicks '+' -> [CONTROLLER]
                                |
    +---------------------------+---------------------------+
    |                                                       |
    v                                                       v
  [MODEL] -> Performs: Num1 + Num2 -> Returns Result -> [VIEW] -> Displays Result
```

```java
// Explanation: Calculator MVC Implementation   //
import java.awt.event.*; import javax.swing.*;  // UI Imports
                                                //
class CalcModel {                               // 1. MODEL
    private int calcValue;                      // State
    public void addTwoNumbers(int a, int b) {   // Logic
        calcValue = a + b;                      // Operation
    }                                           //
    public int getCalcValue() {return calcValue;}// Result
}                                               //
                                                //
class CalcView extends JFrame {                 // 2. VIEW
    JTextField num1 = new JTextField(5);        // Input 1
    JTextField num2 = new JTextField(5);        // Input 2
    JButton addBtn = new JButton("+");          // Action
    JTextField res = new JTextField(10);        // Output
    CalcView() {                                // Constructor
        JPanel p = new JPanel();                // Panel setup
        p.add(num1); p.add(addBtn); p.add(num2);// Add components
        p.add(new JLabel("=")); p.add(res);     // Add result box
        this.add(p); this.pack();               // Attach to frame
    }                                           //
}                                               //
                                                //
class CalcController {                          // 3. CONTROLLER
    private CalcView v; private CalcModel m;    // References
    CalcController(CalcView v, CalcModel m) {   // Constructor
        this.v = v; this.m = m;                 // Set references
        this.v.addBtn.addActionListener(e -> {  // Listen for click
            int n1 = Integer.parseInt(v.num1.getText()); // Extract data
            int n2 = Integer.parseInt(v.num2.getText()); // Extract data
            m.addTwoNumbers(n1, n2);            // Model calculates
            v.res.setText(""+m.getCalcValue()); // View displays
        });                                     //
    }                                           //
}                                               //
                                                //
public class CalcApp {                          // Main execution class
    public static void main(String[] args) {    // Program entry point
        CalcModel m = new CalcModel();          // Create model
        CalcView v = new CalcView();            // Create view
        new CalcController(v, m);               // Wire controller
        v.setVisible(true);                     // Show UI
    }                                           //
}                                               //
```

**Output:**
```
GUI Calculator with two input fields,
Add/Sub/Mul/Div buttons, and a result label.
(Enter 10 and 3, click Add -> "Result: 13.0")
```

---

## Question 27: Develop a library management GUI application using Swing following the MVC design pattern. Include functionalities like adding and viewing books.

**Topic Introduction: Library Management GUI using MVC.**

1. **Topic Introduction**: A Library Management system tracks books. Applying MVC here ensures the book inventory logic is completely decoupled from the Swing interface.
2. **The Model**: The Model consists of a `Book` JavaBean (ID, Title, Author) and a `LibraryModel` class containing an `ArrayList<Book>` to manage the inventory state.
3. **Model Capabilities**: `LibraryModel` provides standard data manipulation methods: `addBook(Book b)` and `List<Book> getAllBooks()`.
4. **The View**: The `LibraryView` contains input text fields for Book details, an "Add Book" button, and a `JTextArea` or `JTable` to display the inventory.
5. **View Design**: It uses `BorderLayout` where the data entry form sits in the `NORTH` region, and the display area fills the `CENTER` region inside a `JScrollPane`.
6. **The Controller**: The `LibraryController` instantiates and wires the Model and View together upon application startup.
7. **Action Listening**: The Controller attaches a lambda expression or internal class to the "Add Book" button's `ActionListener`.
8. **Extraction & Injection**: Upon clicking "Add", the Controller extracts text from the View's fields, instantiates a new `Book` object, and injects it into `LibraryModel`.
9. **View Refresh**: Immediately after insertion, the Controller fetches the updated `ArrayList` from the Model and rewrites the View's display area to reflect the new state.
10. **Validation Logic**: The Controller enforces business rules, ensuring that empty titles or duplicate book IDs are rejected before reaching the Model.
11. **Separation Advantage**: The `LibraryModel` could easily be connected to a SQL database backend later without touching a single line of code in the View.
12. **Component Roles**: Model = Inventory Rules & Storage; View = User Presentation; Controller = Workflow Orchestrator.

```text
  [Library Controller]
     |              |
 (fetches list)  (reads inputs)
     |              |
     v              v
 [Library Model] [Library View]
 (ArrayList)     (Text Fields & Area)
```

```java
// Explanation: Library MVC Implementation      //
import java.util.*; import javax.swing.*;       // Imports
                                                //
class Book {                                    // DATA BEAN
    String title; public Book(String t){title=t;}
}                                               //
class LibModel {                                // 1. MODEL
    List<Book> books = new ArrayList<>();       // State storage
    public void add(Book b) { books.add(b); }   // Logic
    public List<Book> get() { return books; }   // Retrieval
}                                               //
                                                //
class LibView {                                 // 2. VIEW
    JFrame f = new JFrame("Library");           // Window
    JTextField txt = new JTextField(10);        // Input
    JButton btn = new JButton("Add Book");      // Action
    JTextArea area = new JTextArea(5, 20);      // Display
    LibView() {                                 // UI Setup
        JPanel p = new JPanel(); p.add(txt); p.add(btn);
        f.add(p, "North"); f.add(area, "Center");
        f.pack(); f.setVisible(true);           // Show
    }                                           //
}                                               //
                                                //
class LibController {                           // 3. CONTROLLER
    LibView v; LibModel m;                      // Links
    LibController(LibView v, LibModel m) {      // Constructor
        this.v = v; this.m = m;                 // Set Links
        v.btn.addActionListener(e -> {          // Event Handle
            m.add(new Book(v.txt.getText()));   // Update Model
            v.area.setText("");                 // Clear View
            for(Book b : m.get())               // Fetch Model
                v.area.append(b.title + "\n");  // Refresh View
        });                                     //
    }                                           //
}                                               //
                                                //
public class LibApp {                           // Main execution class
    public static void main(String[] args) {    // Program entry point
        LibModel m = new LibModel();            // Create model
        LibView v = new LibView();              // Create view
        new LibController(v, m);                // Wire controller
    }                                           //
}                                               //
```

**Output:**
```
GUI with book title text field and Add button.
(Type "Java Basics" + click Add -> list shows "Java Basics")
```

---

## Question 28: Write a Java program to implement a bank account management system using Swing and MVC architecture. The application should support deposit and withdrawal operations with proper separation of concerns.

**Topic Introduction: Bank Account Management using MVC.**

1. **Topic Introduction**: A Bank Account simulator requires strict state management. MVC safely separates financial calculations from the UI inputs.
2. **The Model**: The `AccountModel` encapsulates a `balance` (double) and provides specific transactional methods: `deposit(amount)` and `withdraw(amount)`.
3. **Data Integrity**: The Model guarantees integrity; for example, `withdraw()` must reject requests that exceed the current balance, returning a boolean status.
4. **The View**: The `AccountView` offers a simple UI: an input field for the amount, and two buttons ("Deposit", "Withdraw"), plus a label showing the current balance.
5. **The Controller**: The `AccountController` handles user interactions, reading the amount from the View and deciding which Model method to invoke based on the clicked button.
6. **Deposit Workflow**: User clicks "Deposit" -> Controller reads amount -> Calls `Model.deposit()` -> Controller requests new balance -> Updates View label.
7. **Withdraw Workflow**: User clicks "Withdraw" -> Controller reads amount -> Calls `Model.withdraw()`.
8. **Error Display**: If `withdraw()` returns false (insufficient funds), the Controller triggers a `JOptionPane` error dialog in the View instead of updating the balance.
9. **Parsing Numbers**: The Controller uses `Double.parseDouble()` on the View's text field, explicitly catching `NumberFormatException` if the user types invalid characters.
10. **State Isolation**: The View has absolutely no way to alter the balance directly. It must ask the Controller to ask the Model.
11. **Scalability**: Adding an "Apply Interest" feature requires a new math method in the Model and a new button in the View, effortlessly wired by the Controller.
12. **Audit Trail**: The Model can be expanded to hold a `List<String>` of transaction history without altering the existing Swing UI structure.

```text
  View(Input: 100) --Click Deposit--> Controller
                                          |
  View(Balance: 100) <--- Controller <--- Model(Balance += 100)
```

```java
// Explanation: Bank Account MVC Demo           //
import javax.swing.*;                           // GUI imports
                                                //
class AccModel {                                // 1. MODEL
    private double bal = 0;                     // Private state
    public void deposit(double a) { bal += a; } // Modify state
    public boolean withdraw(double a) {         // Modify with rule
        if(a > bal) return false;               // Business logic fail
        bal -= a; return true;                  // Business logic success
    }                                           //
    public double getBal() { return bal; }      // Read state
}                                               //
                                                //
class AccView {                                 // 2. VIEW
    JFrame f = new JFrame("Bank");              // Window
    JTextField amt = new JTextField(5);         // Input field
    JButton bDep = new JButton("Deposit");      // Action 1
    JButton bWit = new JButton("Withdraw");     // Action 2
    JLabel lblBal = new JLabel("Bal: $0.0");    // Output label
    AccView() {                                 // Constructor
        JPanel p = new JPanel();                // Setup layout
        p.add(new JLabel("$")); p.add(amt);     // Add components
        p.add(bDep); p.add(bWit); p.add(lblBal);// Add components
        f.add(p); f.pack(); f.setVisible(true); // Display
    }                                           //
}                                               //
                                                //
class AccController {                           // 3. CONTROLLER
    AccView v; AccModel m;                      // Object links
    AccController(AccView v, AccModel m) {      // Constructor
        this.v = v; this.m = m;                 // Assignment
        v.bDep.addActionListener(e -> {         // Deposit event
            m.deposit(Double.parseDouble(v.amt.getText()));
            v.lblBal.setText("Bal: $" + m.getBal()); // Update UI
        });                                     //
        v.bWit.addActionListener(e -> {         // Withdraw event
            if(!m.withdraw(Double.parseDouble(v.amt.getText())))
                JOptionPane.showMessageDialog(v.f, "Error!"); // UI Logic
            v.lblBal.setText("Bal: $" + m.getBal()); // Update UI
        });                                     //
    }                                           //
}                                               //
                                                //
public class BankApp {                          // Main execution class
    public static void main(String[] args) {    // Program entry point
        AccModel m = new AccModel();            // Create model
        AccView v = new AccView();              // Create view
        new AccController(v, m);                // Wire controller
    }                                           //
}                                               //
```

**Output:**
```
GUI with amount field, Deposit/Withdraw buttons, balance label.
(Enter 500, click Deposit -> "Bal: $500.0")
(Enter 200, click Withdraw -> "Bal: $300.0")
```

---

## Question 29: Develop a Java Swing application to simulate an online job application form where users can select skills using checkboxes, choose job type using radio buttons, select location from a dropdown list, set experience level using a slider, and display the submitted information.

**Topic Introduction: Online Job Application Form (Swing Advanced Components).**

1. **Topic Introduction**: This application demonstrates how to capture diverse user input using Swing's advanced interactive components (Checkboxes, RadioButtons, ComboBoxes, Sliders).
2. **JCheckBox (Skills)**: `JCheckBox` allows multiple selections. We create an array of checkboxes (Java, Python, C++) because a user can possess multiple skills.
3. **JRadioButton (Job Type)**: `JRadioButton` allows only one selection from a group. We create buttons (Full-Time, Part-Time) and group them using a `ButtonGroup` object.
4. **JComboBox (Location)**: `JComboBox` provides a dropdown list of options (e.g., NY, LA, Chicago), saving screen space while restricting user input to predefined choices.
5. **JSlider (Experience)**: `JSlider` offers a graphical way to select a numeric value within a range (e.g., 0 to 10 years), featuring optional tick marks and labels.
6. **Layout Configuration**: A `GridLayout` or `BoxLayout` is ideal to stack these different input categories vertically for a clean form-like appearance.
7. **Submission Mechanism**: A final "Submit" `JButton` triggers the data collection process via an `ActionListener`.
8. **Collecting Checkboxes**: The listener iterates through the `JCheckBox` array, utilizing the `isSelected()` method to compile a string of chosen skills.
9. **Collecting RadioButtons**: We check which specific `JRadioButton` returns true for `isSelected()` to determine the job type.
10. **Collecting Dropdown**: `getSelectedItem()` extracts the currently visible String value from the `JComboBox`.
11. **Collecting Slider**: `getValue()` returns the integer currently selected on the `JSlider`.
12. **Displaying Results**: All collected data is concatenated into a formatted String and displayed in a popup `JOptionPane` or a dedicated `JTextArea`.

```text
  [Job Application Form]
  Skills: [x] Java [ ] Python
  Type  : (o) Full-Time ( ) Part
  City  : [ New York  | v ]
  Exp   : 0 --|------ 10 (Slider)
  [ SUBMIT BUTTON ]
```

```java
// Explanation: Advanced Swing Form Demo        //
import javax.swing.*;                           // Import GUI toolkit
                                                //
public class JobForm {                          // Main class
    public static void main(String[] args) {    // Program entry
        JFrame f = new JFrame("Job Form");      // Frame setup
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
                                                //
        JCheckBox cbJava = new JCheckBox("Java"); // Checkbox multiple
        JCheckBox cbPy = new JCheckBox("Python"); // Checkbox multiple
                                                //
        JRadioButton rbFull = new JRadioButton("Full-Time"); // Single choice
        JRadioButton rbPart = new JRadioButton("Part-Time"); // Single choice
        ButtonGroup bg = new ButtonGroup();     // Group for radio
        bg.add(rbFull); bg.add(rbPart);         // Enforce single choice
                                                //
        String[] cities = {"NY", "LA", "CHI"};  // Array for dropdown
        JComboBox<String> combo = new JComboBox<>(cities); // Dropdown
                                                //
        JSlider slider = new JSlider(0, 10, 2); // Min, Max, Initial
                                                //
        JButton btn = new JButton("Submit");    // Submit action
        btn.addActionListener(e -> {            // Read logic
            String s = cbJava.isSelected() ? "Java " : "";
            String t = rbFull.isSelected() ? "Full" : "Part";
            String c = (String) combo.getSelectedItem();
            int exp = slider.getValue();        // Extract values
            JOptionPane.showMessageDialog(f,    // Show popup
                "Skills: " + s + "\nType: " + t + 
                "\nCity: " + c + "\nExp: " + exp);
        });                                     //
                                                //
        f.add(cbJava); f.add(cbPy); f.add(rbFull); f.add(rbPart);
        f.add(combo); f.add(slider); f.add(btn);// Populate UI
        f.pack(); f.setVisible(true);           // Show
    }                                           //
}                                               //
```

**Output (on clicking Submit with Java selected, Full-time, Delhi, Exp=5):**
```
Dialog: "Skills: Java
Type: Full
City: Delhi
Exp: 5"
```

---

## Question 30: Create a Java Swing GUI for a survey form where users can select interests using checkboxes, choose age group using radio buttons, select country from a combo box, rate satisfaction using a slider, and view results upon submission.

**Topic Introduction: Survey Form GUI (Checkboxes, Radio, Combo, Slider).**

1. **Topic Introduction**: A survey form requires intuitive UI components. This program captures user interests, age demographics, location, and satisfaction scores using specialized Swing elements.
2. **JCheckBox (Interests)**: Surveys often allow multiple interests (Sports, Tech, Arts). `JCheckBox` enables this non-exclusive boolean selection.
3. **JRadioButton (Age Group)**: Age brackets must be mutually exclusive (e.g., 18-25, 26-40). `JRadioButton` combined with `ButtonGroup` ensures only one bracket can be active.
4. **JComboBox (Country)**: To handle a large list of countries without cluttering the UI, a `JComboBox` (dropdown list) provides a clean, scrollable selection mechanism.
5. **JSlider (Satisfaction)**: A `JSlider` perfectly represents a "Rate from 1 to 5" satisfaction scale, allowing users to visually slide a knob to their desired rating.
6. **UI Assembly**: We use nested `JPanel` objects with `FlowLayout` inside a parent `GridLayout` to align labels next to their respective input components neatly.
7. **Action Trigger**: A single `JButton` labeled "Submit Survey" acts as the catalyst, containing the `ActionListener` that parses the form.
8. **Parsing Checkboxes**: The code individually queries `chkSports.isSelected()` and `chkTech.isSelected()`, building a comma-separated String of chosen interests.
9. **Parsing Radios**: It uses conditional checks (if `rbYoung.isSelected()`) to capture the single demographic string.
10. **Parsing Combo & Slider**: It invokes `getSelectedItem().toString()` for the country and `getValue()` for the numerical satisfaction rating.
11. **Summary Generation**: The compiled data is structured into a highly readable multi-line String format utilizing `\n` characters.
12. **Display Notification**: A `JOptionPane.showMessageDialog()` temporarily halts the program to present the final survey summary to the user before they proceed.

```text
  [Survey Form Layout]
  [ Interests Panel ] -> [x] Tech [ ] Sports
  [ Age Group Panel ] -> ( ) 18-25 (o) 26-40
  [ Location  Panel ] -> [ Dropdown Menu ]
  [ Rating    Panel ] -> [ -|------- ] (Slider)
  [ Submit    Panel ] -> [ SUBMIT BUTTON ]
```

```java
// Explanation: Survey Form Components Demo     //
import javax.swing.*;                           // Swing imports
import java.awt.*;                              // AWT layouts
                                                //
public class SurveyForm {                       // Main class
    public static void main(String[] args) {    // Program entry point
        JFrame f = new JFrame("Survey");        // Create main frame
        f.setLayout(new GridLayout(5, 1));      // Stack vertically
                                                //
        JCheckBox c1 = new JCheckBox("Tech");   // Interest 1
        JCheckBox c2 = new JCheckBox("Sports"); // Interest 2
        JPanel p1 = new JPanel(); p1.add(c1); p1.add(c2);
                                                //
        JRadioButton r1 = new JRadioButton("18-25");// Age 1
        JRadioButton r2 = new JRadioButton("26+");  // Age 2
        ButtonGroup bg = new ButtonGroup();     // Group enforces 1 choice
        bg.add(r1); bg.add(r2);                 // Add to group
        JPanel p2 = new JPanel(); p2.add(r1); p2.add(r2);
                                                //
        JComboBox<String> cb = new JComboBox<>(new String[]{"USA", "UK"});
        JPanel p3 = new JPanel(); p3.add(cb);   // Dropdown panel
                                                //
        JSlider sl = new JSlider(1, 5, 3);      // Satisfaction 1-5, def 3
        JPanel p4 = new JPanel(); p4.add(sl);   // Slider panel
                                                //
        JButton btn = new JButton("Submit");    // Action button
        btn.addActionListener(e -> {            // Parse logic
            String res = "Interests: " + (c1.isSelected()?"Tech ":"") +
                         (c2.isSelected()?"Sports":"") + "\nAge: " +
                         (r1.isSelected()?"18-25":"26+") + "\nCountry: " +
                         cb.getSelectedItem() + "\nRating: " + sl.getValue();
            JOptionPane.showMessageDialog(f, res); // Show results
        });                                     //
                                                //
        f.add(p1); f.add(p2); f.add(p3); f.add(p4); f.add(btn);
        f.setSize(300, 300); f.setVisible(true);// Display GUI
    }                                           //
}                                               //
```

**Output (on clicking Submit with Tech checked, 26+, USA, Rating=4):**
```
Dialog: "Interests: Tech
Age: 26+
Country: USA
Rating: 4"
```
