package Controllers.SelectionControllers;

import Boundaries.SelectionBoundaries.SeatSelectionGUI;
import Boundaries.TicketPaymentBoundaries.PayTicketGUI;
import Controllers.TicketPaymentControllers.TicketPaymentController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SeatSelectionController {
    /**
     * Used to retrieve seats.
     */
    // TODO: is this necessary? since the selected showtime already references the list of seats
    private TheatreManager theatreManager;

    /**
     * Prompts for user seat selection and displays available seats.
     */
    private SeatSelectionGUI seatSelectionGUI;

    /**
     * Array of seat status based on whether a seat has been booked (true) or not (false).
     */
    private boolean[][] seatBookedStatusArr;

    /**
     * Seat info selected by the user.
     */
    private char selectedRowChar;
    private int selectedSeatNumber;

    /**
     * Maximum number of rows of seats.
     */
    private static final int ROW_COUNT = 3;

    /**
     * Maximum number of columns of seats
     */
    private static final int COL_COUNT = 3;

    public SeatSelectionController() {
        setTheatreManager(null);
        setSeatSelectionGUI(null);
        setSeatBookedStatusArr(null);
        setSelectedRowChar('-');
        setSelectedSeatNumber(0);
    }

    /**
     * Add listeners to the seat selection GUI.
     */
    public void addListeners() {
        seatSelectionGUI.addSeatButtonListeners(createSeatListenersArray());
        seatSelectionGUI.addPayForSeatButtonListener(new ProceedToPaymentListener());
    }

    /**
     * @return A 2D array of action listeners that are attached to each seat.
     */
    private ActionListener[][] createSeatListenersArray() {

        ActionListener[][] seatListeners2D = new ActionListener[ROW_COUNT][COL_COUNT];

        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                seatListeners2D[i][j] = new SeatSelectedButtonListener((char) (i + 'A'), j + 1);
            }
        }
        return seatListeners2D;
    }

    /**
     * Displays the status of seats on the GUI based on whether a seat is booked or not
     */
    public void displaySeatStatus() {
        // Traverse each seat and assign if booked into seat status array
        boolean[][] seatBookedStatusArr = theatreManager.getSeatAvailability();
        seatSelectionGUI.updateSeatButtonsStatus(seatBookedStatusArr);
    }

    class SeatSelectedButtonListener implements ActionListener {

        // Keeps track of the row and column of the seat button being listened
        private char rowChar;
        private int seatNum;

        // Constructs the action listener with row and col index of the seat.
        public SeatSelectedButtonListener(char rowChar, int seatNum) {
            this.rowChar = rowChar;
            this.seatNum = seatNum;
        }

        /**
         * Updates the seat selected whenever a seat button is clicked.
         *
         * @param e event where the user clicks an available seat button
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            setSelectedRowChar(rowChar);
            setSelectedSeatNumber(seatNum);
            displaySeatStatus();
            seatSelectionGUI.setSeatRowText(String.valueOf(rowChar));
            seatSelectionGUI.setSeatColText(String.valueOf(seatNum));
            JButton buttonClicked = (JButton) e.getSource();
            buttonClicked.setEnabled(false);
        }
    }

    class ProceedToPaymentListener implements ActionListener {
        /**
         * Once a seat is selected, proceed to ticket payment for the seat.
         * @param e event where the user clicks the button to proceed to payment
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedRowChar != '-' && selectedSeatNumber != 0) {
                // Notify seat selection
                theatreManager.setSeatSelected(selectedRowChar, selectedSeatNumber);

                // Call TicketPaymentController with the ticketId for the specified seat
                // TODO: add tasks to call the payment controller (call some method that begins payment on a ticket ID)
//                int ticketId = theatreManager.getTicketId();
                TicketPaymentController ticketPaymentController = new TicketPaymentController();
                ticketPaymentController.setTicketPayTicketGUI(new PayTicketGUI());
                seatSelectionGUI.close();
            }
            else
                seatSelectionGUI.displayMessage("Select a seat first!");
        }
    }

    // Getters and setter below
    public TheatreManager getTheatreManager() {
        return this.theatreManager;
    }

    public void setTheatreManager(TheatreManager theatreManager) {
        this.theatreManager = theatreManager;
    }

    public SeatSelectionGUI getSeatSelectionGUI() {
        return this.seatSelectionGUI;
    }

    public void setSeatSelectionGUI(SeatSelectionGUI seatSelectionGUI) {
        this.seatSelectionGUI = seatSelectionGUI;
    }

    public char getSelectedRowChar() {
        return selectedRowChar;
    }

    public void setSelectedRowChar(char selectedRowChar) {
        this.selectedRowChar = selectedRowChar;
    }

    public int getSelectedSeatNumber() {
        return selectedSeatNumber;
    }

    public boolean[][] getSeatBookedStatusArr() {
        return seatBookedStatusArr;
    }

    public void setSeatBookedStatusArr(boolean[][] seatBookedStatusArr) {
        this.seatBookedStatusArr = seatBookedStatusArr;
    }

    public void setSelectedSeatNumber(int selectedSeatNumber) {
        this.selectedSeatNumber = selectedSeatNumber;
    }
}
