# Module 13: RMI and EJB (Simplified)

## 1. What is RMI? (Remote Method Invocation)

**Simple Explanation (Universal Analogy):**
Normally, if you want a wooden chair built, you build it yourself in your own local workshop. 
But what if you need a huge metal bridge section built? **RMI** lets you pick up your phone, dial a giant distant steel factory (the Remote Server), strictly give them the measurements, and they do all the heavy building for you and deliver the final finished bridge section back to you!
*It invokes (calls) a method remotely (on another powerful computer) safely over the internet!*

---

## 2. Architecture of RMI (The Stub & Skeleton Model)

How does your local computer safely pack the exact data and send it 500 miles away securely? It basically uses two protective bodyguards:

1.  **Stub (Client Side):** Sits strictly on your local user computer. When you call `add()`, the Stub packs your raw numbers into a unified network package (formally called *Marshalling*) and sends it over the internet.
2.  **Skeleton (Server Side):** Sits on the remote server computer. It smoothly catches the package, unpacks the numbers (formally called *Unmarshalling*), carefully gives it to the real Java logic, gets the pure result, and organically sends it back precisely the identical way.

**Concept Diagram: RMI Flow**

```text
 [Client Program]
       |
     (Calls Method)
       V
  [Stub Proxy] (Packs Data / Marshals)
       |
  (Internet / Network)
       |
  [Skeleton Proxy] (Unpacks Data / Unmarshals)
       |
     (Executes Code)
       V
 [Server Real Object]
```

---

## 3. The 6 Steps to Write an RMI Program (12 Mark Question)

You MUST memorize these exact 6 basic steps for university exams!

1.  **Create Interface:** Extend `java.rmi.Remote` and throw `java.rmi.RemoteException`.
2.  **Provide Implementation:** Real logic extends `UnicastRemoteObject`.
3.  **Compile with `rmic` (Crucial Step):** In the terminal, run the command `rmic YourClassName`. This instantly generates the magic `Stub` and `Skeleton` files automatically for you!
4.  **Start RMI Registry:** Actively run `start rmiregistry` inside the terminal to start the naming directory service.
5.  **Create Server:** Server binds itself using `Naming.bind()`.
6.  **Create Client:** Client looks for the server using `Naming.lookup()`.

**Exam Code Snippet (10-12 Mark Guarantee):**
```java
import java.rmi.*;
import java.rmi.server.*;

// 1. Create the Interface
public interface Adder extends Remote {
    public int add(int x, int y) throws RemoteException;
}

// 2. Provide Implementation
public class AdderImpl extends UnicastRemoteObject implements Adder {
    public AdderImpl() throws RemoteException { super(); }
    public int add(int x, int y) { return x + y; }
}

// 5. Create Server
public class MyServer {
    public static void main(String args[]) {
        try {
            Naming.rebind("rmi://localhost:5000/MyAdder", new AdderImpl());
            System.out.println("Server is Ready!");
        } catch(Exception e) { System.out.println(e); }
    }
}

// 6. Create Client
public class MyClient {
    public static void main(String args[]) {
        try {
            // Ask the registry for the remote object proxy
            Adder skeletonProxy = (Adder) Naming.lookup("rmi://localhost:5000/MyAdder");
            
            // Execute remotely!
            System.out.println("Result is: " + skeletonProxy.add(10, 5));
        } catch(Exception e) { System.out.println(e); }
    }
}
```

---

## 4. What is EJB? (Enterprise JavaBeans)

**Simple Explanation:**
RMI is genuinely great, but what if 10,000 workers try to distinctly use the factory at the exact same minute? RMI will crash, it has zero strict security doors, and it cannot handle tracking databases easily.

**EJB (Enterprise JavaBeans)** is the heavily-upgraded enterprise version built tightly on top of RMI. It automatically handles:
*   Security (Passwords/Logins validation smoothly)
*   Database saving (Persistence accurately)
*   Scaling (Multithreading traffic perfectly).

### Types of EJB:
1.  **Stateless Session Bean:** Fast, uniquely forgets who you are after the transaction ends.
2.  **Stateful Session Bean:** Slower, remembers you fully across multiple clicks.
3.  **Message-Driven Bean:** Used reliably for asynchronous background messages.

### How to Answer Exam Questions on this Topic:
*   **Stub and Skeleton (5M):** Graphically draw the exact text diagram below. Define Stub as the Client-Side proxy responsible for Marshalling (packing) the method arguments. Define Skeleton as the Server-Side proxy responsible for Unmarshalling (unpacking) the arguments and handing them to the real server object.
    ```text
     [Client] ---> [Stub Proxy] ---> (Network) ---> [Skeleton Proxy] ---> [Server Object]
                     (Packs)                          (Unpacks)
    ```
*   **6 Steps of RMI (10-12M):** Write the 6 sequence steps clearly, and provide the `Adder` Interface, Implementation, Server, and Client code blocks shown above.
*   **EJB Types (5M):** State the 3 unique types (Stateless, Stateful, Message-Driven) and provide a one-line example of what they strictly track.
*   **Differences Between RMI and EJB (10-12M):** Draw the basic architectural diagram and provide at least 10 key differences as shown below.

---

## 5. Differences Between RMI and EJB (Ultimate Comparison)

**Concept Diagram: RMI vs EJB Architecture**

```text
       [RMI Model]                          [EJB Model]
   (Direct Connection)               (Managed Container Service)

 [Client] ----> [RMI Server]        [Client] ---> [[ EJB Container ]]
               (Your Logic)               |      ---> [Security]
                                          |      ---> [Transactions]
    *No built-in security*                |      ---> [Connection Pools]
    *No automatic scaling*                |-----> [EJB Real Object]
                                          
                                    *Fully Managed & Highly Secure*
```

**Key Differences (11 Points for Exams)**

| Point | Feature | RMI (Remote Method Invocation) | EJB (Enterprise JavaBeans) |
| :--- | :--- | :--- | :--- |
| **1** | **Primary Purpose** | Basic remote communication between Java apps. | Building massive, secure, distributed enterprise apps. |
| **2** | **Architecture** | Direct client-server framework (Stub/Skeleton). | Runs within a strict managed EJB Container (App Server). |
| **3** | **Underlying Tech** | Lower-level foundational networking technology. | Advanced component built *on top* of RMI internally. |
| **4** | **Middleware** | Does not require any specialized application server. | Strictly requires an EJB Container (like JBoss, GlassFish). |
| **5** | **Security** | No built-in security (developers write it manually). | High-level automatic security (declarative annotations). |
| **6** | **Transactions** | No built-in database/transaction rollback features. | Fully automated transaction management (ACID properties). |
| **7** | **Scalability** | Poor scalability; struggles with huge traffic spikes. | Highly scalable through automated connection pooling. |
| **8** | **Complexity & Cost** | Lightweight, simple, and very cheap to implement. | Heavyweight, highly complex, and requires high resources. |
| **9** | **Object Lifecycle** | Developer manually creates/destroys logic objects. | Container silently manages the entire object lifecycle. |
| **10** | **Protocols Used** | Uses JRMP (Java Remote Method Protocol). | Uses RMI-IIOP and advanced enterprise network protocols. |
| **11** | **Application Size** | Ideal for small/medium internal network applications. | Ideal for large-scale robust systems (Banking, E-Commerce). |

**Real-World Example:**

*   **RMI Example (Simple System):** A basic local university network where an admin's computer sends a simple "fetch student marks" request directly to the main campus database server.
*   **EJB Example (Enterprise System):** A global banking ATM network where millions of users withdraw money simultaneously, demanding strict security authorizations, multi-threaded server load balancing, and immediate transaction rollbacks if the network drops.
