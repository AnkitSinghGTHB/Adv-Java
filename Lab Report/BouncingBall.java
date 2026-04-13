import javax.swing.JFrame;   // Importing JFrame for the application window
import javax.swing.JPanel;   // Importing JPanel as the drawing surface
import java.awt.Color;       // Importing Color class
import java.awt.Graphics;    // Importing Graphics class for rendering
import java.awt.Dimension;   // Importing Dimension for panel sizing

// Swing-based Bouncing Ball using JPanel and Runnable for multithreading
public class BouncingBall extends JPanel implements Runnable {

    int x = 150; // Fixed X coordinate for the ball
    int y = 50;  // Initial Y coordinate
    int yDir = 1; // Direction of Y (1 = down, -1 = up)
    Thread t;     // Thread variable for the animation loop

    public BouncingBall() { // Constructor to initialize the panel
        setBackground(Color.BLACK);                    // Setting panel background color
        setPreferredSize(new Dimension(400, 300));     // Setting preferred size of the panel
        t = new Thread(this);                          // Instantiating the thread with this panel instance
        t.start();                                     // Starting the animation thread
    }

    @Override
    public void run() { // The thread's execution block
        while (true) { // Infinite loop for continuous animation
            try {
                y += (5 * yDir); // Modifying Y coordinate by 5 pixels based on direction

                if (y >= 250) {    // If ball hits the bottom boundary
                    yDir = -1;     // Reverse direction (bounce up)
                } else if (y <= 0) { // If ball hits the top boundary
                    yDir = 1;      // Reverse direction (bounce down)
                }

                repaint();           // Request a UI update (calls paintComponent indirectly)
                Thread.sleep(30);    // Pause for 30 milliseconds to control frame rate
            } catch (InterruptedException e) { // Handling thread interruptions
                e.printStackTrace();           // Printing error trace
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) { // Method to render graphics on the panel
        super.paintComponent(g);                 // Clearing previous frame by calling parent method
        g.setColor(Color.RED);                   // Setting drawing color to Red
        g.fillOval(x, y, 30, 30);               // Drawing the ball at updated coordinates
    }

    public static void main(String[] args) { // Main method — entry point for the application
        JFrame frame = new JFrame("Bouncing Ball"); // Creating the application window with a title
        BouncingBall ball = new BouncingBall();      // Creating an instance of the BouncingBall panel
        frame.add(ball);                             // Adding the panel to the frame
        frame.pack();                                // Sizing the frame to fit the panel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closing app on window close
        frame.setLocationRelativeTo(null);           // Centering the window on the screen
        frame.setVisible(true);                      // Making the window visible
    }
}