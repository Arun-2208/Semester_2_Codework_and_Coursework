package control_panels;

import gui_buttons.ParkingSlotButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import modals.ParkingSlot;
import utils.ObservableList;

/**
 * SlotGridPanel is a custom JPanel that displays a grid of parking slots,
 * represented as buttons. The layout dynamically updates whenever the underlying
 * parkingSlots list changes. It also provides a scrollable view of the grid,
 * which adapts to the available frame size.
 * 
 * @author Arun Ragavendhar - 104837257
 * @date - 16/10/2024
 * @file SlotGridPanel.java
 * @description A grid panel for displaying parking slots, with automatic layout updates and scrollable view.
 */
public class SlotGridPanel extends JPanel {

  /**
   * The number of columns in the grid.
   */
  final static int GRID_COLUMNS = 3;

  /**
   * The spacing between buttons in the grid layout.
   */
  final static int SPACING = 12;

  /**
   * The height of each button in the grid.
   */
  final static int BUTTON_HEIGHT = 100;

  /**
   * The list of parking slots to be displayed in the grid.
   */
  ObservableList<ParkingSlot> parkingSlots;

  /**
   * The width of the frame that contains this panel. Used to calculate the
   * preferred size.
   */
  int frameWidth;

  /**
   * Constructs a SlotGridPanel with a specified list of parking slots and frame
   * width. The grid layout is initialized with the number of columns and spacing,
   * and a change listener is added to refresh the panel when the list of parking
   * slots changes.
   * 
   * @param parkingSlots the observable list of ParkingSlot objects to display.
   * @param frameWidth   the width of the frame, used to determine panel layout
   *                     and size.
   */
  public SlotGridPanel(ObservableList<ParkingSlot> parkingSlots, int frameWidth) {
    this.parkingSlots = parkingSlots;
    this.frameWidth = frameWidth;

    // Set grid layout with spacing between buttons
    setLayout(new GridLayout(0, GRID_COLUMNS, SPACING, SPACING));
    setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 20)); // Add padding around the panel

    // Attach a listener to refresh the panel when parkingSlots change
    this.parkingSlots.addChangeListener(this::refreshPanel);
    refreshPanel(); // Initial refresh of the panel
  }

  /**
   * Refreshes the panel by clearing existing buttons and re-adding buttons for
   * each parking slot. Each button represents a parking slot, with its background
   * color and border determined by whether it is a staff slot.
   */
  void refreshPanel() {
    // Remove all existing buttons before refreshing the panel
    removeAll();

    // Add a button for each parking slot
    for (ParkingSlot slot : parkingSlots.getList()) {
      ParkingSlotButton slotButton = new ParkingSlotButton(slot, BUTTON_HEIGHT);
      add(slotButton);
    }

    // Adjust the panel size based on the number of slots and the frame width
    setPreferredSize(new Dimension((int) (frameWidth * 0.70), (parkingSlots.size() * BUTTON_HEIGHT) / GRID_COLUMNS));
    revalidate();
    repaint();
  }

  /**
   * Returns a scrollable view (JScrollPane) of this panel. The scroll pane allows
   * the user to navigate through the grid if the panel exceeds the viewable area.
   * 
   * @return a JScrollPane containing this panel, with scroll bars set as needed.
   */
  public JScrollPane getScrollPane() {
    return new JScrollPane(this) {
      {
        // Set scroll policies for vertical and horizontal scrolling
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setBorder(BorderFactory.createEmptyBorder());

        // Set scroll speed for both horizontal and vertical scroll bars
        getHorizontalScrollBar().setUnitIncrement(20);
        getVerticalScrollBar().setUnitIncrement(20);

        // Customize scroll bar thickness
        getVerticalScrollBar().setPreferredSize(new Dimension(8, Integer.MAX_VALUE)); // Set width to 8 pixels
      }
    };
  }

}
