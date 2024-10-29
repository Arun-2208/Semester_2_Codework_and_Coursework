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
 * Represents a button for unparking a car from a parking slot.
 * This button prompts the user to enter a car's registration number
 * and removes the car from the parking slot if found.
 * 
 * @author Arun Ragavendhar - 104837257
 * @date - 16/10/2024
 * @file UnParkCarButton.java
 * @description Handles the action of removing a car from a parking slot and displays the car's parking duration and fee.
 */
public class UnParkCarButton extends JButton implements ActionListener {

  /**
   * Constructs an "UnPark Car" button with predefined settings.
   * The button is aligned to the center and sized to the maximum width
   * while maintaining a predefined height.
   */
  public UnParkCarButton() {
    super("UnPark Car"); // Set the button text to "UnPark Car"
    setAlignmentX(Component.CENTER_ALIGNMENT); // Align the button to the center
    setMaximumSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set max width, predefined height
    setPreferredSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set preferred size
    addActionListener(this); // Register this button as its own ActionListener
  }

  /**
   * Invoked when the button is clicked. This method prompts the user for a
   * car registration number and attempts to remove the car from its parking slot,
   * showing the total duration and fee the car was parked for.
   * 
   * @param e The action event triggered by the button click.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // Prompt the user for the car registration number
    String regNo = ParkingManager.askForCarId();

    // Check if the user input is valid (non-null and non-empty)
    if (regNo != null && !regNo.trim().isEmpty()) {
      // Iterate through parking slots to find the car
      for (ParkingSlot slot : AppState.parkingSlots.getList()) {
        if (slot.isOccupied() && slot.getCar().getRegNo().equalsIgnoreCase(regNo.trim())) {

          // If the car is found and parked, show the parking duration and fee
          if (slot.isOccupied()) {
            double fee = slot.calculateParkingFee(); // Calculate the parking fee
            String duration = slot.getParkingDuration(); // Get the parking duration
            JOptionPane.showMessageDialog(null, 
                String.format("Car was parked for %s. Total fee: $%.2f", duration, fee),
                "Car Removed", JOptionPane.INFORMATION_MESSAGE);
          }

          // Remove the car from the slot
          slot.removeCar();
          AppState.parkingSlots.notifyListeners(); // Update the UI
          JOptionPane.showMessageDialog(null, "Car with registration number " + regNo + " was removed successfully.",
              "Success", JOptionPane.INFORMATION_MESSAGE);
          return; // Exit once the car is removed
        }
      }

      // If the car is not found in any slot
      JOptionPane.showMessageDialog(null, "Car with registration number " + regNo + " not found.", "Error",
          JOptionPane.ERROR_MESSAGE);
    } else {
      // If the input is invalid
      JOptionPane.showMessageDialog(null, "Invalid registration number entered.", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}
