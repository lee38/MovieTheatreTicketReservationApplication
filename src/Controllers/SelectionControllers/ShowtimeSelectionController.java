package Controllers.SelectionControllers;

import Boundaries.SelectionBoundaries.ShowtimeSelectionGUI;
import Boundaries.SelectionBoundaries.SeatSelectionGUI;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowtimeSelectionController {
    /**
     * Keeps track whether the user is a registered user.
     */
    private boolean isUserRegistered;

    /**
     * Used to retrieve available showtimes
     */
    private TheatreManager theatreManager;

    /**
     * Prompts for user showtime selection and displays available showtimes.
     */
    private ShowtimeSelectionGUI showtimeSelectionGUI;

    /**
     * List of available showtimes
     */
    private ArrayList<String> availableShowtimes;

    /**
     * Showtime selected
     */
    private String showtimeSelected;

    public ShowtimeSelectionController(boolean isUserRegistered) {
        setUserRegistered(isUserRegistered);
        setTheatreManager(null);
        setShowtimeSelectionGUI(null);
        setShowtimeSelected(null);
        setAvailableShowtimes(null);
    }

    /**
     * Add listeners to the showtime selection GUI.
     */
    public void addListeners() {
        showtimeSelectionGUI.addShowtimeResultSelectedListener(new ShowtimeResultSelectedListener());
        showtimeSelectionGUI.addProceedToSeatsButtonListener(new ProceedToSeatsListener());
    }

    /**
     * Adds available showtimes to showtime list in GUI.
     */
    public void displayAvailableShowtimes() {
        // Update showtime results list on GUI
        showtimeSelectionGUI.clearSearchResult();
        for (String showtime: availableShowtimes)
            showtimeSelectionGUI.addShowtimeSearchResult(showtime);
    }

    class ShowtimeResultSelectedListener implements ListSelectionListener {
        /**
         * Reads the selected showtime and sets the selected showtime field
         * @param e ListSelectionEvent where a user selects an item in the available showtimes list
         */
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                // Retrieve user input, assign the selected showtime, then display
                String showtimeSelectedString = showtimeSelectionGUI.getShowtimeResultSelected();
                if (isValidSelection(showtimeSelectedString)) {
                    showtimeSelected = showtimeSelectedString;
                    showtimeSelectionGUI.setSelectedShowtimeText(showtimeSelected);
                }
                else {
                    showtimeSelected = null;
                    showtimeSelectionGUI.setSelectedShowtimeText("");
                }
            }
        }

        /**
         * Helper method to validate if a user selected an available showtime
         * @param showtimeSelectedString user input for showtime selection
         * @return true if movie name is found in available movies; else false.
         */
        private boolean isValidSelection(String showtimeSelectedString) {
            for (String showtimeResult: availableShowtimes) {
                if (showtimeResult.equals(showtimeSelectedString))
                    return true;
            }
            return false;
        }
    }

    class ProceedToSeatsListener implements ActionListener {

        /**
         * Proceeds to seat selection once user has selected a valid seat
         * @param e event where user clicked the button to proceed to seat selection
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (showtimeSelected != null) {
                // Notify the showtime selected
                theatreManager.setShowtimeSelected(showtimeSelected);

                // Create Seat selection GUI
                SeatSelectionGUI seatSelectionGUI = new SeatSelectionGUI();
                seatSelectionGUI.setMovieText(showtimeSelectionGUI.getMovieSelectedText());
                seatSelectionGUI.setShowtimeText(showtimeSelected);

                // Create seat selection controller
                SeatSelectionController seatSelectionController = new SeatSelectionController();
                seatSelectionController.setSeatSelectionGUI(seatSelectionGUI);
                seatSelectionController.setTheatreManager(theatreManager);

                // Load available seats based on showtime selected
                seatSelectionController.setSeatBookedStatusArr(theatreManager.getSeatAvailability());
                seatSelectionController.addListeners();
                seatSelectionController.displaySeatStatus();
                showtimeSelectionGUI.close();
            }
            else
                showtimeSelectionGUI.displayMessage("Select a showtime first!");
        }
    }

    // Getters and setters below
    public boolean isUserRegistered() {
        return isUserRegistered;
    }

    public void setUserRegistered(boolean userRegistered) {
        isUserRegistered = userRegistered;
    }

    public TheatreManager getTheatreManager() {
        return theatreManager;
    }

    public void setTheatreManager(TheatreManager theatreManager) {
        this.theatreManager = theatreManager;
    }

    public ShowtimeSelectionGUI showtimeSelectionGUI() {
        return showtimeSelectionGUI;
    }

    public ShowtimeSelectionGUI getShowtimeSelectionGUI() {
        return showtimeSelectionGUI;
    }

    public void setShowtimeSelectionGUI(ShowtimeSelectionGUI showtimeSelectionGUI) {
        this.showtimeSelectionGUI = showtimeSelectionGUI;
    }

    public ArrayList<String> getAvailableShowtimes() {
        return availableShowtimes;
    }

    public void setAvailableShowtimes(ArrayList<String> availableShowtimes) {
        this.availableShowtimes = availableShowtimes;
    }

    public String getShowtimeSelected() {
        return showtimeSelected;
    }

    public void setShowtimeSelected(String showtime) {
        this.showtimeSelected = showtime;
    }
}
