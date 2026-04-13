package Question_Bank_Solutions;

import javax.swing.*;
import java.awt.*;

public class NestedLayoutApp extends JFrame {
    public NestedLayoutApp() {
        setTitle("Nested Layouts Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Frame Layout: BorderLayout
        setLayout(new BorderLayout());

        // --- North Panel: FlowLayout ---
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        northPanel.setBackground(Color.LIGHT_GRAY);

        northPanel.add(new JLabel("Welcome to the Control Center"));
        northPanel.add(new JButton("Logout"));

        // Add North Panel to the North boundary of main frame
        add(northPanel, BorderLayout.NORTH);

        // --- Center Panel: GridLayout ---
        JPanel centerPanel = new JPanel();
        // 3 rows, 2 columns grid
        centerPanel.setLayout(new GridLayout(3, 2, 10, 10));

        centerPanel.add(new JLabel("Username:"));
        centerPanel.add(new JTextField("admin"));

        centerPanel.add(new JLabel("Server ID:"));
        centerPanel.add(new JTextField("SRV-01A1"));

        centerPanel.add(new JLabel("Status:"));
        centerPanel.add(new JButton("CONNECT"));

        // Add Center Panel to the Center boundary of main frame
        // Due to BorderLayout, this grid stretches to fill all space seamlessly.
        add(centerPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new NestedLayoutApp().setVisible(true);
    }
}