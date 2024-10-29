package gui_buttons;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import modals.ParkingSlot;
import utils.AppState;
import utils.ParkingManager;

/**
 * Represents a button that adds new parking slots to the system.
 * When clicked, the button prompts the user for a slot ID and staff/visitor information,
 * then adds the parking slot if it does not already exist.
 * 
 * @author Arun Ragavendhar - 104837257
 * @Date - 16/10/2024 
 * @file AddSlotButton.java
 * @description Button to add new parking slots to the system.
 */
public class AddSlotButton extends JButton implements ActionListener {

  /**
   * Constructs the "Add New Slot" button with predefined settings
   * such as alignment and size.
   */
  public AddSlotButton() {
    super("Add New Slot"); // Set the button text to "Add New Slot"
    setAlignmentX(Component.CENTER_ALIGNMENT); // Align the button to the center
    setMaximumSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set max width, predefined height
    setPreferredSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set preferred size
    addActionListener(this); // Register this button as its own ActionListener
  }

  /**
   * Adds a new parking slot to the parking lot. Ensures no duplicate slot IDs
   * are added by checking for existing IDs in the list of parking slots.
   *
   * @param slot The ParkingSlot object to be added.
   * @return True if the slot is added successfully, false if the slot ID already
   *         exists.
   */
  public boolean addParkingSlot(ParkingSlot slot) {
    // Check if the slot ID already exists in the list
    for (ParkingSlot s : AppState.parkingSlots.getList()) {
      if (s.getSlotId().equalsIgnoreCase(slot.getSlotId())) {
        return false; // Duplicate slot ID
      }
    }
    // Add the new slot if no duplicate is found
    AppState.parkingSlots.add(slot);
    return true;
  }

  /**
   * Handles the action of adding a new parking slot. Prompts the user for a slot ID
   * and whether it is a staff slot, then attempts to add it to the parking lot.
   *
   * @param e The action event triggered by clicking the button.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // Prompt the user to enter the slot ID
    String slotId = ParkingManager.askForSlotId();
    if (slotId == null) {
      return; // User canceled or provided invalid ID
    }

    // Ask if the slot is for staff or a visitor
    boolean isStaffSlot = ParkingManager.askIfStaffMember();

    // Create a new ParkingSlot object
    ParkingSlot newSlot = new ParkingSlot(slotId, isStaffSlot);
    boolean success = addParkingSlot(newSlot);

    // Notify the user if the slot was added or if a duplicate was found
    if (success) {
      JOptionPane.showMessageDialog(null, "Parking slot added successfully.", "Success",
          JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, "Slot ID already exists. Please retry with another Slot ID .", "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}
