# Advanced Java Solutions - Part 8 (Q36 to Q40)

---

## Question 36: Develop a Java socket program where the client sends a number to the server and the server returns its square or factorial.

**Topic Introduction: Socket Program for Math Operations (Square/Factorial).**

1. **Topic Introduction**: This application demonstrates a practical Remote Procedure Call (RPC) concept where a client offloads heavy mathematical processing to a dedicated server.
2. **Server Capability**: The server is designed to parse an incoming integer, calculate its square (or factorial), and return the result as a formatted string.
3. **Data Streams**: Because we are transmitting raw integers and structured text, `DataInputStream` and `DataOutputStream` are the optimal choices for network I/O.
4. **Client Request**: The client prompts the user via Scanner to enter an integer, writes it to the output stream using `writeInt()`, and flushes the buffer.
5. **Server Processing**: The server blocks on `readInt()`. Once received, it executes a simple mathematical operation (e.g., `result = number * number`).
6. **Server Response**: The server writes the calculated result back to the client using `writeUTF()`, ensuring cross-platform text compatibility.
7. **Client Reception**: The client, previously blocked on `readUTF()`, receives the mathematical result and displays it to the local user.
8. **Stateless Nature**: This specific implementation is stateless; the server handles one request, provides one response, and immediately closes the connection.
9. **Extensibility**: To support both Square and Factorial simultaneously, the client could send a String payload like "SQUARE:5" or "FACT:5", which the server parses before calculating.
10. **Data Integrity**: TCP guarantees that the bytes representing the integer arrive exactly as sent, preventing silent mathematical corruption during transmission.
11. **Exception Boundaries**: If the client disconnects prematurely before the server writes the response, an `IOException` (Broken Pipe) is safely caught and handled.
12. **Real-world Parallels**: This fundamentally mimics how modern microservices operate, sending parameters to an API endpoint and awaiting a computed JSON response.

```text
  [CLIENT]                   [SERVER]
  Input: 5
  writeInt(5) ----(TCP)----> readInt()
                               |
                        [Calc: 5 * 5 = 25]
                               |
  readUTF()  <----(TCP)----- writeUTF("25")
  Output: 25
```

```java
// Explanation: Math Server and Client          //
import java.io.*; import java.net.*;            // Imports
                                                //
class MathServer {                              // Server Class
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(4000); // Port 4000
        Socket s = ss.accept();                 // Await Client
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
                                                //
        int num = in.readInt();                 // Read Number
        int square = num * num;                 // Compute Square
                                                //
        out.writeUTF("Square is: " + square);   // Send Result
        out.flush();                            // Push data
        s.close(); ss.close();                  // Cleanup
    }                                           //
}                                               //
                                                //
class MathClient {                              // Client Class
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 4000); // Connect Server
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        DataInputStream in = new DataInputStream(s.getInputStream());
                                                //
        int numberToSend = 5;                   // Mocking user input
        out.writeInt(numberToSend);             // Send Number
        out.flush();                            // Push data
                                                //
        String result = in.readUTF();           // Wait for Result
        System.out.println("Server: " + result);// Print Result
        s.close();                              // Cleanup
    }                                           //
}                                               //
```

**Output (Server):** `Received: 5 -> Square = 25`
**Output (Client):** `Server: Square is: 25`

---

## Question 37: Develop a Java socket program where multiple clients can connect to a server and exchange messages.

**Topic Introduction: Multi-Client Socket Server Architecture.**

1. **Topic Introduction**: A standard `ServerSocket` can only talk to one client at a time. To support a chat room with multiple clients, we must introduce Multithreading.
2. **The Bottleneck**: Calling `accept()` blocks the main thread. If a client connects, the server handles them, but ignores all other connection attempts until the first client leaves.
3. **The Threaded Solution**: The Server's main thread runs an infinite `while(true)` loop calling `accept()`. As soon as a client connects, it immediately hands that `Socket` to a brand-new Thread.
4. **ClientHandler Class**: We create a `ClientHandler` class that implements `Runnable`. Its constructor takes the client's `Socket` as a parameter.
5. **Main Thread Freed**: After starting the thread, the Server's main loop instantly loops back to `accept()`, ready for the next incoming connection without delay.
6. **Thread Execution**: Each `ClientHandler` thread has its own isolated `run()` method containing the `read/write` loop dedicated solely to its assigned client.
7. **Broadcasting Concept**: To exchange messages *between* clients, the Server must maintain a global `ArrayList<PrintWriter>` containing the output streams of every connected client.
8. **Broadcasting Execution**: When one `ClientHandler` reads a message from its client, it iterates through the global `ArrayList` and writes that message to everyone else's stream.
9. **Concurrency Control**: Because multiple threads might try to add/remove streams or broadcast simultaneously, the global `ArrayList` must be wrapped in `synchronized` blocks.
10. **Client Simplicity**: Interestingly, the Client code remains completely unaware of this complexity. It just connects to the port and reads/writes normally.
11. **Scalability Limits**: Creating a new OS thread per client works for hundreds of users, but enterprise servers (like Netty) use Java NIO (Non-blocking I/O) to handle millions.
12. **Real-world Use**: This is the fundamental architecture powering multiplayer game servers, IRC chat rooms, and collaborative document editing tools.

```text
  [Main Thread: ServerSocket.accept()]
      | (creates & starts new thread)
      +---> [Thread 1: ClientHandler] <==> [Client A]
      |
      +---> [Thread 2: ClientHandler] <==> [Client B]
      |
      +---> [Thread 3: ClientHandler] <==> [Client C]
```

```java
// Explanation: Multi-Client Server Logic       //
import java.io.*; import java.net.*;            // Imports
                                                //
public class MultiServer {                      // Main Server
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000); // Port setup
        System.out.println("Server Started...");// Status
        while (true) {                          // Infinite accept loop
            Socket s = ss.accept();             // Wait for Client
            System.out.println("Client joined!"); // Log connection
            new Thread(new Handler(s)).start(); // Hand off to Thread
        }                                       //
    }                                           //
}                                               //
                                                //
class Handler implements Runnable {             // Thread Logic
    private Socket socket;                      // Local socket
    public Handler(Socket s) { socket = s; }    // Constructor
                                                //
    public void run() {                         // Thread entry point
        try {                                   //
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Welcome to the Server!"); // Greet client
                                                //
            String msg;                         // Message buffer
            while ((msg = in.readLine()) != null) { // Read loop
                System.out.println("Rcv: " + msg); // Process/Broadcast here
            }                                   //
        } catch (IOException e) {               // Handle disconnect
            System.out.println("Client left."); // Log disconnect
        }                                       //
    }                                           //
}                                               //
```

**Output (Server):**
```
Server Started...
Client joined!
Client joined!
Rcv: Hello from Client1
```

---

## Question 38: Write a Java program to demonstrate TCP-based client-server communication using sockets and explain its working.

**Brief Explanation:**

- TCP is connection-oriented and reliable — guarantees data delivery and ordering.
- Java's `Socket` and `ServerSocket` abstract the TCP handshake, retransmission, and packet ordering.
- Server listens on a port, client connects, both exchange data via Input/Output streams.

```text
  [TCP Communication Flow]
  CLIENT                         SERVER
    |                               |
    +--- SYN (Connect) ----------->|  (3-way handshake)
    |<-- SYN-ACK ----------------  |
    +--- ACK -------------------->  |
    |                               |
    +--- writeUTF("Hello") ------->|  (Reliable delivery)
    |<-- writeUTF("ACK") ---------  |
    |                               |
    +--- FIN (Close) ------------->|  (Graceful teardown)
```

```java
// Explanation: TCP Server-Client Demo          //
import java.io.*; import java.net.*;            // Network & IO imports
                                                //
// ===== RUN THIS CLASS FIRST =====             //
class TCPServer {                               // Server Application
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(6000);// Listen on port 6000
        System.out.println("Server waiting...");// Status log
        Socket s = ss.accept();                 // Block until client connects
        System.out.println("Client connected!");// Connection confirmed
                                                //
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                                                //
        String msg = din.readUTF();             // Read client's message
        System.out.println("Received: " + msg); // Print it
                                                //
        dout.writeUTF("Server ACK: " + msg);    // Send acknowledgment
        dout.flush();                           // Push data over network
                                                //
        s.close(); ss.close();                  // Release resources
        System.out.println("Server closed.");   // Status log
    }                                           //
}                                               //
                                                //
// ===== RUN THIS CLASS SECOND =====            //
class TCPClient {                               // Client Application
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 6000);// Connect to server
        System.out.println("Connected to server!");// Status log
                                                //
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        DataInputStream din = new DataInputStream(s.getInputStream());
                                                //
        dout.writeUTF("Hello via TCP!");        // Send message
        dout.flush();                           // Push data
        System.out.println("Sent: Hello via TCP!");// Log
                                                //
        String reply = din.readUTF();           // Read server's reply
        System.out.println("Reply: " + reply);  // Print reply
                                                //
        s.close();                              // Release resources
        System.out.println("Client closed.");   // Status log
    }                                           //
}                                               //
```

**Output (Server Console):**
```
Server waiting...
Client connected!
Received: Hello via TCP!
Server closed.
```

**Output (Client Console):**
```
Connected to server!
Sent: Hello via TCP!
Reply: Server ACK: Hello via TCP!
Client closed.
```

---

## Question 39: Write a Java Applet to draw different geometric shapes such as square, ellipse, line, and polygon, and display a message "Geometric Figures". Use appropriate coordinates. Also write the HTML code to execute the applet.

**Topic Introduction: Java Applet for Geometric Figures.**

1. **Topic Introduction**: An Applet is a special type of Java program designed to be embedded within HTML web pages and run seamlessly inside a client's web browser.
2. **Applet Lifecycle**: Applets do not use a `main()` method. Instead, the browser controls their execution via `init()`, `start()`, `paint()`, `stop()`, and `destroy()`.
3. **The `paint()` Method**: All visual rendering (drawing shapes, text) must occur exclusively inside the overridden `paint(Graphics g)` method.
4. **The Graphics Object**: The `Graphics` object (`g`) passed to `paint()` acts as the virtual paintbrush and canvas, providing dozens of drawing methods.
5. **Drawing a Square**: A square is drawn using `g.drawRect(x, y, width, height)`. Since it is a square, the width and height parameters must be identical.
6. **Drawing an Ellipse**: An ellipse is drawn using `g.drawOval(x, y, width, height)`. A circle is just an ellipse with identical width and height.
7. **Drawing a Line**: A straight line requires starting and ending coordinates: `g.drawLine(x1, y1, x2, y2)`.
8. **Drawing a Polygon**: A polygon requires arrays of X and Y coordinates representing the vertices, and the total number of points: `g.drawPolygon(xArray, yArray, points)`.
9. **Drawing Text**: Text is drawn onto the canvas using `g.drawString("Text", x, y)`.
10. **Coordinate System**: The Java 2D coordinate system starts with (0,0) at the top-left corner of the applet window. X increases to the right, Y increases downwards.
11. **HTML Integration**: To run the compiled `.class` file, it must be referenced by an `<applet>` tag within an HTML file specifying the desired width and height.
12. **Deprecation Note**: Due to severe security vulnerabilities, major browsers permanently removed support for Java Applets years ago, making this a strictly academic exercise.

```text
  [Applet Window (0,0 at Top Left)]
  (x:50, y:50)
    +-------+ (Square)      * (Line)
    |       |                \
    +-------+                 *
          _..._ (Ellipse)      /\
        /       \             /  \ (Polygon)
        \_....._/            /____\
```

```java
// Explanation: Applet Drawing Code             //
import java.applet.Applet;                      // Import Applet
import java.awt.Graphics;                       // Import Graphics
                                                //
/* HTML Code required to run:                   // Embedded HTML
<applet code="ShapesApplet.class" width="400" height="300">
</applet>                                       //
*/                                              //
                                                //
public class ShapesApplet extends Applet {      // Extend Applet class
    public void paint(Graphics g) {             // Override paint
        // 1. Draw Message                      //
        g.drawString("Geometric Figures", 150, 20);
                                                //
        // 2. Draw Square                       //
        g.drawRect(50, 50, 80, 80);             // X, Y, W, H
                                                //
        // 3. Draw Ellipse                      //
        g.drawOval(200, 50, 100, 60);           // X, Y, W, H
                                                //
        // 4. Draw Line                         //
        g.drawLine(50, 200, 150, 250);          // X1, Y1, X2, Y2
                                                //
        // 5. Draw Polygon (Triangle here)      //
        int[] xPoints = {250, 300, 200};        // X Coordinates
        int[] yPoints = {180, 250, 250};        // Y Coordinates
        g.drawPolygon(xPoints, yPoints, 3);     // Arrays, Node Count
    }                                           //
}                                               //
```

**Output (in Applet Viewer):**
```
Draws: "Geometric Figures" text, a square,
an ellipse, a diagonal line, and a triangle.
```

---

## Question 40: Develop a Java Applet that draws circle, rectangle, triangle, and arc with different colors and displays a string "Drawing Shapes". Provide suitable coordinates and include the HTML code to run the applet.

**Topic Introduction: Colored Shapes Java Applet.**

1. **Topic Introduction**: This Applet extends basic drawing capabilities by incorporating dynamic color management and specialized rendering methods.
2. **Color Management**: The `Graphics` object maintains a current active color. Anything drawn after calling `g.setColor(Color.X)` will be rendered in that specific color.
3. **Color Constants**: The `Color` class provides predefined constants like `Color.RED`, `Color.BLUE`, and `Color.GREEN` for convenience.
4. **Custom Colors**: Developers can mix custom RGB colors using `new Color(red, green, blue)` where values range from 0 to 255.
5. **Drawing a Circle**: A circle is generated using `drawOval(x, y, diameter, diameter)`. Both width and height are equal.
6. **Drawing an Arc**: An arc is a segment of an oval. `drawArc(x, y, w, h, startAngle, arcAngle)` defines the bounding box, starting angle (0 is 3 o'clock), and degrees to sweep.
7. **Filled Shapes**: Instead of just drawing outlines (`drawRect`), Applets can draw solid shapes using methods like `fillRect`, `fillOval`, and `fillArc`.
8. **Drawing a Triangle**: Without a `drawTriangle` method, developers must use `drawPolygon()` with exactly 3 coordinate pairs.
9. **Drawing a Rectangle**: A standard rectangle is drawn using `drawRect(x, y, width, height)`.
10. **State Machine**: The `Graphics` context is a state machine. Changing the color to Blue to draw the circle means the subsequent rectangle will also be Blue unless the color is explicitly changed again.
11. **Text Formatting**: We draw the requested string "Drawing Shapes" at the top of the canvas, ensuring the color is set to something highly visible like Black.
12. **Execution**: The Applet viewer utility (`appletviewer index.html`) was traditionally used by developers to test Applets locally without needing a full web browser.

```text
  [Applet Screen (Color Demo)]
   RED String: "Drawing Shapes"
   
   [BLUE Circle]         [GREEN Rectangle]
   
   [ORANGE Triangle]     [MAGENTA Arc]
```

```java
// Explanation: Colored Shapes Applet           //
import java.applet.Applet;                      // Applet import
import java.awt.*;                              // Graphics & Color
                                                //
/* HTML Code required to run:                   // Embedded HTML
<applet code="ColorShapes.class" width="400" height="300">
</applet>                                       //
*/                                              //
                                                //
public class ColorShapes extends Applet {       // Class declaration
    public void paint(Graphics g) {             // Rendering method
        // 1. Draw String (Black default)       //
        g.drawString("Drawing Shapes", 150, 20);// Text render
                                                //
        // 2. Draw Blue Circle                  //
        g.setColor(Color.BLUE);                 // Change state
        g.drawOval(50, 50, 80, 80);             // Circle (W=H)
                                                //
        // 3. Draw Green Rectangle              //
        g.setColor(Color.GREEN);                // Change state
        g.drawRect(200, 50, 100, 60);           // Rectangle
                                                //
        // 4. Draw Orange Triangle (Polygon)    //
        g.setColor(Color.ORANGE);               // Change state
        int[] x = {100, 140, 60};               // Triangle X
        int[] y = {150, 220, 220};              // Triangle Y
        g.drawPolygon(x, y, 3);                 // Render poly
                                                //
        // 5. Draw Magenta Arc                  //
        g.setColor(Color.MAGENTA);              // Change state
        g.drawArc(200, 150, 80, 80, 0, 180);    // Sweeps 180 deg
    }                                           //
}                                               //
```

**Output (in Applet Viewer):**
```
Draws: "Drawing Shapes" text in black,
blue circle, green rectangle, orange triangle, magenta arc.
```
