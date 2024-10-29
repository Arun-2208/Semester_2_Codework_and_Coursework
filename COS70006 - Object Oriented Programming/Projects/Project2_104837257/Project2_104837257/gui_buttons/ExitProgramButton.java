package gui_buttons;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import utils.AppState;

/**
 * Represents a button that exits the program.
 * When clicked, the button terminates the application.
 * 
 * @author Arun Ragavendhar - 104837257
 * @Date - 16/10/2024
 * @file ExitProgramButton.java
 * @description Button to exit the parking lot program.
 */
public class ExitProgramButton extends JButton implements ActionListener {

  /**
   * Constructs the "Exit" button with predefined settings such as alignment and size.
   */
  public ExitProgramButton() {
    super("Exit"); // Set the button text to "Exit"
    setAlignmentX(Component.CENTER_ALIGNMENT); // Align the button to the center
    setMaximumSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set max width, predefined height
    setPreferredSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set preferred size
    addActionListener(this); // Register this button as its own ActionListener
  }

  /**
   * Exits the program when the button is clicked.
   * 
   * @param e The action event triggered by clicking the button.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // Terminate the program
    System.exit(0);
  }
}
