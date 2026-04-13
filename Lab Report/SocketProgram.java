import java.io.*; // Importing IO classes for streams
import java.net.*; // Importing networking classes (Socket, ServerSocket)

public class SocketProgram { // Main class
    public static void main(String[] args) { // Main method

        // Thread representing the Server
        Thread serverThread = new Thread(() -> { // Lambda expression for server execution
            try {
                ServerSocket server = new ServerSocket(5000); // Server listening on port 5000
                System.out.println("Server: Waiting for client..."); // Status message
                Socket socket = server.accept(); // Blocking call, waits for client connection
                System.out.println("Server: Client connected!"); // Connection success

                DataInputStream in = new DataInputStream(socket.getInputStream()); // Stream to receive data
                String msg = in.readUTF(); // Reading UTF string from client
                System.out.println("Server received: " + msg); // Printing received message

                server.close(); // Shutting down server socket
            } catch (IOException e) { // Handling networking errors
                e.printStackTrace(); // Printing stack trace
            }
        });

        // Thread representing the Client
        Thread clientThread = new Thread(() -> { // Lambda expression for client execution
            try {
                Thread.sleep(1000); // Pause to ensure server starts first
                Socket socket = new Socket("localhost", 5000); // Connecting to localhost on port 5000

                DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // Stream to send data
                out.writeUTF("Hello Server, from Client!"); // Sending UTF string to server
                System.out.println("Client: Message sent."); // Status message

                socket.close(); // Shutting down client socket
            } catch (Exception e) { // Catching general exceptions (including InterruptedException)
                e.printStackTrace(); // Printing stack trace
            }
        });

        serverThread.start(); // Starting server thread
        clientThread.start(); // Starting client thread
    }
}