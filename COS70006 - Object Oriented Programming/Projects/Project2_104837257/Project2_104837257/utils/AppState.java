package utils;

import modals.ParkingSlot;

/**
 * AppState is a utility class that stores shared application state, such as the
 * list of parking slots and the height for control buttons. It provides methods
 * to initialize parking slots for staff and visitors.
 * 
 * @author Arun Ragavendhar - 104837257
 * @date  - 16/10/2024
 * @file AppState.java
 * @description Shared application state for managing parking slots and UI controls.
 */
public class AppState {
  
  // Static list to store shared parking slots
  public static ObservableList<ParkingSlot> parkingSlots = new ObservableList<>();

  // Constant for the height of control buttons
  public final static int CONTROL_BUTTON_HEIGHT = 60;

  /**
   * Initializes the parking slots for staff and visitors.
   * This method populates the parkingSlots list with a specified number of staff and visitor slots.
   * 
   * @param numberOfStaffSlots   the number of staff parking slots to initialize
   * @param numberOfVisitorSlots the number of visitor parking slots to initialize
   */
  public static void initializeParkingSlots(int numberOfStaffSlots, int numberOfVisitorSlots) {
    // Create staff slots with IDs "S01", "S02", etc.
    for (int i = 1; i <= numberOfStaffSlots; i++) {
      String slotId = 'S' + String.format("%02d", i);
      parkingSlots.add(new ParkingSlot(slotId, true)); // Add a staff slot
    }

    // Create visitor slots with IDs "V01", "V02", etc.
    for (int i = 1; i <= numberOfVisitorSlots; i++) {
      String slotId = 'V' + String.format("%02d", i);
      parkingSlots.add(new ParkingSlot(slotId, false)); // Add a visitor slot
    }
  }
}
