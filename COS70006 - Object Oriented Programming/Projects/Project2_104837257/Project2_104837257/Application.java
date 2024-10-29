import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dialogbox.SetupDialog;
import control_panels.ControlPanel;
import control_panels.SlotGridPanel;
import utils.AppState;

/**
 * Application is the main application class for the Swinburne Parking System.
 * It initializes and displays a GUI window with parking slot controls,
 * a grid of parking slots, and a title header. Staff and visitor parking
 * slots are dynamically generated upon initialization.
 * 
 * @author Arun Ragavendhar - 104837257
 * @date - 16/10/2024
 * @file Application.java
 * @description Main application class for the parking system, responsible for managing the UI and initialization.
 */
public class Application extends JFrame {

  // Constant string for the application title
  final static String TITLE_STRING = "Arun's Car Parking System";

  // Dimensions for the main frame and control buttons
  final static int MAIN_FRAME_WIDTH = 1000;
  final static int MAIN_FRAME_HEIGHT = 800;

  // Default number of staff and visitor parking slots
  int numberOfStaffSlots = 5;
  int numberOfVisitorSlots = 5;

  /**
   * Constructs the Application by initializing staff and visitor parking slots
   * and setting up the main application window.
   */
  Application() {
    // Show setup dialog to get parking slot configurations
    SetupDialog setupDialog = SetupDialog.showDialog();
    numberOfStaffSlots = setupDialog.getNumberOfStaffSlots();
    numberOfVisitorSlots = setupDialog.getNumberOfVisitorSlots();
    AppState.initializeParkingSlots(numberOfStaffSlots, numberOfVisitorSlots); // Initialize parking slots

    // Main Frame Configuration
    setTitle(TITLE_STRING); // Set the frame title
    setSize(new Dimension(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT)); // Set the frame size
    setLocationRelativeTo(null); // Center the frame on the screen
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits on close
    setLayout(new BorderLayout()); // Set layout manager to BorderLayout

    initComponents(); // Initialize the components of the frame
  }

  /**
   * Initializes the components of the main frame, including the title panel,
   * the slot grid panel, and the control panel.
   */
  void initComponents() {
    // Title Panel for displaying the application title
    JPanel titlePanel = new JPanel() {
      {
        setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the title horizontally
        setPreferredSize(new Dimension(getWidth(), 50)); // Set the height of the title panel
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add padding to the panel
        add(new JLabel() {
          {
            setText(TITLE_STRING); // Set the title text
            setFont(new Font("Monospaced", Font.PLAIN, 24)); // Set font for the title
            setHorizontalAlignment(JLabel.CENTER); // Align the title horizontally
            setVerticalAlignment(JLabel.CENTER); // Align the title vertically
          }
        });
      }
    };

    // Add title panel to the north region of the frame
    add(titlePanel, BorderLayout.NORTH);

    // Add slot grid panel to the center region of the frame (scrollable)
    add(new SlotGridPanel(AppState.parkingSlots, MAIN_FRAME_WIDTH).getScrollPane(), BorderLayout.CENTER);

    // Add control panel to the east region of the frame
    add(new ControlPanel(AppState.parkingSlots, MAIN_FRAME_WIDTH), BorderLayout.EAST);
  }

  /**
   * The main method to run the Application. It invokes the Swing UI thread and
   * makes the application visible.
   * 
   * @param args the command-line arguments (not used)
   */
  public static void main(String[] args) {
    // Use SwingUtilities.invokeLater to ensure thread safety for the Swing UI
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        Application app = new Application(); // Create an instance of Application
        app.setVisible(true); // Set the application window to be visible
      }
    });
  }

}
