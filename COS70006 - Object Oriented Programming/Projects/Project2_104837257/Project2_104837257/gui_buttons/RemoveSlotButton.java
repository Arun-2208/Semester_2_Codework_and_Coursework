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
 * Button to remove a parking slot by asking the user for a Slot ID.
 * When clicked, the button prompts the user to enter the ID of the parking slot to remove,
 * and attempts to delete it from the parking lot system.
 * 
 * @author Arun Ragavendhar - 104837257
 * @date - 16/10/2024
 * @file RemoveSlotButton.java
 * @description Button to remove a parking slot by prompting for a slot ID.
 */
public class RemoveSlotButton extends JButton implements ActionListener {

  /**
   * Constructs the "Remove Slot" button with predefined settings such as alignment and size.
   */
  public RemoveSlotButton() {
    super("Remove Slot"); // Set the button text to "Remove Slot"
    setAlignmentX(Component.CENTER_ALIGNMENT); // Align the button to the center
    setMaximumSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set max width, predefined height
    setPreferredSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set preferred size
    addActionListener(this); // Register this button as its own ActionListener
  }

  /**
   * Handles the action of removing a parking slot by prompting for a slot ID.
   * If the slot is found and can be removed, it deletes the slot from the system and shows a success message.
   * Otherwise, it shows an error message.
   * 
   * @param e The action event triggered by clicking the button.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // Prompt the user for the Slot ID to be removed
    String slotId = ParkingManager.askForSlotId();

    // If the user provides a valid Slot ID, attempt to remove the slot
    if (slotId != null) {
      boolean success = ParkingManager.deleteParkingSlot(slotId); // Call method to delete slot

      // Display success or error messages based on the result
      if (success) {
        JOptionPane.showMessageDialog(null,  slotId + " Slot deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(null, "Slot could not be deleted. It may be occupied or not found.", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
