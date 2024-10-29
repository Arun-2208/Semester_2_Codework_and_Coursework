package modals;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a parking slot in the parking lot system.
 * Each slot is identified by a unique ID and can be designated for staff or visitor use.
 * The slot can hold a car, record the time it was parked, and calculate the parking fee.
 * 
 * @author Arun Ragavendhar - 104837257
 * @date - 16/10/2024
 * @file ParkingSlot.java
 * @description Class representing a parking slot with details such as ID, type, parked car, time, and fee calculation.
 */
public class ParkingSlot {
    // Instance variables for parking slot details
    private String slotId; // Unique identifier for the parking slot
    private boolean isStaffSlot; // Indicates whether the slot is for staff members
    private Car car; // The car parked in the slot
    private LocalDateTime parkedTime; // Time when the car was parked

    // Formatter for displaying the parking time in a readable format
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructs a ParkingSlot with the specified ID and type.
     *
     * @param slotId      The unique identifier for the parking slot.
     * @param isStaffSlot True if the slot is for staff members, false for visitors.
     */
    public ParkingSlot(String slotId, boolean isStaffSlot) {
        this.slotId = slotId;
        this.isStaffSlot = isStaffSlot;
        this.car = null;
        this.parkedTime = null;
    }

    /**
     * Retrieves the slot ID.
     *
     * @return The slot ID.
     */
    public String getSlotId() {
        return slotId;
    }

    /**
     * Checks if the slot is designated for staff members.
     *
     * @return True if staff slot, false otherwise.
     */
    public boolean isStaffSlot() {
        return isStaffSlot;
    }

    /**
     * Checks if the slot is currently occupied by a car.
     *
     * @return True if occupied, false otherwise.
     */
    public boolean isOccupied() {
        return car != null;
    }

    /**
     * Parks a car in this slot and records the current time.
     *
     * @param car The Car to be parked.
     */
    public void parkCar(Car car) {
        this.car = car;
        this.parkedTime = LocalDateTime.now(); // Record the current time when the car is parked
    }

    /**
     * Removes the parked car from this slot and clears the parked time.
     */
    public void removeCar() {
        this.car = null;
        this.parkedTime = null;
    }

    /**
     * Retrieves the car parked in this slot.
     *
     * @return The parked Car.
     */
    public Car getCar() {
        return car;
    }

    /**
     * Retrieves the parked time formatted as a string.
     *
     * @return The formatted parked time, or "N/A" if no car is parked.
     */
    public String getParkedTimeFormatted() {
        if (parkedTime != null) {
            return parkedTime.format(formatter);
        }
        return "N/A";
    }

    /**
     * Calculates the duration the car has been parked.
     *
     * @return A string representing the duration in hours, minutes, and seconds.
     */
    public String getParkingDuration() {
        if (parkedTime != null) {
            Duration duration = Duration.between(parkedTime, LocalDateTime.now());
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            long seconds = duration.getSeconds() % 60;
            return String.format("%d hours %d minutes %d seconds", hours, minutes, seconds);
        }
        return "N/A";
    }

    /**
     * Calculates the parking fee based on the duration the car has been parked.
     * The fee is $5 per hour, rounded up to the next hour.
     *
     * @return The calculated parking fee.
     */
    public double calculateParkingFee() {
        if (parkedTime != null) {
            Duration duration = Duration.between(parkedTime, LocalDateTime.now());
            long totalMinutes = duration.toMinutes();
            long hours = totalMinutes / 60;
            if (totalMinutes % 60 != 0) {
                hours += 1; // Round up to next hour
            }
            return hours * 5.0; // Fee is $5 per hour
        }
        return 0.0;
    }

    /**
     * Returns a string representation of the parking slot, including details like slot ID,
     * slot type (staff or visitor), occupancy status, and parking details if occupied.
     *
     * @return The string representation of the parking slot.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(slotId)
          .append(" | Slot Type: ").append(isStaffSlot ? "Staff" : "Visitor")
          .append(" | Status: ").append(isOccupied() ? "Occupied" : "Vacant");
        if (isOccupied()) {
            sb.append(" | Car Reg No: ").append(car.getRegNo())
              .append(" | Owner: ").append(car.getOwnerName())
              .append(" | Parking Time: ").append(getParkedTimeFormatted())
              .append(" | Duration: ").append(getParkingDuration())
              .append(" | Fee: $").append(String.format("%.2f", calculateParkingFee()));
        }
        return sb.toString();
    }

    /**
     * Retrieves the parked time as a LocalDateTime object.
     *
     * @return The parked time.
     */
    public LocalDateTime getParkedTime() {
        return parkedTime;
    }

    /**
     * Sets the parked time for this slot.
     *
     * @param parkedTime The LocalDateTime when the car was parked.
     */
    public void setParkedTime(LocalDateTime parkedTime) {
        this.parkedTime = parkedTime;
    }

}
