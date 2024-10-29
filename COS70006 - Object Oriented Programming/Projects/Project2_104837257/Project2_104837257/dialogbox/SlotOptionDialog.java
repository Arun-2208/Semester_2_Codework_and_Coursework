package dialogbox;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modals.OptionModal;
import modals.ParkingSlot;

/**
 * SlotOptionDialog is a modal dialog that displays a list of options
 * as buttons. When an option button is clicked, the dialog closes, and
 * the selected option is stored. A "Close" button is also included to
 * exit the dialog without making a selection.
 * 
 * @author Arun Ragavendhar - 104837257
 * @date - 16/10/2024
 * @file SlotOptionDialog.java
 * @description A dialog window for displaying parking slot management options to the user.
 */
public class SlotOptionDialog extends JDialog {
  // Constant for the dialog title
  private final static String DIALOG_TITLE = "Slot Management Options";
  private final static int SPACING = 10;

  /**
   * Stores the action of the selected option. Defaults to null if no option is
   * selected.
   */
  private String optionAction = null;

  ParkingSlot slot;

  /**
   * The list of options to be displayed as buttons in the dialog.
   */
  List<OptionModal> options;

  /**
   * Constructs a SlotOptionDialog with the provided list of options.
   * If the options list is null or empty, an empty list is used instead.
   * 
   * @param slot    The ParkingSlot associated with the dialog.
   * @param options The list of options to display as buttons.
   */
  public SlotOptionDialog(ParkingSlot slot, List<OptionModal> options) {
    super((Frame) null, DIALOG_TITLE, true);
    this.slot = slot;

    // Null check on options; if null, assign an empty list
    if (options == null || options.size() == 0) {
      this.options = new ArrayList<>(); // Empty list as fallback
    } else {
      this.options = options;
    }

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setSize(new Dimension(300, (100 * options.size())));
    setLocationRelativeTo(null);

    initComponents();
  }

  /**
   * Initializes the components of the dialog, including the option buttons
   * and a title label. Adds a "Close" button for exiting the dialog.
   */
  private void initComponents() {
    // Creating the dialog content pane
    JPanel dialogContentPane = new JPanel();
    dialogContentPane.setLayout(new BoxLayout(dialogContentPane, BoxLayout.Y_AXIS));
    dialogContentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    dialogContentPane.setPreferredSize(new Dimension(getWidth(), getHeight()));

    // Add a title label
    dialogContentPane.add(new JLabel() {
      {
        setText("Select Option");
        setFont(new Font("Monospaced", Font.PLAIN, 18));
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
        setAlignmentY(JLabel.CENTER_ALIGNMENT);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
      }
    });
    dialogContentPane.add(Box.createVerticalStrut(SPACING));
    
    // Display parking slot details
    dialogContentPane.add(new JLabel() {
      {
        setText("Parking:\t" + slot.getSlotId());
        setFont(new Font("Monospaced", Font.PLAIN, 16));
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
        setAlignmentY(JLabel.CENTER_ALIGNMENT);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
      }
    });
    dialogContentPane.add(new JLabel() {
      {
        setText("Status:\t" + (slot.isOccupied() ? "Occupied" : "Vacant"));
        setFont(new Font("Monospaced", Font.PLAIN, 16));
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
        setAlignmentY(JLabel.CENTER_ALIGNMENT);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
      }
    });
    dialogContentPane.add(Box.createVerticalStrut(SPACING));

    // Add option buttons for each valid option
    for (OptionModal option : options) {
      if (option.isVisibilityStatus()) {
        dialogContentPane.add(new OptionButton(option.getTitle(), option.getOptionAction())); // Add each option button to the panel
        dialogContentPane.add(Box.createVerticalStrut(SPACING));
      }
    }

    // Add a "Close" button to exit the dialog
    dialogContentPane.add(new OptionButton("Close", null));

    setContentPane(dialogContentPane); // Set the content pane of the dialog
  }

  /**
   * Displays the SlotOptionDialog as a modal dialog, blocking input to other
   * windows. This method is static and can be used to show the dialog with
   * the provided options.
   * 
   * @param slot    The ParkingSlot associated with the dialog.
   * @param options The list of options to display as buttons.
   * @return an instance of SlotOptionDialog, or null if no valid options are
   *         provided.
   */
  public static SlotOptionDialog showDialog(ParkingSlot slot, List<OptionModal> options) {
    // Null check on options; if null or empty, return null
    if (options == null || options.size() == 0) {
      return null;
    }

    SlotOptionDialog setupDialog = new SlotOptionDialog(slot, options);
    setupDialog.setVisible(true);
    return setupDialog;
  }

  /**
   * Returns the list of options provided to the dialog.
   * 
   * @return the list of options.
   */
  public List<OptionModal> getOptions() {
    return options;
  }

  /**
   * Sets a new list of options for the dialog.
   * 
   * @param options the list of options to set.
   */
  public void setOptions(List<OptionModal> options) {
    this.options = options;
  }

  /**
   * Retrieves the action associated with the selected option.
   * 
   * @return The action associated with the selected option.
   */
  public String getOptionAction() {
    return optionAction;
  }

  /**
   * Sets the selected option's action.
   * 
   * @param optionAction The action associated with the selected option.
   */
  public void setOptionAction(String optionAction) {
    this.optionAction = optionAction;
  }

  /**
   * OptionButton is an inner class that extends JButton and handles the option
   * selection. The class stores the option action and sets the selected option
   * when the button is clicked.
   */
  class OptionButton extends JButton implements ActionListener {
    String optionAction;

    /**
     * Constructs an OptionButton with the given title and action. If the action
     * is null, it is assumed to be the "Close" button, and selecting it will close the
     * dialog without selecting an option.
     * 
     * @param buttonTitle   The title of the button.
     * @param optionAction  The action associated with the button.
     */
    OptionButton(Object buttonTitle, String optionAction) {
      super(buttonTitle.toString());
      this.optionAction = optionAction;

      setPreferredSize(new Dimension(Integer.MAX_VALUE, 60));
      setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
      setAlignmentX(JButton.CENTER_ALIGNMENT);

      addActionListener(this);
    }

    /**
     * Handles the button click event. Sets the selected option and closes the
     * dialog. If the "Close" button is clicked, the dialog closes without selecting an option.
     * 
     * @param e The ActionEvent triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (optionAction != null) {
        setOptionAction(optionAction); // Set the selected option action
      }
      dispose(); // Close the dialog
    }
  }
}
