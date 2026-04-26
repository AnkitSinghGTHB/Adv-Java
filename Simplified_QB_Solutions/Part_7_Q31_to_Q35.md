# Advanced Java Solutions - Part 7 (Q31 to Q35)

---

## Question 31: Design a Java Swing application for a feedback system where users can select services using checkboxes, choose feedback type using radio buttons, select branch location from a dropdown, rate services using a slider, and display the feedback summary.

**Topic Introduction: Swing Feedback System GUI.**

1. **Topic Introduction**: This application constructs a comprehensive Feedback System UI using advanced Swing elements to gather highly structured customer reviews.
2. **JCheckBox (Services)**: Customers often use multiple services (e.g., Dining, Room Service). We deploy an array of `JCheckBox` components to capture all utilized services.
3. **JRadioButton (Feedback Type)**: Feedback naturally falls into mutually exclusive categories (Complaint, Suggestion, Compliment). We group these using a `ButtonGroup`.
4. **JComboBox (Branch)**: If a business has multiple locations, a `JComboBox` (dropdown) restricts the user to valid branches (e.g., "Downtown", "Airport").
5. **JSlider (Rating)**: We implement a `JSlider` scaled from 1 to 10 to capture a numerical, granular satisfaction score for the selected services.
6. **Layout Hierarchy**: We use nested panels with `FlowLayout` wrapped inside a main `JFrame` configured with `GridLayout` or `BoxLayout` to stack the inputs vertically.
7. **The Catalyst**: A `JButton` labeled "Submit Feedback" serves as the single point of action, triggering the data collection via its `ActionListener`.
8. **Extraction (Boolean)**: The listener checks the state of the checkboxes and radio buttons using the `isSelected()` method to build a logical profile of the feedback.
9. **Extraction (Values)**: It directly pulls the String from the combobox via `getSelectedItem()` and the integer from the slider via `getValue()`.
10. **Data Aggregation**: All extracted pieces are concatenated into a highly readable, multi-line String summary, acting as the final data payload.
11. **Visual Confirmation**: A `JOptionPane.showMessageDialog` pops up, confirming to the user exactly what data was recorded before clearing the form.
12. **Real-world Utility**: This structure is universally applicable to any kiosk, internal corporate tool, or simple desktop CRM module requiring structured input.

```text
  [Feedback System Layout]
  [ Services ] -> [x] Dining [x] Spa
  [ Category ] -> ( ) Complaint (o) Praise
  [ Location ] -> [ Airport Branch  | v ]
  [ Rating   ] -> [ ---|------- ] (Slider)
  [ Submit   ] -> [ SUBMIT FEEDBACK ]
```

```java
// Explanation: Feedback System Swing UI        //
import javax.swing.*;                           // UI Library
import java.awt.*;                              // Layouts
                                                //
public class FeedbackApp {                      // Main class
    public static void main(String[] args) {    // Program entry
        JFrame f = new JFrame("Feedback");      // Main Window
        f.setLayout(new GridLayout(6,1));       // Vertical stack
                                                //
        JCheckBox c1 = new JCheckBox("Dining"); // Service 1
        JCheckBox c2 = new JCheckBox("Spa");    // Service 2
        JPanel p1 = new JPanel(); p1.add(c1); p1.add(c2);
                                                //
        JRadioButton r1 = new JRadioButton("Complaint"); // Type 1
        JRadioButton r2 = new JRadioButton("Praise");    // Type 2
        ButtonGroup bg = new ButtonGroup();     // Group logic
        bg.add(r1); bg.add(r2);                 // Enforce 1 choice
        JPanel p2 = new JPanel(); p2.add(r1); p2.add(r2);
                                                //
        String[] branches = {"Downtown", "Airport"}; // Locations
        JComboBox<String> cb = new JComboBox<>(branches);// Dropdown
        JPanel p3 = new JPanel(); p3.add(cb);   // Add to panel
                                                //
        JSlider sl = new JSlider(1, 10, 8);     // Rating 1-10
        JPanel p4 = new JPanel(); p4.add(sl);   // Add to panel
                                                //
        JButton btn = new JButton("Submit");    // Action button
        btn.addActionListener(e -> {            // Read logic
            String res = "Services: " + (c1.isSelected()?"Dining ":"") +
                         (c2.isSelected()?"Spa":"") + "\nType: " +
                         (r1.isSelected()?"Complaint":"Praise") + 
                         "\nBranch: " + cb.getSelectedItem() + 
                         "\nRating: " + sl.getValue();
            JOptionPane.showMessageDialog(f, res); // Display summary
        });                                     //
                                                //
        f.add(p1); f.add(p2); f.add(p3); f.add(p4); f.add(btn);
        f.setSize(300, 300); f.setVisible(true);// Show UI
    }                                           //
}                                               //
```

**Output (on clicking Submit with Dining+Spa, Complaint, NYC, Rating=8):**
```
Dialog: "Services: Dining Spa
Type: Complaint
Branch: NYC
Rating: 8"
```

---

## Question 32: Develop a Swing-based notepad application featuring menus (File, Edit), a toolbar for quick actions, and dialog boxes for opening, saving, and exiting the application.

**Topic Introduction: Swing Notepad Application (Menus, Toolbars, Dialogs).**

1. **Topic Introduction**: A standard desktop Notepad requires a central text editing area wrapped by menus, toolbars, and file operation dialogs.
2. **The Core Area**: We utilize a `JTextArea` wrapped inside a `JScrollPane` placed in the `CENTER` of a `BorderLayout` to provide a massive, scrollable typing surface.
3. **Menu Bar Structure**: The top of the window features a `JMenuBar` containing standard drop-down `JMenu` objects (like "File" and "Edit").
4. **Menu Items**: Inside the `JMenu`, individual `JMenuItem` objects (like "Open", "Save", "Exit") provide clickable actions that trigger `ActionListener` events.
5. **The Toolbar**: A `JToolBar` is placed in the `NORTH` region (just below the menu), containing iconic `JButton` shortcuts for the exact same actions available in the menus.
6. **File Dialogs (JFileChooser)**: When "Open" or "Save" is clicked, a `JFileChooser` dialog pops up, allowing the user to navigate the OS file system visually.
7. **Reading Files**: The "Open" logic uses `BufferedReader` and `FileReader` to read text from the selected file and display it inside the `JTextArea`.
8. **Writing Files**: The "Save" logic uses `BufferedWriter` and `FileWriter` to extract the text from the `JTextArea` and write it to the selected file path.
9. **Exit Dialog (JOptionPane)**: Clicking "Exit" triggers a `JOptionPane.showConfirmDialog` asking "Are you sure?", providing a graceful exit mechanism.
10. **Action Code Sharing**: Good design dictates creating a single `Action` object or method for "Save" that is shared by both the Menu item and the Toolbar button.
11. **Exception Handling**: File I/O requires strict `try-catch` blocks to handle potential `IOException` scenarios gracefully without crashing the GUI.
12. **Accelerators**: Professional applications enhance menus with `setAccelerator(KeyStroke.getKeyStroke(...))` to bind shortcuts like Ctrl+S to the save action.

```text
  +-----------------------------------+
  | File  Edit  Help       (JMenuBar) |
  +-----------------------------------+
  | [Open] [Save] [Exit]   (JToolBar) |
  +-----------------------------------+
  | Dear Diary,                       |
  | This is a Notepad clone.          |
  |                      (JTextArea)  |
  +-----------------------------------+
```

```java
// Explanation: Simple Notepad with Swing       //
import javax.swing.*; import java.awt.*;        // UI Imports
import java.io.*;                               // File IO
                                                //
public class SimpleNotepad {                    // Main class
    public static void main(String[] args) {    // Program entry
        JFrame f = new JFrame("Notepad");       // Window
        JTextArea txt = new JTextArea();        // Typing area
        f.add(new JScrollPane(txt), "Center");  // Scrollable center
                                                //
        JMenuBar mb = new JMenuBar();           // Menu bar
        JMenu menuFile = new JMenu("File");     // File Menu
        JMenuItem itemOpen = new JMenuItem("Open");// Open Item
        JMenuItem itemExit = new JMenuItem("Exit");// Exit Item
        menuFile.add(itemOpen); menuFile.add(itemExit);
        mb.add(menuFile); f.setJMenuBar(mb);    // Attach menu
                                                //
        JToolBar tb = new JToolBar();           // Tool bar
        JButton btnSave = new JButton("Save");  // Save shortcut
        tb.add(btnSave);                        // Add to toolbar
        f.add(tb, "North");                     // Attach below menu
                                                //
        // Add File Chooser Logic               //
        itemOpen.addActionListener(e -> {       // Open logic
            JFileChooser jfc = new JFileChooser();// Dialog
            if(jfc.showOpenDialog(f) == JFileChooser.APPROVE_OPTION){
                try {                           // Read file content
                    BufferedReader br = new BufferedReader(
                        new FileReader(jfc.getSelectedFile()));
                    txt.read(br, null); br.close(); // Load to text area
                } catch(Exception ex) { ex.printStackTrace(); }
            }                                   //
        });                                     //
                                                //
        // Add Exit Dialog Logic                //
        itemExit.addActionListener(e -> {       // Exit logic
            int ans = JOptionPane.showConfirmDialog(f, "Exit?");
            if(ans == JOptionPane.YES_OPTION) System.exit(0);
        });                                     //
                                                //
        f.setSize(400, 300); f.setVisible(true);// Display window
    }                                           //
}                                               //
```

**Output:**
```
Notepad window with File menu (Open/Exit),
and a scrollable text editing area.
(Click Open -> File chooser dialog appears)
```

---

## Question 33: Provide a detailed comparison between AWT and Swing. Also, write short notes on five components each (such as Button, Label, TextField, Frame for AWT and JButton, JLabel, JTextField, JFrame, JTable for Swing) with examples.

**Topic Introduction: Detailed Comparison: AWT vs Swing Components.**

1. **Topic Introduction**: AWT is Java's original, platform-dependent GUI toolkit, whereas Swing is a newer, entirely Java-based, platform-independent GUI toolkit.
2. **Weight Difference**: AWT components are "Heavyweight" (relying heavily on the host OS), while Swing components are "Lightweight" (drawn directly by Java).
3. **Pluggable Look & Feel**: AWT forcefully assumes the look of the OS it runs on. Swing allows developers to dynamically change the UI theme (e.g., Metal, Windows, Nimbus) without rewriting code.
4. **MVC Architecture**: AWT components bundle data and presentation together. Swing components strictly follow MVC, cleanly separating the internal data model from the visual rendering.
5. **Component Set**: AWT has a very limited set of basic controls. Swing includes everything AWT has, plus highly advanced controls like Trees, Tables, and TabbedPanes.
6. **AWT Component Notes**: 
   - **Button**: A simple clickable element triggering actions.
   - **Label**: Read-only text display element.
   - **TextField**: Single-line text input.
   - **Frame**: Top-level heavyweight window with a title bar.
   - **List**: Scrollable list of selectable items.
7. **Swing Component Notes**: 
   - **JButton**: Enhanced button supporting text, images, and HTML formatting.
   - **JLabel**: Enhanced label that can display icons alongside text.
   - **JTextField**: Enhanced input field supporting custom document models.
   - **JFrame**: Lightweight top-level container featuring a root pane and glass pane.
   - **JTable**: Highly complex grid component for displaying 2D database-like data.
8. **Double Buffering**: Swing utilizes built-in double buffering, drawing the UI to an off-screen image before pushing it to the screen, eliminating visual tearing and flickering. AWT does not.
9. **Transparency**: Swing components fully support transparency and alpha channels, allowing for highly modernized, overlaid UI designs. AWT components are strictly opaque rectangles.
10. **Borders**: Swing components feature an extensive `BorderFactory` allowing the easy addition of etched, beveled, or titled borders. AWT requires manual graphical drawing.
11. **Tooltip Support**: Swing has native, zero-configuration support for hover-over tooltips (`setToolTipText()`). AWT requires complex custom mouse listeners to simulate this behavior.
12. **Prefix Convention**: The universal distinction is nomenclature: All Swing classes start with 'J', whereas AWT classes do not.

```text
  [AWT Component] ---- OS Native Peer ----> Screen (Heavy)
  
  [Swing Component] -- Java 2D Graphics --> Screen (Light)
  
  Examples: Button vs JButton | Frame vs JFrame
```

```java
// Explanation: AWT vs Swing Code Comparison    //
import java.awt.*;                              // AWT Package
import javax.swing.*;                           // Swing Package
                                                //
public class ComponentComparison {              // Main class
    public static void main(String[] args) {    // Program entry
        // AWT Setup (Heavyweight)              //
        Frame aFrame = new Frame("AWT Window"); // AWT Frame
        aFrame.setLayout(new FlowLayout());     // Layout
        Button aBtn = new Button("AWT Button"); // AWT Button
        Label aLbl = new Label("AWT Label");    // AWT Label
        TextField aTxt = new TextField(10);     // AWT Text
        aFrame.add(aLbl); aFrame.add(aTxt); aFrame.add(aBtn);
        aFrame.setSize(200, 100); aFrame.setVisible(true);
                                                //
        // Swing Setup (Lightweight)            //
        JFrame sFrame = new JFrame("Swing Window");// Swing JFrame
        sFrame.setLayout(new FlowLayout());     // Layout
        // Swing supports icons!                //
        JButton sBtn = new JButton("Swing Button");// Swing JButton
        JLabel sLbl = new JLabel("Swing Label");// Swing JLabel
        JTextField sTxt = new JTextField(10);   // Swing JTextField
        sFrame.add(sLbl); sFrame.add(sTxt); sFrame.add(sBtn);
        sFrame.setSize(200, 100);               // Size
        sFrame.setLocation(220, 0);             // Offset location
        sFrame.setVisible(true);                // Display
    }                                           //
}                                               //
```

**Output:**
```
Two windows: one with native OS buttons (AWT),
one with Java-rendered buttons (Swing).
```

---

## Question 34: Write a Java program to implement a client-server communication system where the client sends a message and the server responds with an acknowledgment.

**Topic Introduction: Basic Client-Server Communication System.**

1. **Topic Introduction**: This outlines the fundamental architecture of networked communication in Java using the TCP/IP suite via standard blocking Sockets.
2. **Server Architecture**: The server application must run first. It creates a `ServerSocket` bound to a specific, unassigned port number (e.g., 5000) and waits.
3. **The `accept()` Method**: The server invokes `accept()`, completely halting its execution (blocking) until a remote client initiates a connection request.
4. **Client Architecture**: The client application creates a standard `Socket` object, explicitly specifying the server's IP address (e.g., "localhost") and the correct port number.
5. **The Handshake**: The OS handles the 3-way TCP handshake. Once complete, `accept()` unblocks on the server, returning a unique `Socket` object dedicated entirely to that specific client.
6. **I/O Streams Overview**: Every connected `Socket` provides two vital pipes: an `InputStream` (for reading incoming data) and an `OutputStream` (for sending outgoing data).
7. **Client Sending**: The client wraps its `OutputStream` inside a `DataOutputStream` and writes a UTF string message ("Hello Server!"). It must then call `flush()` to push data through the network.
8. **Server Receiving**: The server wraps its `InputStream` inside a `DataInputStream` and calls `readUTF()`. This method blocks until the client's message arrives across the network.
9. **Server Acknowledgment**: Immediately after reading, the server uses its own `DataOutputStream` to send a predefined String ("Message Received") back down the pipe.
10. **Client Receiving**: The client, which was waiting via its `DataInputStream.readUTF()`, receives the acknowledgment and prints it to the local console.
11. **Connection Termination**: Following the exchange, both the client and server must explicitly call `close()` on their respective socket objects to free OS network resources.
12. **Exception Handling**: All socket and stream operations throw `IOException`, making strict try-catch handling absolutely mandatory in Java networking code.

```text
  [SERVER] (ServerSocket on Port 5000)
     |
  accept() <--blocks-- [CLIENT] (Socket "localhost", 5000)
     |                    |
   [Socket] <== TCP ==> [Socket]
     |                    |
  readUTF() <---Msg---- writeUTF()
  writeUTF() ---Ack---> readUTF()
     |                    |
  close()               close()
```

```java
// Explanation: Simple Client-Server Comm       //
import java.io.*; import java.net.*;            // Network & IO imports
                                                //
// RUN SERVER CLASS FIRST!                      //
class Server {                                  // Server Application
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000); // Listen on port
        System.out.println("Waiting for client...");// Log status
        Socket s = ss.accept();                 // Block until connection
                                                //
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                                                //
        String msg = din.readUTF();             // Receive msg from client
        System.out.println("Client says: " + msg); // Print msg
                                                //
        dout.writeUTF("Server ACK: Message Received"); // Send Ack
        dout.flush();                           // Push data
        s.close(); ss.close();                  // Cleanup
    }                                           //
}                                               //
                                                //
class Client {                                  // Client Application
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000); // Connect to Server
                                                //
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        DataInputStream din = new DataInputStream(s.getInputStream());
                                                //
        dout.writeUTF("Hello Server!");         // Send Msg
        dout.flush();                           // Push data
                                                //
        String ack = din.readUTF();             // Receive Ack
        System.out.println(ack);                // Print Ack
        s.close();                              // Cleanup
    }                                           //
}                                               //
```

**Output (Server):** `Client says: Hello Server!`
**Output (Client):** `Server ACK: Message Received`

---

## Question 35: Create a Java program to implement a two-way communication between client and server using sockets.

**Topic Introduction: Two-Way Communication between Client and Server (Chat System).**

1. **Topic Introduction**: A two-way chat system expands on basic sockets by wrapping the Input/Output streams in `BufferedReader` and `PrintWriter` loops for continuous, bidirectional textual exchange.
2. **Setup Phase**: Similar to the basic model, the Server starts a `ServerSocket` and `accept()`s the connection. The Client connects via a standard `Socket`.
3. **The Three Streams**: Both applications require three specific streams: 1) Incoming network data, 2) Outgoing network data, and 3) Standard Input (`System.in`) to capture human typing.
4. **BufferedReader Context**: `BufferedReader` is superior to `DataInputStream` for chat systems because its `readLine()` method efficiently reads entire human-readable text strings until it hits a newline character.
5. **The Chat Loop**: Both programs enter an infinite `while(true)` loop. Inside this loop, execution halts, waiting for either user input or incoming network data.
6. **Client Flow**: The Client reads from the keyboard, sends the string over the network socket, and immediately blocks, waiting to read the Server's reply from the socket.
7. **Server Flow**: The Server blocks, waiting to read the string from the network socket. Upon receipt, it reads from the keyboard and sends its reply back.
8. **The "Over" Condition**: The loop includes a termination clause: if either party types "stop" or "over", the loop breaks instantly, initiating the shutdown sequence.
9. **Simultaneous Chat Limitation**: This synchronous 'Ping-Pong' model forces turns (Client talks, then Server talks). If the Client tries to send two messages instantly, it blocks until the Server replies.
10. **Multithreading Requirement**: To achieve true asynchronous chat (like WhatsApp), the read logic and write logic must be separated into two completely distinct Java Threads.
11. **Network Flushing**: When writing to the network stream using `PrintWriter`, auto-flush must be enabled (`new PrintWriter(out, true)`), or the message will sit indefinitely in the local memory buffer.
12. **Resource Exhaustion Prevention**: Once the loop breaks, explicitly closing all streams (`in.close()`, `out.close()`) before closing the actual socket prevents port exhaustion.

```text
  [CLIENT]                       [SERVER]
  (Keyboard)                     (Keyboard)
      |                              |
      v                              v
  write(msg) ----> Network ----> readLine()
      |                              |
  readLine() <---- Network <---- write(msg)
```

```java
// Explanation: Two-Way Chat Application        //
import java.io.*; import java.net.*;            // Imports
                                                //
// RUN SERVER CLASS FIRST!                      //
class ChatServer {                              // Server App
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000); // Port setup
        Socket s = ss.accept();                 // Accept connection
                                                //
        BufferedReader netIn = new BufferedReader( // Net Input
            new InputStreamReader(s.getInputStream()));
        PrintWriter netOut = new PrintWriter(   // Net Output (AutoFlush)
            s.getOutputStream(), true);
        BufferedReader kb = new BufferedReader( // Keyboard Input
            new InputStreamReader(System.in));
                                                //
        String str = "", str2 = "";             // Message buffers
        while(!str.equals("stop")) {            // Chat Loop
            str = netIn.readLine();             // Wait for Client Msg
            System.out.println("Client: " + str); // Print it
                                                //
            str2 = kb.readLine();               // Wait for Keyboard
            netOut.println(str2);               // Send to Client
        }                                       //
        s.close(); ss.close();                  // Cleanup
    }                                           //
}                                               //
                                                //
class ChatClient {                              // Client App
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000); // Connect
                                                //
        BufferedReader netIn = new BufferedReader( // Net Input
            new InputStreamReader(s.getInputStream()));
        PrintWriter netOut = new PrintWriter(   // Net Output
            s.getOutputStream(), true);
        BufferedReader kb = new BufferedReader( // Keyboard Input
            new InputStreamReader(System.in));
                                                //
        String str = "", str2 = "";             // Message buffers
        while(!str.equals("stop")) {            // Chat Loop
            str = kb.readLine();                // Wait for Keyboard
            netOut.println(str);                // Send to Server
                                                //
            str2 = netIn.readLine();            // Wait for Server Msg
            System.out.println("Server: " + str2);// Print it
        }                                       //
        s.close();                              // Cleanup
    }                                           //
}                                               //
```

**Output (Server):** `Client: Hello` → types reply
**Output (Client):** Types "Hello" → sees `Server: Hi back`
