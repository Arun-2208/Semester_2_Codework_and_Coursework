package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * ObservableList is a generic list that supports listeners to observe changes
 * in the list. Whenever the list is modified (addition, removal, updating, or
 * clearing of elements), all registered listeners are notified, allowing UI
 * components (such as slot panels) to refresh automatically.
 *
 * This class is particularly useful in UI-based applications where dynamic data
 * changes should reflect in the UI without manual intervention. For instance,
 * when parking slots are added, removed, or updated in a Parking Lot Management
 * System, this observable list can trigger a refresh of the slot panel UI.
 *
 * @param <T> The type of elements in this list.
 * @author Arun Ragavendhar - 104837257
 * @date - 16/10/2024
 * @file ObservableList.java
 * @description A list that notifies listeners when modified, enabling automatic UI updates.
 */
public class ObservableList<T> {

  // Internal list to store elements
  private List<T> list = new ArrayList<>();

  // List of listeners (usually UI components) that observe changes in the list
  private List<Runnable> listeners = new ArrayList<>();

  /**
   * Returns the entire list.
   * 
   * @return the list of elements.
   */
  public List<T> getList() {
    return list;
  }

  /**
   * Adds an element to the list and notifies all registered listeners.
   * 
   * @param element the element to add.
   */
  public void add(T element) {
    list.add(element); // Add the element to the list
    notifyListeners(); // Notify listeners about the change
  }

  /**
   * Removes an element from the list by index and notifies all registered
   * listeners.
   * 
   * @param index the index of the element to remove.
   */
  public void remove(int index) {
    if (index >= 0 && index < list.size()) {
      list.remove(index); // Remove the element at the specified index
      notifyListeners(); // Notify listeners about the removal
    }
  }

  /**
   * Removes an element from the list by value and notifies all registered
   * listeners.
   * 
   * @param element the element to remove.
   */
  public void remove(T element) {
    if (list.remove(element)) { // Remove the element if found
      notifyListeners(); // Notify listeners about the removal
    }
  }

  /**
   * Removes all occurrences of the elements in the given list and notifies
   * listeners.
   * 
   * @param elementsToRemove a list of elements to remove from the list.
   */
  public void removeAll(List<T> elementsToRemove) {
    boolean listModified = false;
    for (T element : elementsToRemove) {
      if (list.remove(element)) {
        listModified = true; // Mark the list as modified if any element is removed
      }
    }
    if (listModified) {
      notifyListeners(); // Notify listeners if any changes were made
    }
  }

  /**
   * Updates an element in the list by index and notifies all registered
   * listeners.
   * 
   * @param index      the index of the element to update.
   * @param newElement the new element to replace the old one.
   */
  public void updateElement(int index, T newElement) {
    if (index >= 0 && index < list.size()) {
      list.set(index, newElement); // Update the element at the specified index
      notifyListeners(); // Notify listeners about the update
    } else {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
  }

  /**
   * Returns the number of elements in the list.
   * 
   * @return the size of the list.
   */
  public int size() {
    return list.size();
  }

  /**
   * Checks if the list contains a specific element.
   * 
   * @param element the element to check.
   * @return true if the list contains the element, false otherwise.
   */
  public boolean contains(T element) {
    return list.contains(element);
  }

  /**
   * Clears the list and notifies all registered listeners.
   */
  public void clear() {
    list.clear(); // Clear the list
    notifyListeners(); // Notify listeners that the list has been cleared
  }

  /**
   * Retrieves an element by its index.
   * 
   * @param index the index of the element to retrieve.
   * @return the element at the specified index.
   * @throws IndexOutOfBoundsException if the index is out of range.
   */
  public T getElement(int index) {
    if (index >= 0 && index < list.size()) {
      return list.get(index); // Return the element at the specified index
    } else {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
  }

  /**
   * Registers a listener to observe changes in the list.
   * Whenever the list is modified (add, remove, update), the listener's run
   * method will be called.
   * 
   * @param listener the listener to register.
   */
  public void addChangeListener(Runnable listener) {
    listeners.add(listener); // Add the listener to the list of observers
  }

  /**
   * Notifies all registered listeners of a change in the list.
   * This is usually triggered after an addition, removal, update, or clearing of
   * elements.
   */
  public void notifyListeners() {
    for (Runnable listener : listeners) {
      listener.run(); // Invoke the run method of each registered listener
    }
  }
}
