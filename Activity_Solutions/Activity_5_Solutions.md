# Activity 5: Applets, Networking, RMI, Servlets & EJB

---

### **1. Explain the complete life cycle of an Applet with a suitable diagram and example. Also outline how it interacts with HTML.**

**Answer:**
**An Applet** is a special Java program executed safely inside a web browser. The Applet's lifecycle is managed entirely by the browser (or AppletViewer), not by a `main()` method.

**Lifecycle Diagram:**
```text
  [ Browser Loads Applet ]
           |
       1. init()     <--- Called exactly ONCE (Initialization)
           |
       2. start()    <--- Called whenever returning to page (Execution begins)
           |
           v
  ( Applet is Running ) --> 3. paint(Graphics g) <-- Called repeatedly to draw UI
           |
       4. stop()     <--- Called when leaving the page or minimizing
           |
     5. destroy()    <--- Called exactly ONCE when browser is closed (Memory cleanup)
```

**HTML Interaction:**
Applets cannot run on their own; they must be embedded in an `.html` file using the `<applet>` tag, which the browser reads.
```html
<applet code="MyApplet.class" width="300" height="300">
    <!-- Passing parameters from HTML to Java -->
    <param name="username" value="student1">
</applet>
```

---

### **2 & 13. Describe socket programming in Java with client-server architecture. Explain how socket programming can be used.**

**Answer:**
**Socket Programming** handles low-level, byte-stream network communication between two individual machines (a Client and a Server) over TCP/IP.

**Architecture & How it is Used:**
1.  **Server Machine:** Opens a **`ServerSocket`** on a specific `PORT` (e.g., 8080) and calls `accept()`. The server halts execution here, legally waiting until a client knocks on the door.
2.  **Client Machine:** Opens a standard **`Socket`**, providing the Server's IP address and Port.
3.  **The Handshake:** When the client connects, the Server's `accept()` method wakes up and returns a dedicated `Socket` specifically for that client.
4.  **Communication:** Both machines generate `InputStream` (to read data) and `OutputStream` (to send data). 

*   *Use Case:* Building a live chat application (WhatsApp backend), multiplayer game servers, or IoT device data streams.

---

### **3. Explain Remote Method Invocation (RMI) in detail. Describe architecture, components, and working process.**

**Answer:**
**RMI (Remote Method Invocation)** is a Java-specific API allowing a Java program running on one JVM (Client) to invoke methods on an object residing in a *completely different* JVM (Server) as if it were a local object.

**The Architecture & Working Process:**
1.  **The Interface:** Both Client and Server share a `.java` interface extending `java.rmi.Remote`.
2.  **The Stub (Client-Side Proxy):** A system-generated class. When the client calls `calculateTax()`, it actually talks to the Stub. The Stub packs (marshals) the request and sends it over the network.
3.  **The Skeleton (Server-Side Proxy):** Receives the network packet from the Stub, unpacks (unmarshals) it, and calls the *actual* Server implementation object.
4.  **RMI Registry:** A directory service running on the server. The Server registers its object (e.g., "TaxCalculator") with the registry. The Client asks the registry "Where is TaxCalculator?" to obtain the Stub.

---

### **4. Explain the architecture of web applications using Servlets. Discuss the role of the Servlet container and request-response model.**

**Answer:**
**Servlets** are Java programs running on a centralized web server used to generate dynamic web pages (HTML) in response to HTTP requests.

**The Request-Response Model:**
1.  **Request:** A user fills a web form and clicks submit. The browser sends an `HttpServletRequest` (containing form data) to the Server.
2.  **Container:** The **Servlet Container** (e.g., Apache Tomcat) intercepts this HTTP request.
3.  **Processing:** Tomcat finds the correct Servlet, passes the request to `doGet()` or `doPost()`. The Servlet interacts with databases or logic.
4.  **Response:** The Servlet constructs HTML dynamically and writes it into the `HttpServletResponse` object, which Tomcat sends back to the user's browser.

**Role of the Servlet Container:**
The container handles all the heavy lifting: network listening, multithreading (creating a new thread for every user request), memory management, and security, allowing the developer to focus purely on business logic.

---

### **5 & 12. Differentiate between Applets and Servlets (Execution, Security, Performance). Explain why Servlets are preferred dynamically.**

**Answer:**

| Feature | Applet | Servlet |
| :--- | :--- | :--- |
| **Execution Location** | Runs strictly on the **Client's Machine** (Browser). | Runs strictly on the **Server Machine** (Tomcat). |
| **Security** | Highly restricted ("Sandbox"). Cannot read/write local client files safely. | High permissions. Full access to server-side databases and file systems. |
| **Performance** | **Poor UI startup**. Requires downloading the `.class` file to the client and booting a heavy JVM plugin in the browser. | **Extremely Fast**. The client just receives a lightweight HTML string. Heavy lifting is done by powerful server hardware. |
| **Why Servlets are Preferred** | Applets are dead. Modern browsers disabled Java Plugins for security. Servlets send pure, standard HTML/CSS, meaning they work flawlessly on PCs, iPhones, and Androids without plugins. |

---

### **6 & 7. Describe Enterprise JavaBeans (EJB) basics: Session Beans, Lifecycle, Use Cases, and Security Mechanisms.**

**Answer:**
**EJB (Enterprise JavaBeans)** is an architecture for setting up highly secure, distributed, transactional business logic on a centralized application server.

**Session Beans (The Business Logic):**
*   **Stateless Session Bean:** Does *not* remember the user between method calls. Easiest to scale.
    *   *Use Case:* A "Currency Converter" bean. User asks to convert $10 to Euros. The bean calculates and returns the result, immediately forgetting the user exists.
*   **Stateful Session Bean:** Remembers business state regarding a specific user across multiple method calls.
    *   *Use Case:* An "E-Commerce Shopping Cart". The bean remembers you added a Shirt to `cart` on Monday, and a Hat on Tuesday.

**EJB Security Mechanisms:**
EJBs handle security declaratively via annotations, completely separating security logic from business logic.
*   **Authentication:** The Web Server verifies *who* you are (Login/Password).
*   **Authorization:** The EJB Container verifies *what you can do* using **Roles**.
    *   *Example:* `@RolesAllowed("ADMIN") public void deleteDatabase() {...}`. If a "GUEST" tries to call this method, the EJB container intercepts the call and blocks it throwing a Security Exception.

---

### **8 & 9. Evaluate modern Servlets and how EJB simplifies enterprise systems.**

**Answer:**
*   **Role of Servlets:** Servlets remain the foundational bedrock of Java Web Development. Even modern, high-level frameworks like Spring Boot or JSF are ultimately just wrappers abstractions built on top of the underlying Servlet API. 
*   **How EJB Simplifies Development:** Without EJB, the developer must manually write code for Database Transactions (`con.commit() / con.rollback()`), Thread Pooling, and Security rules. EJB utilizes **Inversion of Control (IoC)**—the developer writes pure business logic, and the heavy Oracle WebLogic / JBoss server injects the security and transactions automatically via annotations.

---

### **10 & 11. What are the limitations of socket programming compared to RMI? Discuss advantages and challenges of RMI.**

**Answer:**
**Limitations of Sockets vs RMI:**
Sockets are "low-level". If you send an object via a Socket, you must manually serialize it to bytes, design a custom protocol (e.g., "Command=Calculate,Value=10"), and the server must manually parse those bytes. In **RMI**, you simply call `server.calculate(10)`—the RMI protocol handles all the byte-parsing natively.

**RMI Advantages:**
*   Object-Oriented network communication.
*   Abstracts away network IP/Socket complexity.

**RMI Challenges:**
*   **Language Lock-in:** Both the Client and Server *must* be written in Java. (In comparison, REST APIs allow a Java server to talk to a Python client).
*   **Firewall Issues:** RMI negotiates custom random ports at runtime, making it a nightmare to configure through strict corporate firewalls.

---

### **14. Describe the HTML tags and attributes used to embed an applet.**

**Answer:**
*EXAMINER NOTE: The `<applet>` tag is historically important but was officially deprecated in HTML5.*

**Basic Syntax:**
```html
<applet code="FinancialCalculator.class" width="500" height="400">
    <param name="interestRate" value="7.5">
</applet>
```
**Attributes Explained:**
*   `code`: Defines the exact `.class` filename of the compiled Java Applet to download and run.
*   `width` & `height`: Reserves the physical pixel space in the browser document for the Applet UI.
*   `<param>`: Used to inject configuration variables into the Java code dynamically without hardcoding them in the Java source.
