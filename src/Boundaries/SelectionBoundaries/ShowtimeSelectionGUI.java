package Boundaries.SelectionBoundaries;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;

/**
 * This class represents a GUI for selecting a showtime.
 */
public class ShowtimeSelectionGUI {
    // GUI-related components
    private JPanel bodyPanel;
    private JPanel searchShowtimePanel;
    private JPanel selectedMoviePanel;
    private JScrollPane showtimeResultsScrollPane;
    private JList<String> showtimeResultsList;
    private JPanel showtimeSelectedPanel;
    private JTextField selectedShowtimeField;
    private JPanel proceedToShowtimePanel;
    private JButton proceedToSeatsButton;
    private JPanel headerPanel;
    private JButton mainMenuButton;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JComboBox<String> availableDatesComboBox;

    private JPanel showtimeSelectionPanel;
    private JTextField selectedMovieField;
    private JFrame showtimeSelectionFrame;

    /**
     * Constructs the GUI as a new JFrame.
     */
    public ShowtimeSelectionGUI() {
        showtimeSelectionFrame = new JFrame("Movie Selection GUI");
        showtimeSelectionFrame.setContentPane(this.showtimeSelectionPanel);
        showtimeSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showtimeSelectionFrame.pack();
        showtimeSelectionFrame.setVisible(true);
    }

    /**
     * Closes the GUI by destroying the associated frame.
     */
    public void close() {
        showtimeSelectionFrame.dispose();
    }

    public String getMovieSelectedText() {
        return selectedMovieField.getText();
    }

    /**
     * Add listener to the 'View Available Seats' button.
     * @param listener ActionListener object to add to proceedToSeatsButton.
     */
    public void addProceedToSeatsButtonListener(ActionListener listener) {
        proceedToSeatsButton.addActionListener(listener);
    }

    /**
     * Adds listener to listen for user's showtime selection in the result list.
     * @param listener ListSelectionListener object to add to showtime results JList
     */
    public void addShowtimeResultSelectedListener(ListSelectionListener listener) {
        ListSelectionModel listSelectionModel = showtimeResultsList.getSelectionModel();
        listSelectionModel.addListSelectionListener(listener);
    }

    /**
     * Sets the selected movie field
     * @param movieName the movie name String
     */
    public void setSelectedMovieText(String movieName) {
        selectedMovieField.setText(movieName);
    }

    /**
     * Sets the selected showtime field
     * @param showtimeInfo the showtime info String
     */
    public void setSelectedShowtimeText(String showtimeInfo) {
        selectedShowtimeField.setText(showtimeInfo);
    }

    /**
     * Clears items in the showtime results list.
     */
    public void clearSearchResult() {
        ((DefaultListModel<String>) showtimeResultsList.getModel()).clear();
    }

    /**
     * Appends an item to the showtime search result list.
     * @param showtimeResult line of text describing the showtime
     */
    public void addShowtimeSearchResult(String showtimeResult) {
        ((DefaultListModel<String>) showtimeResultsList.getModel()).addElement(showtimeResult);
    }

    public void addAvailableDate(String date) {
        availableDatesComboBox.addItem(date);
    }

    /**
     * Retrieve the currently selected movie.
     * @return String of showtime info selected in the showtime results list
     */
    public String getShowtimeResultSelected() {
        return showtimeResultsList.getSelectedValue();
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(showtimeSelectionPanel, message);
    }


    public static void main(String[] args) {
        new ShowtimeSelectionGUI();
    }
}
