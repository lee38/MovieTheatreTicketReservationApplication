package Boundaries.SelectionBoundaries;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SeatSelectionGUI {
    // GUI-related components
    private JPanel selectedInfoPanel;
    private JPanel proceedToPaymentPanel;
    private JButton payForSeatButton;
    private JPanel headerPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel bodyPanel;
    private JTextField selectedSeatRowField;
    private JTextField selectedSeatColField;
    private JLabel selectedSeatColLabel;
    private JLabel selectedSeatRowLabel;
    private JPanel col3SeatsPanel;
    private JButton seatRowACol3Button;
    private JButton seatRowBCol3Button;
    private JButton seatRowCCol3Button;
    private JPanel col1SeatsPanels;
    private JButton seatRowACol1Button;
    private JButton seatRowBCol1Button;
    private JButton seatRowCCol1Button;
    private JButton seatRowACol2Button;
    private JButton seatRowBCol2Button;
    private JButton seatRowCCol2Button;
    private JPanel col2SeatsPanel;
    private JPanel seatsButtonPanel;
    private JPanel availableSeatsLabelPanel;
    private JLabel selectedMoveLabel;
    private JTextField selectedMovieField;
    private JLabel selectedShowtimeLabel;
    private JTextField selectedShowtimeField;
    private JLabel availableSeatsLabel;

    private JPanel seatSelectionPanel;
    private JFrame seatSelectionFrame;

    private JButton[][] seatButtons;

    /**
     * Maximum number of rows of seats.
     */
    private static final int ROW_COUNT = 3;

    /**
     * Maximum number of columns of seats
     */
    private static final int COL_COUNT = 3;

    /**
     * Constructs the GUI as a new JFrame
     */
    public SeatSelectionGUI() {
        seatSelectionFrame = new JFrame("Movie Selection GUI");
        seatSelectionFrame.setContentPane(this.seatSelectionPanel);
        seatSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createSeatButtonsArray();

        seatSelectionFrame.pack();
        seatSelectionFrame.setVisible(true);
    }

    /**
     * Closes the GUI by destroying the associated frame.
     */
    public void close() {
        seatSelectionFrame.dispose();
    }


    /**
     * Helper method to organize the JButtons into array seatButtons.
     */
    private void createSeatButtonsArray() {
        seatButtons = new JButton[3][3];
        seatButtons[0][0] = seatRowACol1Button;
        seatButtons[0][1] = seatRowACol2Button;
        seatButtons[0][2] = seatRowACol3Button;

        seatButtons[1][0] = seatRowBCol1Button;
        seatButtons[1][1] = seatRowBCol2Button;
        seatButtons[1][2] = seatRowBCol3Button;

        seatButtons[2][0] = seatRowCCol1Button;
        seatButtons[2][1] = seatRowCCol2Button;
        seatButtons[2][2] = seatRowCCol3Button;
    }

    /**
     * Adds action listeners to all seat selection buttons
     * @param listeners list of ActionListeners to add to each seat JButton
     */
    public void addSeatButtonListeners(ActionListener[][] listeners) {
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++)
                seatButtons[i][j].addActionListener(listeners[i][j]);
        }
    }

    /**
     * Add action listener to the 'Pay for seat' button.
     * @param listener ActionListener object to add to payForSeatButton
     */
    public void addPayForSeatButtonListener(ActionListener listener) {
        payForSeatButton.addActionListener(listener);
    }

    /**
     * Update all seat status and makes occupied seats non-selectable and marked with an "X".
     * @param seatBookedStatusArr an array of boolean representing the status of each seat's occupancy
     *                            true means a seat is booked, false means a seat is available for booking.
     */
    public void updateSeatButtonsStatus(boolean[][] seatBookedStatusArr) {
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                seatButtons[i][j].setEnabled(!seatBookedStatusArr[i][j]);
                if (seatBookedStatusArr[i][j])
                    seatButtons[i][j].setText("X");
            }
        }
    }

    // Getters and setters below
    public void setMovieText(String movie) {
        selectedMovieField.setText(movie);
    }

    public void setShowtimeText(String showtime) {
        selectedShowtimeField.setText(showtime);
    }

    public void setSeatRowText(String row) {
        selectedSeatRowField.setText(row);
    }

    public void setSeatColText(String col) {
        selectedSeatColField.setText(col);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(seatSelectionPanel, message);
    }

    public static void main(String[] args) {
        SeatSelectionGUI gui = new SeatSelectionGUI();

        boolean[][] booleanArr = new boolean[ROW_COUNT][COL_COUNT];
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < ROW_COUNT; j++)
                booleanArr[i][j] = true;
        }

        booleanArr[1][1] = false;

        gui.updateSeatButtonsStatus(booleanArr);
    }
}
