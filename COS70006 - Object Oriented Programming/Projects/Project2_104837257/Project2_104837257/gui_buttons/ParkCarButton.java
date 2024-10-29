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
 * ParkCarButton is a JButton that, when clicked, triggers the car parking
 * process.
 * This button is intended to allow users to park a car in an available parking
 * slot.
 * It is styled with predefined settings such as size and alignment.
 * 
 * @author Arun Ragavendhar
 * @version 1.0.0
 * @since 16/10/2024
 */
public class ParkCarButton extends JButton implements ActionListener {

    /**
     * Constructs a "Park Car" button with predefined settings.
     * The button is aligned to the center and sized to the maximum width
     * while maintaining a predefined height.
     */
    public ParkCarButton() {
        super("Park Car"); // Set the button text to "Park Car"
        setAlignmentX(Component.CENTER_ALIGNMENT); // Align the button to the center
        setMaximumSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set max size with defined height
        setPreferredSize(new Dimension(Integer.MAX_VALUE, AppState.CONTROL_BUTTON_HEIGHT)); // Set preferred size
        addActionListener(this); // Register this button as its own ActionListener
    }

    /**
     * This method is triggered when the "Park Car" button is clicked.
     * It prompts the user for a slot ID, checks if the slot is occupied, 
     * and then proceeds with parking the car if the slot is available.
     * 
     * @param e The action event triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the slot ID from the user
        String slotId = ParkingManager.askForSlotId();  
        
        // Check if a valid slot ID was entered
        if (slotId != null) {
            // Check if the selected slot is already occupied
            if (ParkingManager.isSlotOccupied(slotId)) {
                // If the slot is occupied, display an error message to the user
                JOptionPane.showMessageDialog(null, 
                    "The slot " + slotId + " is already occupied.", 
                    "Slot Occupied", JOptionPane.ERROR_MESSAGE);
            } else {
                // If the slot is not occupied, attempt to park the car
                boolean success = ParkingManager.parkCar(slotId);
                
                // Display the result of the parking attempt
                if (success) {
                    // Success message
                    JOptionPane.showMessageDialog(null, 
                        "Car parked successfully in slot: " + slotId, 
                        "Parking Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Failure message
                    JOptionPane.showMessageDialog(null, 
                        "Failed to park the car. Slot may be invalid.", 
                        "Parking Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            // Error message if no slot ID was entered or the input was invalid
            JOptionPane.showMessageDialog(null, 
                "No slot ID entered or invalid input.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
