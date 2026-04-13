import javax.swing.*; // Importing Swing components for GUI
import java.awt.*; // Importing AWT for graphics and layouts
import java.awt.event.ActionEvent; // Importing ActionEvent for button clicks
import java.awt.event.ActionListener; // Importing ActionListener interface

// Custom Panel for drawing shapes
class ShapePanel extends JPanel { // Extending JPanel to create a custom drawing canvas
    @Override // Overriding the paintComponent method
    protected void paintComponent(Graphics g) { // The graphics context is passed here automatically
        super.paintComponent(g); // Calling super to ensure standard panel rendering

        // Drawing a Circle
        g.setColor(Color.RED); // Setting the pen color to Red
        g.fillOval(50, 50, 100, 100); // Drawing a filled circle (x, y, width, height)

        // Drawing a Waveform (using lines)
        g.setColor(Color.BLUE); // Setting the pen color to Blue
        for (int i = 0; i < 300; i += 20) { // Loop to draw multiple connected lines simulating a wave
            g.drawLine(i, 250, i + 10, 200); // Drawing upward slope
            g.drawLine(i + 10, 200, i + 20, 250);// Drawing downward slope
        }
    }
}

public class SwingDrawing { // Main driver class
    public static void main(String[] args) { // Main method
        JFrame frame = new JFrame("Swing Shapes & Buttons"); // Creating a new application window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensuring app terminates when window closes
        frame.setSize(400, 400); // Setting dimensions of the window
        frame.setLayout(new BorderLayout()); // Setting a BorderLayout for the frame

        ShapePanel canvas = new ShapePanel(); // Instantiating the custom drawing panel
        frame.add(canvas, BorderLayout.CENTER); // Adding canvas to the center of the frame

        JPanel buttonPanel = new JPanel(); // Creating a sub-panel to hold buttons
        JButton btn1 = new JButton("Disable Me"); // Creating the first button
        JButton btn2 = new JButton("Enable Other"); // Creating the second button

        btn2.setEnabled(false); // Disabling the second button initially

        // Adding event listeners
        btn1.addActionListener(new ActionListener() { // Anonymous class for button 1 action
            public void actionPerformed(ActionEvent e) { // Method triggered on click
                btn1.setEnabled(false); // Disabling button 1
                btn2.setEnabled(true); // Enabling button 2
            }
        });

        btn2.addActionListener(new ActionListener() { // Anonymous class for button 2 action
            public void actionPerformed(ActionEvent e) { // Method triggered on click
                btn2.setEnabled(false); // Disabling button 2
                btn1.setEnabled(true); // Enabling button 1
            }
        });

        buttonPanel.add(btn1); // Adding button 1 to the sub-panel
        buttonPanel.add(btn2); // Adding button 2 to the sub-panel
        frame.add(buttonPanel, BorderLayout.SOUTH); // Adding sub-panel to the bottom of the frame

        frame.setVisible(true); // Rendering the frame on screen
    }
}