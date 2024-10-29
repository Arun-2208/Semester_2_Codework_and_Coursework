package control_panels;

import gui_buttons.AddSlotButton;
import gui_buttons.DeleteUnoccupiedSlotBtn;
import gui_buttons.ExitProgramButton;
import gui_buttons.FindCarButton;
import gui_buttons.ParkCarButton;
import gui_buttons.RefreshButton;
import gui_buttons.RemoveSlotButton;
import gui_buttons.UnParkCarButton;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import modals.ParkingSlot;
import utils.ObservableList;

/**
 * ControlPanel is a JPanel that contains buttons to manage parking slots in the system.
 * This panel includes buttons to add, remove, find, and manage parking slots, along with
 * options to refresh the view and exit the program.
 * 
 * @author Arun Ragavendhar - 104837257
 * @date - 16/10/2024
 * @file ControlPanel.java
 * @description A control panel for managing parking slots with buttons for different actions.
 */
public class ControlPanel extends JPanel {
  // Observable list of parking slots being managed
  ObservableList<ParkingSlot> parkingSlots;
  int panelWidth; // The width of the control panel

  /**
   * Constructs a control panel that contains buttons for adding parking slots,
   * deleting unoccupied slots, and exiting the program.
   *
   * @param parkingSlots The list of parking slots to manage.
   * @param panelWidth   The width of the panel.
   */
  public ControlPanel(ObservableList<ParkingSlot> parkingSlots, int panelWidth) {
    this.parkingSlots = parkingSlots;
    this.panelWidth = panelWidth;

    // Set the layout and size properties for the control panel
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setPreferredSize(new Dimension((int) (panelWidth * 0.30), getHeight()));
    setMaximumSize(new Dimension((int) (panelWidth * 0.30), getHeight()));
    setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add padding around the panel

    // Add buttons to the control panel
    add(new RefreshButton()); // Button to refresh the parking slots
    add(new AddSlotButton()); // Button to add a new parking slot
    add(new RemoveSlotButton()); // Button to remove a parking slot
    add(new FindCarButton()); // Button to find a parked car
    add(new ParkCarButton()); // Button to park a car
    add(new UnParkCarButton()); // Button to unpark a car
    add(new DeleteUnoccupiedSlotBtn()); // Button to delete all unoccupied slots
    add(new ExitProgramButton()); // Button to exit the program
  }
}
