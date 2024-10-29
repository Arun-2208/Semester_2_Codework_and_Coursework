package modals;

/**
 * Represents a car with an owner, registration number, and staff status.
 * The car object stores information about its owner and whether the owner is a staff member.
 * 
 * @author Arun Ragavendhar - 104837257
 * @date - 16/10/2024
 * @file Car.java
 * @description Class representing a car with associated details such as registration number, owner's name, and staff membership status.
 */
public class Car {
    // Instance variables for car details
    private String ownerName; // Name of the car's owner
    private String regNo; // Car's registration number
    private boolean isStaffMember; // Indicates if the car owner is a staff member

    /**
     * Constructs a Car with the specified registration number, owner's name, and staff status.
     *
     * @param regNo         The registration number of the car.
     * @param ownerName     The name of the car's owner.
     * @param isStaffMember True if the owner is a staff member, false otherwise.
     */
    public Car(String ownerName, String regNo, boolean isStaffMember) {
        this.ownerName = ownerName;
        this.regNo = regNo;
        this.isStaffMember = isStaffMember;
    }

    /**
     * Retrieves the registration number of the car.
     *
     * @return The registration number.
     */
    public String getRegNo() {
        return regNo;
    }

    /**
     * Retrieves the owner's name of the car.
     *
     * @return The owner's name.
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Checks if the owner is a staff member.
     *
     * @return True if the owner is a staff member, false otherwise.
     */
    public boolean isStaffMember() {
        return isStaffMember;
    }
}
