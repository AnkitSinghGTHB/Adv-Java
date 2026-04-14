# Module 5: RMI and Enterprise JavaBeans (EJB)

Java offers powerful abstractions to build inherently distributed applications capable of operating flawlessly across massive network architectures. 

---

## 1. Introduction to RMI (Remote Method Invocation)
**Definition:**
RMI is a standard API that allows a Java object stationed naturally on a local machine (Client) to seamlessly invoke methods on an object stationed securely on a different remote machine (Server). 

In basic terms, it allows code to execute functions across entirely different JVMs over the internet as if they were running locally on the exact same laptop.

### The RMI Architecture Diagram
RMI achieves remote communication by natively utilizing two critical proxy objects:
1.  **Stub (Client-Side):** A gateway or proxy residing on the client. It intercepts the client's method call, bundles (marshals) the arguments securely, and ships the package across the network structurally.
2.  **Skeleton (Server-Side):** A gateway residing on the server. It receives the package physically from the Stub, unbundles (unmarshals) the arguments, and safely passes them natively to the actual real object residing on the server.

---

## 2. Steps to write an RMI Application
*(This 6-step process is a confirmed 10-12 Mark Exam Question)*

To code an RMI application successfully, developers must execute exactly 6 crucial steps:

1.  **Create the Remote Interface:** Define an interface natively extending `java.rmi.Remote`. Every method defined inside must forcibly throw `java.rmi.RemoteException`.
    ```java
    public interface Adder extends Remote {
        public int add(int x, int y) throws RemoteException;
    }
    ```
2.  **Provide the Implementation Class:** Write a class that correctly implements your newly built interface AND natively extends `java.rmi.server.UnicastRemoteObject`.
3.  **Compile the Implementation Class using `rmic`:** Once traditionally compiled using `javac`, you must natively run the `rmic` (RMI Compiler) tool natively via command prompt strictly on the implementation class to synthetically generate the precise Stub and Skeleton files.
    ```bash
    rmic AdderImpl
    ```
4.  **Start the RMI Registry Services:** Start the foundational naming service provided by the OS.
    ```bash
    start rmiregistry
    ```
5.  **Create and Start the Server Application:** The server instantiates the real implementation object and natively registers it structurally with the RMI registry using the `Naming.rebind()` or `bind()` method string.
    ```java
    Naming.rebind("rmi://localhost:5000/MyAdder", new AdderImpl());
    ```
6.  **Create and Start the Client Application:** The client queries the RMI registry using the identical string through `Naming.lookup()` to fetch the localized Stub and trigger native methods.

---

## 3. Enterprise JavaBeans (EJB) Overview
*(Often asked as a 5-mark conceptual note)*

While RMI allows distributed object communication seamlessly, **EJB** was formulated explicitly on top of RMI to architect heavily scaling enterprise-grade business applications sustainably.

**Key Definition:** 
EJB is a server-side software structural component framework encompassing business logic strictly for J2EE environments. The container provides invisible systemic services like automatic transaction management, heavy security pooling, and persistence transparently.

### Advantages over RMI:
*   While RMI demands manual socket mapping and thread scaling, EJB effortlessly handles automated database transactions, multi-user scaling, and strict security structurally out of the box dynamically.

### Disadvantages of EJB:
*   Massively complex to understand theoretically and build cleanly.
*   Mandates the heavy usage of robust Application Servers (like Glassfish or WebLogic) rather than simple standard JVM servers.

### Types of Enterprise Beans:
1.  **Stateless Session Bean:** Does not maintain conversational structural state per user flawlessly. Highly scalable and memory efficient.
2.  **Stateful Session Bean:** Strictly maintains conversational state memory mapping dynamically for a single client (e.g., E-commerce Shopping Cart metrics).
3.  **Message-Driven Bean:** Architected exclusively for seamless asynchronous messaging workflows.

---

## 4. Typical Exam Questions ("Test Me")

### 5 Mark Questions:
1. What is RMI? Define the critical underlying roles of the Stub and Skeleton classes.
2. What are Enterprise JavaBeans (EJB)? Briefly name and describe the 3 distinct types of EJB heavily encountered structurally in Enterprise domains.
3. Compare the capabilities of RMI securely against native socket programming structurally.

### 10 Mark Questions:
1. Explain Java System RMI Architecture. List out deeply the required 6 sequential steps vital to creating and successfully deploying a functional distributed Client-Server Java application cleanly utilizing RMI structures natively. Support your theory with tiny code snippets representing the Interface.
