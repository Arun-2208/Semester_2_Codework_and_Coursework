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
 * Represents a button that finds a parked car in the system by its registration number.
 * When clicked, it prompts the user for a car registration number and displays information about the car's parking details.
 * 
 * @author Arun Ragavendhar - 104837257
 * @date - 16/10/2024
 * @file FindCarButton.java
 * @description Button to find and display the details of a parked car.
 */
public class FindCarButton extends JButton implements ActionListener {

  /**
   * Constructs the "Find A Car" button with predefined settings such as alignment and size.
   */
  public FindCarButton() {
    super("Find A Car"); // Set the button text to "Find A Car"
    setAlignmentX(Component.CENTER_ALIGNMENT); // Align the button to the center
    setMaximumSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set max width, predefined height
    setPreferredSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set preferred size
    addActionListener(this); // Register this button as its own ActionListener
  }

  /**
   * Handles the action of finding a parked car by its registration number.
   * This method prompts the user to enter a car registration number and displays the parking details if the car is found.
   * 
   * @param e The action event triggered by clicking the button.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // Prompt the user to enter the car registration number
    String carRegNo = ParkingManager.askForCarId();

    // Attempt to find the car in the parking lot
    ParkingSlot slot = ParkingManager.findCar(carRegNo);

    // If the car is found, display parking details, otherwise show an error
    if (slot != null) {
      JOptionPane.showMessageDialog(null,
          String.format(
              " Car is found and parked at slot %s from %s till now. Total fees: $%.2f. \nCar registration number: %s. Owner: %s",
              slot.getSlotId(),
              slot.getParkedTimeFormatted(),
              slot.calculateParkingFee(),
              slot.getCar().getRegNo(),
              slot.getCar().getOwnerName()),
          "Car Found",
          JOptionPane.INFORMATION_MESSAGE);
    } else {
      // If the car is not found, display an error message
      JOptionPane.showMessageDialog(null,
          String.format("Car with registration number %s not found.", carRegNo),
          "Not Found", JOptionPane.ERROR_MESSAGE);
    }
  }
}
