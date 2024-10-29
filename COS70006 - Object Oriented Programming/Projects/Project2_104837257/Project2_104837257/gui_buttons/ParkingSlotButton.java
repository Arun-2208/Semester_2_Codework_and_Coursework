package gui_buttons;

import dialogbox.SlotOptionDialog;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import modals.OptionModal;
import modals.ParkingSlot;
import utils.AppState;
import utils.ParkingManager;

/**
 * ParkingSlotButton is a custom JButton representing a parking slot in the system. 
 * The button displays the slot's ID and changes its border color based on whether 
 * the slot is designated for staff or general use.
 * 
 * @author Arun Ragavendhar - 104837257
 * @date - 16/10/2024
 * @file ParkingSlotButton.java
 * @description Button to represent and interact with parking slots in the system.
 */
public class ParkingSlotButton extends JButton implements ActionListener {

  /**
   * The parking slot associated with this button.
   */
  private ParkingSlot parkingSlot;

  /**
   * The height of the button in pixels.
   */
  int buttonHeight;

  /**
   * Constructs a ParkingSlotButton with the provided parking slot and button height.
   * The button's text is set to the slot ID, and its border color is determined by 
   * whether the parking slot is for staff (green) or visitor (yellow).
   * 
   * @param parkingSlot  The parking slot associated with this button.
   * @param buttonHeight The height of the button in pixels.
   */
  public ParkingSlotButton(ParkingSlot parkingSlot, int buttonHeight) {
    super(String.format("<html><center>%s<br>%s<br>%s<br>%s<br>%s<center></html>", 
      parkingSlot.getSlotId(),
      parkingSlot.isOccupied() ? ("(Occupied - " + parkingSlot.getCar().getRegNo() + ")") : "(Vacant)",
      parkingSlot.isOccupied() ? ("Owner: " + parkingSlot.getCar().getOwnerName()) : "",
      parkingSlot.isOccupied() ? ("Fee: $" + parkingSlot.calculateParkingFee()) : "",
      parkingSlot.isOccupied() ? ("Duration: " + parkingSlot.getParkingDuration()) : ""
    ));
    this.parkingSlot = parkingSlot;
    this.buttonHeight = buttonHeight;

    // Set button size constraints and border based on slot type
    setPreferredSize(new Dimension(Integer.MAX_VALUE, buttonHeight));
    setMinimumSize(new Dimension(Integer.MAX_VALUE, buttonHeight));
    setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonHeight));
    setBorder(BorderFactory.createEmptyBorder());
    setContentAreaFilled(false);
    setOpaque(true);
    setBackground(parkingSlot.isOccupied() ? new Color(171, 171, 171) : 
      (parkingSlot.isStaffSlot() ? new Color(128, 170, 255) : new Color(255, 149, 128)));
    addActionListener(this);
    repaint();
  }

  /**
   * Handles button click events. Opens a dialog allowing the user to park/unpark
   * cars or remove the slot if unoccupied.
   *
   * @param e The action event triggered by a click on the button.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    List<OptionModal> options = new ArrayList<>() {{
      add(new OptionModal("Park Car", "park", !parkingSlot.isOccupied()));
      add(new OptionModal("UnPark Car", "unpark", parkingSlot.isOccupied()));
      add(new OptionModal("Remove Slot", "remove_slot", !parkingSlot.isOccupied()));
    }};

    // Show the SlotOptionDialog to allow the user to choose an action
    SlotOptionDialog dialog = SlotOptionDialog.showDialog(parkingSlot, options);
    
    if (dialog != null && dialog.getOptionAction() != null) {
      switch (dialog.getOptionAction()) {
        case "park": {
          // Park Car
          ParkingManager.parkCar(parkingSlot.getSlotId());
          break;
        }

        case "unpark": {
          // Get the parking duration and fee before unparking the car
          String duration = parkingSlot.getParkingDuration();
          double fee = parkingSlot.calculateParkingFee();

          // Unpark the car and update the system
          parkingSlot.removeCar();
          AppState.parkingSlots.notifyListeners();

          // Show a message with the parking duration and fee
          JOptionPane.showMessageDialog(getRootPane(), 
            String.format("Car was parked for %s. Total fee: $%.2f", duration, fee), 
            "UnPark Successfully", 
            JOptionPane.INFORMATION_MESSAGE);
          break;
        }
        
        case "remove_slot": {
          if (parkingSlot.isOccupied()) {
            // Show a message if the slot is occupied and cannot be removed
            JOptionPane.showMessageDialog(getRootPane(), 
              "Slot is occupied and cannot be removed", 
              "Message", 
              JOptionPane.CLOSED_OPTION);
            return;
          }

          // Remove the slot from the system
          AppState.parkingSlots.remove(parkingSlot);
          JOptionPane.showMessageDialog(getRootPane(), 
            parkingSlot.getSlotId() + " Slot is removed", 
            "Message", 
            JOptionPane.CLOSED_OPTION);
          break;
        }
        
        default:
          return;
      }
    }
  }

  /**
   * Retrieves the parking slot associated with this button.
   * 
   * @return The ParkingSlot object.
   */
  public ParkingSlot getParkingSlot() {
    return parkingSlot;
  }

  /**
   * Sets a new parking slot for this button and updates its appearance accordingly.
   * 
   * @param parkingSlot The new ParkingSlot to associate with this button.
   */
  public void setParkingSlot(ParkingSlot parkingSlot) {
    this.parkingSlot = parkingSlot;
  }
}
