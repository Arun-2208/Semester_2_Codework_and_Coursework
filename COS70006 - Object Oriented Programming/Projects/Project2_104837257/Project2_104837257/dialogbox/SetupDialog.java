package dialogbox;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * SetupDialog class provides a dialog window for configuring the number of
 * parking slots for staff and visitors in a parking setup.
 * It allows the user to input the number of staff and visitor slots and
 * validate the input.
 * This dialog blocks further input until it is closed.
 * 
 * @author Arun Ragavendhar - 104837257
 * @date - 16/10/2024
 * @file SetupDialog.java
 * @description A modal dialog for setting up the number of staff and visitor parking slots.
 */
public class SetupDialog extends JDialog {

  // Constant for dialog title
  private final static String DIALOG_TITLE = "Welcome to Arun's Parking Setup";
  int maxParkingSlot; // Maximum number of parking slots allowed

  // Text fields for inputting number of slots
  private JTextField numberOfStaffSlots; // Input field for staff slots
  private JTextField numberOfVisitorSlots; // Input field for visitor slots

  /**
   * Constructs a modal dialog with the specified title and initializes its
   * components. The dialog is set to a fixed size and centered on the screen.
   * 
   * @param maxParkingSlot Maximum allowed parking slots.
   */
  public SetupDialog(int maxParkingSlot) {
    super((Frame) null, DIALOG_TITLE, true); // Create a modal dialog
    this.maxParkingSlot = maxParkingSlot;

    setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Close dialog on exit
    setSize(new Dimension(400, 220)); // Set the dialog size
    setLocationRelativeTo(null); // Center the dialog on the screen
    initComponents(); // Initialize UI components
  }

  /**
   * Default constructor that sets a maximum of 99 parking slots.
   */
  public SetupDialog() {
    this(99);
  }

  /**
   * Initializes the components of the dialog, including text fields for entering
   * the number of staff and visitor slots, and buttons to submit the input or
   * close the dialog.
   */
  private void initComponents() {
    // Creating the dialog content pane
    JPanel dialogContentPane = new JPanel();
    dialogContentPane.setLayout(new BoxLayout(dialogContentPane, BoxLayout.PAGE_AXIS));
    dialogContentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Panel for staff slot input
    JPanel staffInputPanel = new JPanel();
    staffInputPanel.setLayout(new BoxLayout(staffInputPanel, BoxLayout.Y_AXIS));

    JLabel staffInputLabel = new JLabel("Enter number of staff slots:");
    numberOfStaffSlots = new JTextField();
    numberOfStaffSlots.setToolTipText("Please enter a positive number of staff parking slots");

    // Panel for visitor slot input
    JPanel visitorInputPanel = new JPanel();
    visitorInputPanel.setLayout(new BoxLayout(visitorInputPanel, BoxLayout.Y_AXIS));

    JLabel visitorInputLabel = new JLabel("Enter number of visitor slots:");
    numberOfVisitorSlots = new JTextField();
    numberOfVisitorSlots.setToolTipText("Please enter a positive number of visitor parking slots");

    // Panel for buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

    // Close button to exit the program
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(e -> System.exit(0));

    // Submit button to validate and process the input
    JButton submitButton = new JButton("Submit");
    submitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          // Parse and validate input values
          int numberOfStSlots = Integer.parseInt(numberOfStaffSlots.getText().trim());
          int numberOfVsSlots = Integer.parseInt(numberOfVisitorSlots.getText().trim());

          if (numberOfStSlots <= 0 || numberOfVsSlots <= 0) {
            throw new NumberFormatException("Please enter a positive number greater than 0 for both slots.");
          }
          if (numberOfStSlots > maxParkingSlot || numberOfVsSlots > maxParkingSlot) {
            throw new NumberFormatException(String.format("%d  - is the Maximum allowed no of slots per user type  ", maxParkingSlot));
          }

          if ((numberOfStSlots > 0 && numberOfStSlots <= maxParkingSlot) && 
              (numberOfVsSlots > 0 && numberOfVsSlots <= maxParkingSlot)) {
            dispose(); // Close the dialog if input is valid
            return;
          }

        } catch (NumberFormatException numberEx) {
          String errorMessage = "";

          // Handle empty input fields
          if (numberEx.getMessage().contains("For input string: \"\"")) {
            errorMessage = "Input fields cannot be empty";
          } else {
            errorMessage = numberEx.getMessage();
          }

          // Display error message for invalid input
          JOptionPane.showMessageDialog(
              getContentPane(),
              errorMessage,
              "Invalid Input",
              JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
          // Handle any other unexpected errors
          JOptionPane.showMessageDialog(
              getContentPane(),
              "Something went wrong!!",
              "Error",
              JOptionPane.ERROR_MESSAGE);
          System.exit(0);
        }
      }
    });

    // Add components to the content pane
    staffInputPanel.add(staffInputLabel);
    staffInputPanel.add(numberOfStaffSlots);
    dialogContentPane.add(staffInputPanel);
    dialogContentPane.add(Box.createVerticalStrut(20)); // Add spacing

    visitorInputPanel.add(visitorInputLabel);
    visitorInputPanel.add(numberOfVisitorSlots);
    dialogContentPane.add(visitorInputPanel);
    dialogContentPane.add(Box.createVerticalStrut(20)); // Add spacing

    buttonPanel.add(closeButton);
    buttonPanel.add(submitButton);
    dialogContentPane.add(buttonPanel);

    // Set the dialog content pane
    setContentPane(dialogContentPane);
  }

  /**
   * Displays the SetupDialog as a modal dialog, blocking input to other windows.
   * 
   * @return an instance of SetupDialog
   */
  public static SetupDialog showDialog() {
    SetupDialog setupDialog = new SetupDialog();
    setupDialog.setVisible(true);
    return setupDialog;
  }

  /**
   * Returns the dialog title.
   * 
   * @return The title of the dialog.
   */
  public static String getDialogTitle() {
    return DIALOG_TITLE;
  }

  /**
   * Returns the number of staff slots entered by the user.
   * 
   * @return the number of staff slots as an Integer.
   * @throws NumberFormatException if the input is not a valid integer.
   */
  public Integer getNumberOfStaffSlots() throws NumberFormatException {
    return Integer.parseInt(numberOfStaffSlots.getText());
  }

  /**
   * Returns the number of visitor slots entered by the user.
   * 
   * @return the number of visitor slots as an Integer.
   * @throws NumberFormatException if the input is not a valid integer.
   */
  public Integer getNumberOfVisitorSlots() throws NumberFormatException {
    return Integer.parseInt(numberOfVisitorSlots.getText());
  }
}
