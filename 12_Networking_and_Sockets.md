# Module 5: Java Networking and Sockets

Java's `java.net` package provides powerful infrastructure to enable computing devices to share resources across networks. 

---

## 1. Networking Basics and Terminology
1.  **IP Address:** A unique numerical logical address assigned to a node over a network (e.g., `192.168.0.1`).
2.  **Protocol:** A strict set of rules governing data communication (e.g., TCP, HTTP, FTP).
3.  **Port Number:** A communication endpoint that uniquely identifies a specific running application on a server (e.g., HTTP runs on Port 80, Tomcat on 8080).
4.  **MAC Address:** A physical, unique hardware identifier of the Network Interface Controller (NIC).
5.  **Socket:** An endpoint linking two-way communication between two programs executing across the network.

---

## 2. The `InetAddress` Class
The `java.net.InetAddress` class is used to represent an Internet Protocol (IP) address structurally. It does not have public constructors, so instantiation requires factory methods.

**Key Methods:**
*   `public static InetAddress getByName(String host)`: Resolves hostnames to IP addresses.
*   `public static InetAddress getLocalHost()`: Resolves local device IP.
*   `public String getHostName()`: Returns the logical string domain.
*   `public String getHostAddress()`: Returns the physical IP integer map.

**Example:**
```java
InetAddress ip = InetAddress.getByName("www.google.com");
System.out.println("IP Address: " + ip.getHostAddress());
```

---

## 3. TCP/IP Socket Programming (Connection-Oriented)
TCP generates reliable, connection-oriented data pathways where acknowledgments are mandatory to ensure data fidelity.

### A. Server Socket (`ServerSocket`)
Resides strictly on the Server application. It listens statically for incoming client requests on an established port.
*   **Method:** `public Socket accept()` forces the server program to wait/block until a client successfully connects.
```java
ServerSocket ss = new ServerSocket(6666);
Socket s = ss.accept(); // Pauses until client attempts connection
```

### B. Client Socket (`Socket`)
Resides on the user's application, explicitly targeting the server's IP and predetermined Port.
*   **Method:** `public InputStream getInputStream()` to read server data.
*   **Method:** `public OutputStream getOutputStream()` to write out data.
```java
Socket s = new Socket("localhost", 6666);
```

---

## 4. UDP Datagram Programming (Connection-Less)
UDP generates connectionless communication. It rapidly fires packets blindly over the network without verifying whether the receiver actually correctly acquired them (Fast, but unreliable). Useful in continuous media streaming.

*   **`DatagramSocket`:** Represents a connection-less socket used strictly for sending or organically receiving datagram packets.
*   **`DatagramPacket`:** The actual envelope representing the data packet to be routed across the network.

**Coding Example (Server Receiving Packet):**
```java
DatagramSocket ds = new DatagramSocket(3000);
byte[] buf = new byte[1024];
DatagramPacket dp = new DatagramPacket(buf, 1024);
ds.receive(dp); // Receives the packet natively
String str = new String(dp.getData(), 0, dp.getLength());
System.out.println("Received: " + str);
ds.close();
```

---

## 5. Web Interactions (URL & URLConnection)
Java strictly encapsulates web resource mapping into object logic natively.

### A. `URL` Class
Points seamlessly to resources on the World Wide Web.
```java
URL url = new URL("http://www.google.com/index.html");
System.out.println("Protocol: " + url.getProtocol());
System.out.println("Port: " + url.getPort());
System.out.println("File: " + url.getFile());
```

### B. `URLConnection` / `HttpURLConnection`
Represents an active communication link between the configured `URL` and your Java application. You use the native `URLConnection` to scrape or download server data organically.
```java
URL url = new URL("http://www.google.com");
HttpURLConnection huc = (HttpURLConnection) url.openConnection();
System.out.println("Cache Control: " + huc.getHeaderField("Cache-Control"));
huc.disconnect();
```

---

## 6. Typical Exam Questions ("Test Me")

### 2/5 Mark Questions:
1. Explain the primary differences structurally between a Socket and a ServerSocket.
2. Differentiate between TCP connection-oriented protocols and UDP connection-less protocols.
3. Write a code snippet to display the IP Address of a given website using `InetAddress`.

### 10/12 Mark Questions:
1. Define Socket Programming. Write a complete client-server Java application utilizing TCP `Socket` and `ServerSocket` where the client connects to the server securely on port 6666 and sends a "Hello Server" generic message.
2. Explain Java's `URL` class and `HttpURLConnection` features. Write a small script parsing headers from an active Website organically.
