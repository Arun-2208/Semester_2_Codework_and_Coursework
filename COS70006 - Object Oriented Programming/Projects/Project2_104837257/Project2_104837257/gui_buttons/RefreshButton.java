package gui_buttons;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import utils.AppState;

/**
 * Represents a button that refreshes the current state of parking slots in the program.
 * When clicked, it triggers the refresh process to update the displayed parking slots.
 * 
 * @author Arun Ragavendhar - 104837257
 * @date - 16/10/2024
 * @file RefreshButton.java
 * @description Button to refresh the parking lot system state.
 */
public class RefreshButton extends JButton implements ActionListener {

  /**
   * Constructs the "Refresh" button with predefined settings such as alignment and size.
   * The button refreshes the displayed state of parking slots in the system.
   */
  public RefreshButton() {
    super("Refresh"); // Set the button text to "Refresh"
    setAlignmentX(Component.CENTER_ALIGNMENT); // Align the button to the center
    setMaximumSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set max width, predefined height
    setPreferredSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set preferred size
    addActionListener(this); // Register this button as its own ActionListener
  }

  /**
   * Refreshes the displayed state of parking slots when the button is clicked.
   * This method calls AppState's notifyListeners() method to update the UI.
   * 
   * @param e The action event triggered by clicking the button.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // Refresh the parking slots and update the UI
    AppState.parkingSlots.notifyListeners();
  }
}
