package utils;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modals.Car;
import modals.ParkingSlot;

/**
 * ParkingManager handles various operations related to parking slots and cars.
 * It provides methods to park/unpark cars, validate slots and registration numbers,
 * and perform other parking-related tasks.
 * 
 * @author Arun Ragavendhar
 * @version 1.0.0
 * @date 16/10/2024
 */
public class ParkingManager {

    /**
     * Checks if a parking slot is occupied.
     * 
     * @param slotId The ID of the parking slot to check.
     * @return True if the slot is occupied, false otherwise.
     */
    public static boolean isSlotOccupied(String slotId) {
        for (ParkingSlot slot : AppState.parkingSlots.getList()) {
            if (slot.getSlotId().equalsIgnoreCase(slotId)) {
                return slot.isOccupied(); // Return true if the slot is occupied
            }
        }
        return false; // Return false if the slot is not found or not occupied
    }

    /**
     * Validates the format of a slot ID.
     * 
     * @param slotId The slot ID to validate.
     * @return True if the slot ID is valid, false otherwise.
     */
    public static boolean isValidSlotId(String slotId) {
        return slotId.matches("^[VS]\\d{2}$"); // Valid format: 'S01' or 'V01'
    }

    /**
     * Prompts the user to enter a valid slot ID.
     * Ensures that the slot ID follows the correct format.
     * 
     * @return The valid slot ID entered by the user, or null if canceled.
     */
    public static String askForSlotId() {
        String slotId;
        do {
            slotId = JOptionPane.showInputDialog(null, "Enter slot ID (e.g., S01 or V01):", "Slot ID", JOptionPane.QUESTION_MESSAGE);
            if (slotId == null) {
                return null; // User canceled input
            }
            slotId = slotId.trim();
            if (!isValidSlotId(slotId)) {
                JOptionPane.showMessageDialog(null, "Invalid slot ID format. Must start with 'S' for staff or 'V' for visitors, followed by two digits.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                break; // Valid slot ID entered
            }
        } while (true);
        return slotId;
    }

    /**
     * Validates the format of a car registration number.
     * It must start with a letter followed by four digits.
     * 
     * @param regNo The registration number to validate.
     * @return True if the registration number is valid, false otherwise.
     */
    public static boolean isValidRegNo(String regNo) {
        return regNo.matches("^[A-Za-z]\\d{4}$");
    }

    /**
     * Prompts the user to enter a valid car registration number.
     * 
     * @return The valid car registration number, or null if canceled.
     */
    public static String askForCarId() {
        String carId;
        do {
            carId = JOptionPane.showInputDialog(null, "Enter car registration number (e.g., A1234):", "Car Registration", JOptionPane.QUESTION_MESSAGE);
            if (carId == null) {
                return null; // User canceled input
            }
            carId = carId.trim();
            if (!isValidRegNo(carId)) {
                JOptionPane.showMessageDialog(null, "Invalid registration number format. Must start with a letter followed by four digits (e.g., A1234).", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                break; // Valid registration number entered
            }
        } while (true);
        return carId.toUpperCase(); // Return in uppercase
    }

    /**
     * Prompts the user to confirm if the car owner is a staff member.
     * 
     * @return True if the car owner is a staff member, false otherwise.
     */
    public static boolean askIfStaffMember() {
        int staffResponse = JOptionPane.showConfirmDialog(null, "Is the car owner a staff member?", "Staff Member", JOptionPane.YES_NO_OPTION);
        return (staffResponse == JOptionPane.YES_OPTION);
    }

    /**
     * Checks if a car with the same registration number is already parked in any slot.
     * 
     * @param regNo The registration number to check.
     * @return True if the car is already parked, false otherwise.
     */
    public static boolean isCarAlreadyParked(String regNo) {
        for (ParkingSlot slot : AppState.parkingSlots.getList()) {
            if (slot.isOccupied() && slot.getCar().getRegNo().equalsIgnoreCase(regNo)) {
                return true; // The car is already parked
            }
        }
        return false; // The car is not parked
    }

    /**
     * Parks a car in the specified slot, if the slot is available and the car
     * matches the slot type (staff vs. visitor), and the car is not already parked.
     * 
     * @param slotId The ID of the slot to park in.
     * @return True if the car was parked successfully, false otherwise.
     */
    public static boolean parkCar(String slotId) {
        // Validate the slot ID format
        if (!isValidSlotId(slotId)) {
            JOptionPane.showMessageDialog(null, "Invalid slot ID format.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Find the slot and attempt to park the car
        for (ParkingSlot slot : AppState.parkingSlots.getList()) {
            if (slot.getSlotId().equalsIgnoreCase(slotId)) {
                if (!slot.isOccupied()) {
                    // Prompt the user for car details
                    String regNo = askForCarId();
                    if (regNo != null) {
                        // Check if the car is already parked
                        if (isCarAlreadyParked(regNo)) {
                            JOptionPane.showMessageDialog(null, "This car is already parked in another slot.", "Car Already Parked", JOptionPane.ERROR_MESSAGE);
                            return false;
                        }

                        String ownerName = JOptionPane.showInputDialog(null, "Enter the car owner's name:", "Owner Name", JOptionPane.QUESTION_MESSAGE);
                        boolean isStaff = askIfStaffMember();

                        // Check if car type matches slot type
                        if (slot.isStaffSlot() != isStaff) {
                            JOptionPane.showMessageDialog(null, "Mismatch: Staff cars must park in staff slots and visitor cars in visitor slots.", "Error", JOptionPane.ERROR_MESSAGE);
                            return false;
                        }

                        // Create a new Car instance and park it
                        Car car = new Car(ownerName, regNo, isStaff);
                        slot.parkCar(car); // Park the car
                        AppState.parkingSlots.notifyListeners(); // Notify listeners to update the UI
                        return true; // Car parked successfully
                    }
                } else {
                    // Slot is already occupied
                    JOptionPane.showMessageDialog(null, "The slot is already occupied.", "Slot Occupied", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Slot ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
        return false; // Slot ID not found or parking failed
    }

    /**
     * Deletes a parking slot by its slot ID if it is not occupied.
     *
     * @param slotId The ID of the slot to be deleted.
     * @return True if deletion was successful, false otherwise.
     */
    public static boolean deleteParkingSlot(String slotId) {
        for (ParkingSlot slot : AppState.parkingSlots.getList()) {
            if (slot.getSlotId().equalsIgnoreCase(slotId)) {
                if (!slot.isOccupied()) {
                    AppState.parkingSlots.remove(slot);
                    System.out.println("Parking slot deleted successfully.");
                    return true;
                } else {
                    System.out.println("Cannot delete slot. It is currently occupied.");
                    return false;
                }
            }
        }

        System.out.println("Slot ID not found.");
        return false;
    }

    /**
     * Deletes all unoccupied parking slots by identifying unoccupied slots
     * and removing them from the list.
     *
     * @return The number of slots that were deleted.
     */
    public static int deleteAllUnoccupiedSlots() {
        List<ParkingSlot> toRemove = new ArrayList<>();
        for (ParkingSlot slot : AppState.parkingSlots.getList()) {
            if (!slot.isOccupied()) {
                toRemove.add(slot);
            }
        }

        AppState.parkingSlots.removeAll(toRemove);
        System.out.println("All unoccupied parking slots have been deleted.");
        return toRemove.size();
    }

    /**
     * Finds and returns the parking slot for a car based on its registration number.
     * 
     * @param carRegNo The registration number of the car.
     * @return The ParkingSlot object if found, otherwise null.
     */
    public static ParkingSlot findCar(String carRegNo) {
        for (ParkingSlot slot : AppState.parkingSlots.getList()) {
            if (slot.isOccupied() && slot.getCar().getRegNo().equalsIgnoreCase(carRegNo)) {
                return slot; // Car found
            }
        }
        return null; // Car not found
    }
}
