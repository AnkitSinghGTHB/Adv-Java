import java.awt.*;
import java.awt.event.*;

/**
 * This class covers common exam programming questions related to AWT and Event
 * Handling.
 * It demonstrates how to use Layout Managers (GridLayout), TextField, Checkbox,
 * CheckboxGroup,
 * and how to handle Button clicks.
 */
public class GUIExamples extends Frame implements ActionListener {

    // Declare GUI Components
    TextField nameField;
    CheckboxGroup genderGroup;
    Checkbox maleBox, femaleBox, termsBox;
    Button submitButton, clearButton;
    Label displayLabel;

    // Constructor to setup the GUI
    public GUIExamples() {
        // 1. Set the Title of the Frame
        super("Exam Preparation: AWT Registration Form");

        // 2. Set the Layout Manager (GridLayout: 5 rows, 2 columns)
        setLayout(new GridLayout(5, 2));

        // 3. Initialize Components
        nameField = new TextField(20);

        // CheckboxGroup groups Checkboxes together so they act as Radio Buttons
        genderGroup = new CheckboxGroup();
        maleBox = new Checkbox("Male", genderGroup, true); // true sets it as default
        femaleBox = new Checkbox("Female", genderGroup, false);

        // Using a Panel (with FlowLayout) to group radio buttons in one grid cell
        Panel genderPanel = new Panel(new FlowLayout());
        genderPanel.add(maleBox);
        genderPanel.add(femaleBox);

        // Standard standalone Checkbox
        termsBox = new Checkbox("I agree to terms");

        submitButton = new Button("Submit");
        clearButton = new Button("Clear");

        displayLabel = new Label("Fill out the form.");

        // 4. Add Components to the Frame (Fills Left-to-Right, Top-to-Bottom in the
        // Grid)
        add(new Label(" Enter Name: ")); // Row 1, Col 1
        add(nameField); // Row 1, Col 2

        add(new Label(" Select Gender: ")); // Row 2, Col 1
        add(genderPanel); // Row 2, Col 2

        add(new Label(" Agreement: ")); // Row 3, Col 1
        add(termsBox); // Row 3, Col 2

        add(submitButton); // Row 4, Col 1
        add(clearButton); // Row 4, Col 2

        add(new Label(" Status: ")); // Row 5, Col 1
        add(displayLabel); // Row 5, Col 2

        // 5. Register Listeners for Event Handling
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);

        // 6. Handle Frame Closing Event (Important for exams)
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.out.println("Closing GUI...");
                System.exit(0);
            }
        });

        // 7. Set Frame Properties (Size, Visibility)
        setSize(400, 250);
        setVisible(true); // MUST explicitly make the frame visible
    }

    /**
     * This method is triggered whenever a registered button is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Determine which button triggered the event using getSource()
        if (e.getSource() == submitButton) {
            if (!termsBox.getState()) {
                displayLabel.setText("Please accept the terms!");
                return;
            }

            String name = nameField.getText();
            String gender = genderGroup.getSelectedCheckbox().getLabel();

            if (!name.isEmpty()) {
                displayLabel.setText(name + " (" + gender + ") Registered!");
            } else {
                displayLabel.setText("Name cannot be empty.");
            }

        } else if (e.getSource() == clearButton) {
            nameField.setText("");
            termsBox.setState(false);
            genderGroup.setSelectedCheckbox(maleBox); // Reset to default
            displayLabel.setText("Form cleared.");
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        System.out.println("Launching GUI...");
        new GUIExamples();
    }
}

/*
 * EXAM EXPLANATION:
 * 1. We extend `Frame` to create a window, and implement `ActionListener` to
 * handle button clicks.
 * 2. We set a `GridLayout` to neatly arrange inputs into rows and columns.
 * 3. We use `CheckboxGroup` to turn checkboxes into radio buttons (only one can
 * be selected).
 * 4. We register the buttons using `addActionListener(this)`.
 * 5. In `actionPerformed`, we use `termsBox.getState()` to see if the box is
 * checked,
 * and `genderGroup.getSelectedCheckbox().getLabel()` to read the radio button
 * value.
 * 6. The `WindowAdapter` ensures the program terminates when the user clicks
 * 'X'.
 */
