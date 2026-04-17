# Module 12: Networking and Sockets (Simplified)

## 1. Networking Basics

**Simple Explanation (Universal Analogy):**
Imagine reliably sending an official letter to your friend formally working in a vast multi-storey concrete building.
1.  **IP Address:** The building's precise physical street address (e.g., `192.168.0.1`).
2.  **Port Number:** Your exact friend's specific distinct room number exclusively inside the large building (e.g., Port `8080`).
3.  **Protocol:** The common strict language you natively write the formal letter in so they firmly understand (e.g., `HTTP`, `TCP`).
4.  **Socket:** The actual physical red mailbox you distinctly drop the letter securely into!

---

## 2. TCP vs UDP Sockets (Guaranteed 5-8 Mark Diff)

**Simple Explanation:**
*   **TCP (Connection-Oriented):** Exactly like a standard telephone call. You strongly dial the number, they securely answer, and you keep an active connected wire line firmly open. If you securely say "Hello" and the heavy line natively drops, you structurally know immediately. It is 100% reliable but distinctly slightly slower.
*   **UDP (Connection-Less):** Exactly like dropping a sealed envelope into a giant local post box without paying for tracking. You simply throw it into the system and heavily *hope* the receiver organically receives it safely. You frankly never fundamentally get a guaranteed delivery receipt cleanly. It is highly unreliable but extremely incredibly fast.

| Feature | TCP (Transmission Control Protocol) | UDP (User Datagram Protocol) |
| :--- | :--- | :--- |
| **Connection** | Connection-Oriented (Telephone call) | Connection-Less (Non-tracked local Mail envelope) |
| **Reliability** | 100% Reliable (Strict guaranteed delivery) | Highly Unreliable (Packets can structurally drop) |
| **Speed** | Marginally Slower | Vastly Faster |
| **Java Classes** | `Socket`, `ServerSocket` | `DatagramSocket`, `DatagramPacket` |

---

## 3. TCP Server-Client Code (10-12 Mark Guarantee!)

To write an effective TCP program gracefully, you strictly need 2 unique files structurally. The active Server (safely waiting endlessly for a telephone call) and the active Client (physically making the direct call).

**1. The Exact Server Code (`ServerSocket`)**
```java
import java.net.*;

public class MyServer {
    public static void main(String[] args) {
        try {
            // 1. Establish the robust Server actively on secure Port 6666
            ServerSocket ss = new ServerSocket(6666);
            System.out.println("Server robustly waiting exclusively for client...");
            
            // 2. ABSOLUTELY STOP and actively Wait perfectly until a client firmly connects
            Socket s = ss.accept();
            System.out.println("Client Successfully securely Connected!");
            
            ss.close();
        } catch(Exception e) { System.out.println(e); }
    }
}
```

**2. The Client Code (`Socket`)**
```java
import java.net.*;

public class MyClient {
    public static void main(String[] args) {
        try {
            // 1. Forcefully dial the target Server's IP address and assigned Port (6666)
            Socket s = new Socket("localhost", 6666);
            
            s.close();
        } catch(Exception e) { System.out.println(e); }
    }
}
```

---

## 4. IP Addresses natively in Java (`InetAddress`)

We securely utilize the `InetAddress` extensive class cleanly to strongly discover active IP addresses logically. It specifically explicitly does not have a public basic constructor (you structurally absolutely can't universally use `new`).

**Exam Code Snippet:**
```java
import java.net.InetAddress;

public class IPFinder {
    public static void main(String[] args) {
        try {
            // Actively Get the Google domain's structured IP
            InetAddress ip = InetAddress.getByName("www.google.com");
            System.out.println("Active IP: " + ip.getHostAddress());
            
        } catch(Exception e) { System.out.println(e); }
    }
}
```

### How to Answer Exam Questions on this Topic:
*   **TCP vs UDP (5-8M):** Always visually heavily draw the tabular matrix. Strictly distinctly emphasize cleanly that TCP explicitly exclusively uses `Socket` locally and heavily UDP structurally exclusively uses specifically `DatagramSocket`.
*   **Networking Code (12M):** Strictly deeply write securely both Server logic and Client codes organically exactly under heading block comments explicitly. Clearly powerfully emphasize deeply that `ServerSocket.accept()` radically explicitly pauses deeply the server absolutely firmly until a physical client safely securely arrives cleanly.
