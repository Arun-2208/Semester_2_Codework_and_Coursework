package gui_buttons;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import utils.AppState;
import utils.ParkingManager;

/**
 * Represents a button that deletes all unoccupied parking slots from the system.
 * When clicked, it removes all unoccupied slots and notifies the user about the number of slots deleted.
 * 
 * @author Arun Ragavendhar - 104837257 
 * @date - 16/10/2024
 * @file DeleteUnoccupiedSlotBtn.java
 * @description Button to delete unoccupied parking slots from the parking lot system.
 */
public class DeleteUnoccupiedSlotBtn extends JButton implements ActionListener {

  /**
   * Constructs the "Delete all unoccupied parking slots" button.
   * The button text is centered and the button is sized to the maximum width while maintaining a predefined height.
   */
  public DeleteUnoccupiedSlotBtn() {
    super("<html><center>Delete all unoccupied<br/>parking slots</center></html>"); // Set the button text with HTML formatting
    setAlignmentX(Component.CENTER_ALIGNMENT); // Align the button to the center
    setMaximumSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set max width, predefined height
    setPreferredSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set preferred size
    addActionListener(this); // Register this button as its own ActionListener
  }

  /**
   * Handles the action of deleting all unoccupied slots.
   * This method invokes the ParkingManager to delete all unoccupied slots and
   * displays a confirmation dialog showing the number of slots deleted.
   * 
   * @param e The action event triggered by clicking the button.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // Delete all unoccupied slots and get the total number of slots deleted
    int totalSlots = ParkingManager.deleteAllUnoccupiedSlots();

    // Display a confirmation message with the number of slots deleted
    JOptionPane.showMessageDialog(null, totalSlots + " slots deleted successfully.");
  }
}
