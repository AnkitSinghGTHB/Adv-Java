# Module 5: Networking, Applets, Servlets, and EJB

This module explores distributed Java programming, transitioning from client-based applets to robust, server-hosted distributed object architectures.

---

## 1. Java Applets

An Applet is a special type of Java program designed to be transmitted over the internet and executed automatically inside a Java-compatible web browser or an applet viewer. 
> *Note: Applets are largely deprecated in modern engineering but remain core to understanding JVM sandboxing.*

### 📌 Activity Question: Applet Lifecycle
**Question:** Explain the lifecycle of a Java Applet in detail with its five primary methods.

**Solution/Explanation:**
The execution flow of an Applet is dictated by five built-in methods provided by `java.applet.Applet`:
1. **`init()`**: Called exactly once when the applet is first loaded into memory. Used for variable initialization and GUI setup.
2. **`start()`**: Called immediately after `init()`. Also triggered automatically every time the user maximizes the browser window or returns to the HTML page. 
3. **`paint(Graphics g)`**: Responsible for rendering actual graphics, text, or shapes. Fired continuously to repaint the applet screen.
4. **`stop()`**: Automatically executed when the user leaves the HTML page or minimizes the browser, inherently pausing thread executions.
5. **`destroy()`**: Called exactly once right before the browser closes to permanently terminate the applet state and release system memory.

---

## 2. Servlets

Servlets are server-side Java programs executing inside web containers (like Apache Tomcat). They process incoming HTTP requests and generate dynamic web content (HTML/JSON) dynamically.

### 📌 Activity Question: Applets vs Servlets
**Question:** Differentiate between Java Applets and Servlets. Compare their execution environment, security restrictions, performance overhead, and practical use cases in modern computing.

**Solution/Explanation:**
- **Execution Environment**: Applets execute entirely on the **Client's Machine** (browser). Servlets execute entirely on the **Web Server**.
- **Security**: Applets operate inside a strict sandbox to prevent them from reading/writing files on the client's local disk. Servlets have broad file-system capabilities acting on the server.
- **Performance**: Applets demand heavy client-side bandwidth to download the JVM bytecode initially. Servlets generate HTML rapidly sending only lightweight text blocks back to the client.
- **Use Case**: Applets were used for legacy browser-side games/visualizations. Servlets are the backbone of modern REST APIs and enterprise web logic.

---

## 3. Java Networking

Networking enables sharing resources remotely across devices. `java.net` package provides connection-oriented (TCP) and connection-less (UDP/Datagram) communications.
- **InetAddress**: Represents an IP Address (e.g., `InetAddress.getLocalHost();`).
- **URL**: Uniform Resource Locator pointing to web resources explicitly.

### 📌 Activity Question: Application utilizing TCP Sockets
**Question:** Design a simple application using Sockets. Explain the `ServerSocket` and `Socket` classes clearly.

**Solution/Explanation:**
- **`ServerSocket`**: Opened actively on the Server machine. It continuously listens to a specific Port Number waiting for incoming client connection requests.
- **`Socket`**: Opened actively on the Client machine. It connects to the exact IP and Port Number exposed by the ServerSocket. Once connected, they yield `InputStream` and `OutputStream` constructs for bidirectional data flow.

```java
// Server Side Implementation
import java.io.*;
import java.net.*;

public class ServerDemo {
    public static void main(String[] args) {
        try {
            // Step 1: Open listening port 6666
            ServerSocket ss = new ServerSocket(6666);
            System.out.println("Waiting for client...");
            
            // Step 2: Accept incoming connection
            Socket s = ss.accept();
            System.out.println("Client connected!");

            // Step 3: Read data sent purely from the client
            DataInputStream din = new DataInputStream(s.getInputStream());
            String str = (String) din.readUTF();
            System.out.println("Message from Client: " + str);
            
            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

```java
// Client Side Implementation
import java.io.*;
import java.net.*;

public class ClientDemo {
    public static void main(String[] args) {
        try {
            // Step 1: Connect to localhost (127.0.0.1) on port 6666
            Socket s = new Socket("localhost", 6666);
            
            // Step 2: Write logic down the OutputStream
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("Hello Server, from Client!");
            dout.flush();
            
            // Step 3: Flush and close
            dout.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
**Expected Terminal Output:**
*Server Terminal:*
```text
Waiting for client...
Client connected!
Message from Client: Hello Server, from Client!
```

---

## 4. Remote Method Invocation (RMI)

RMI allows a Java object executing in one JVM to invoke methods on an object running in a completely different JVM across a network. It heavily relies on internal TCP/IP configurations.

### 📌 Activity Question: RMI Architecture
**Question:** Explain the RMI Architecture, emphasizing Stubs, Skeletons, and the RMI Registry.

**Solution/Explanation:**
1. **Stub (Client Proxy)**: An object acting as a gateway residing identically on the client side. The client invokes methods on the stub locally. The stub actively bundles the parameters (Marshaling) and transmits them across the network.
2. **Skeleton (Server Proxy)**: Resides on the server side. It receives the network payload, unbundles the parameters (Unmarshaling), mapping them finally to the real operational Server Object method. It re-bundles the returned answer to pass back to the stub.
3. **RMI Registry**: A localized directory service (like yellow-pages) hosted identically on the server. The server registers its exported objects into the Registry using a unique string name. Clients lookup this string in the Registry to download the Stub reference.

---

## 5. Enterprise JavaBeans (EJB)

EJB is an architecture used explicitly for creating highly scalable, transactional, and multi-user enterprise applications. 

### 📌 Activity Question: Stateful vs Stateless Beans
**Question:** Create a basic understanding of stateless and stateful session beans in EJB.

**Solution/Explanation:**
- **Stateless Session Bean**: Does not maintain any conversational state regarding the specific client making the HTTP request. Every method invocation is treated as entirely new. 
    - *Example*: A Currency Conversion bean. Converting dollars to euros does not inherently change mathematically based on who the user is. Highly memory-efficient and inherently pooled.
- **Stateful Session Bean**: Maintains a conversational state specifically attached to a client. 
    - *Example*: An E-commerce Shopping Cart bean. As the user navigates across different screens clicking "add to cart", the underlying container securely tracks those exact items for that exact client till the checkout ends. Consumes extremely vast memory overhead.
