package modals;

/**
 * Represents an option modal with a title, action, and visibility status.
 * This modal is used to display options for parking slots, such as park, unpark, or remove slot.
 * 
 * @author Arun Ragavendhar - 104837257
 * @date - 16/10/2024
 * @file OptionModal.java
 * @description Class representing an option modal with various attributes such as title, option action, and visibility status.
 */
public class OptionModal {

  private String title; // The title of the modal dialog
  private String optionAction; // The action associated with the modal option
  private boolean visibilityStatus; // True if the slot is occupied, false otherwise

  /**
   * Constructs an OptionModal with a title, action, and an initial visibility status.
   * 
   * @param title            The title of the option modal.
   * @param optionAction     The action associated with the option (e.g., "park", "unpark").
   * @param visibilityStatus True if the slot is occupied, false if it is not.
   */
  public OptionModal(String title, String optionAction, boolean visibilityStatus) {
    this.title = title.trim();
    this.optionAction = optionAction.trim();
    this.visibilityStatus = visibilityStatus;
  }

  /**
   * Gets the title of the OptionModal.
   * 
   * @return The title of the modal.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title of the OptionModal.
   * 
   * @param title The new title for the modal.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets the optionAction of the OptionModal.
   * 
   * @return The action associated with the modal.
   */
  public String getOptionAction() {
    return optionAction;
  }

  /**
   * Sets the optionAction of the OptionModal.
   * 
   * @param optionAction The new action for the modal.
   */
  public void setOptionAction(String optionAction) {
    this.optionAction = optionAction;
  }

  /**
   * Gets the visibility status (occupied/not occupied).
   * 
   * @return True if the slot is occupied, false otherwise.
   */
  public boolean isVisibilityStatus() {
    return visibilityStatus;
  }

  /**
   * Sets the visibility status (occupied/not occupied).
   * 
   * @param visibilityStatus True if the slot is occupied, false otherwise.
   */
  public void setVisibilityStatus(boolean visibilityStatus) {
    this.visibilityStatus = visibilityStatus;
  }

  /**
   * Returns the visibility status as a String ("Occupied" or "Not Occupied").
   * 
   * @return "Occupied" if the status is true, "Not Occupied" otherwise.
   */
  public String getStatusString() {
    return visibilityStatus ? "Occupied" : "Not Occupied";
  }

  /**
   * Displays the modal information.
   * This method prints out the title and visibility status of the modal.
   */
  public void displayModalInfo() {
    System.out.println("Title: " + title);
    System.out.println("Status: " + getStatusString());
  }
}
