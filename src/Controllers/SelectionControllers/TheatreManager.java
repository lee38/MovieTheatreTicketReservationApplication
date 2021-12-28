package Controllers.SelectionControllers;

import Controllers.DatabaseInterface.DatabaseController;
import Entities.BookingEntities.*;
import Entities.PaymentEntities.Voucher;

import java.util.ArrayList;

public class TheatreManager {
    /**
     * Stores list of theatres loaded from database
     */
    private ArrayList<Theatre> theatres;

    /**
     * Used to retrieve theatre and movie information and to update objects.
     */
    private DatabaseController dbController;

    /**
     * Theatre selected
     */
    private Theatre theatreSelected;

    /**
     * Movie selected
     */
    private Movie movieSelected;

    /**
     * Showtime selected
     */
    private Showtime showtimeSelected;

    /**
     * Seat selected
     */
    private Seat seatSelected;

    /**
     * List of all tickets.
     */
    private ArrayList<Ticket> allTickets;

    /**
     * List of all vouchers
     */
    private ArrayList<Voucher> allVouchers;

    /**
     * Maximum number of rows of seats.
     */
    private static final int ROW_COUNT = 3;

    /**
     * Maximum number of columns of seats
     */
    private static final int COL_COUNT = 3;

    public TheatreManager(DatabaseController dbController) {
        this.theatreSelected = null;
        this.movieSelected = null;
        this.showtimeSelected = null;
        this.seatSelected = null;
        setDbController(dbController);
        this.theatres = dbController.loadTheatres();
//        this.allVouchers = dbController.loadVouchers();
        this.allTickets = new ArrayList<Ticket>();
        pullAllTickets();
    }

    /**
     * Setter for theatre selected based on a string argument.
     * @param theatreName name of the theatre to select
     */
    public void setTheatreSelected(String theatreName) {
        this.theatreSelected = searchTheatres(theatreName);
    }

    /**
     * Setter for movie selected based on a string argument.
     * @param movieName name of the movie to select
     */
    public void setMovieSelected(String movieName) {
        this.movieSelected = searchMovies(movieName);
    }

    /**
     * Setter for showtime selected based on a string argument (should match the toString() of a Showtime)
     * @param showtimeInfo toString() of a Showtime to select
     */
    public void setShowtimeSelected(String showtimeInfo) {
        this.showtimeSelected = searchShowtimes(showtimeInfo);
    }

    /**
     * Setter for seat selected based on row character and seat number (column).
     * @param rowChar row char of the seat to select (e.g. 'A', 'B', 'C' capitalized)
     * @param seatNum seat number of the seat to select (e.g. 1, 2, 3 one-indexed)
     */
    public void setSeatSelected(char rowChar, int seatNum) {
        this.seatSelected = searchSeats(rowChar, seatNum);
    }

    /**
     * Helper method to search for a Theatre based on a string argument
     * @param name name of the theatre
     * @return matching Theatre object; else null
     */
    private Theatre searchTheatres(String name) {
        Theatre result = null;
        for (Theatre theatre: theatres) {
            if (theatre.getName().equalsIgnoreCase(name)) {
                result = theatre;
                break;
            }
        }
        return result;
    }

    /**
     * Helper method to search for a Movie based on a string argument
     * @param name name of the movie
     * @return matching Movie object; else null
     */
    private Movie searchMovies(String name) {
        if (theatreSelected == null)
            return null;

        for (Movie movie: theatreSelected.getMovies()) {
            if (movie.getName().equalsIgnoreCase(name))
                return movie;
        }
        return null;
    }

    /**
     * Helper method to search for a Showtime based on a string argument
     * @param showtimeInfo String representation of a showtime
     * @return matching Showtime object; else null
     */
    private Showtime searchShowtimes(String showtimeInfo) {
        if (movieSelected == null)
            return null;

        for (Showtime showtime: movieSelected.getShowtimes()) {
            if (showtime.toString().equalsIgnoreCase(showtimeInfo))
                return showtime;
        }
        return null;
    }

    /**
     * Helper method to search for a seat based on seat indices
     * @param rowChar row char of the seat to select (e.g. 'A', 'B', 'C' capitalized)
     * @param seatNum seat number of the seat to select (e.g. 1, 2, 3 one-indexed)
     * @return matching Showtime object; else null
     */
    private Seat searchSeats(char rowChar, int seatNum) {
        if (showtimeSelected == null)
            return null;

        for (Seat seat: showtimeSelected.getTheatreRoom().getSeats()) {
            if (seat.getRowCharacter() == rowChar && seat.getSeatNumber() == seatNum)
                return seat;
        }
        return null;
    }

    /**
     * Retrieves a list of available movies in the form of a list of movie names.
     *
     * @param includeUnannounced true if movies that haven't been publicly announced should be included
     * @return ArrayList of String movie names; else null if the theatre hasn't been selected first.
     */
    public ArrayList<String> getAvailableMoviesList(boolean includeUnannounced) {
        if (theatreSelected == null)
            return null;

        ArrayList<Movie> movies = theatreSelected.getMovies();

        return convertToMovieStrings(movies, includeUnannounced);
    }

    /**
     * Retrieves a list of available showtimes in the form of a list of showtime info strings.
     *
     * @param includeEarlyReg true will include showtimes that have early registration open
     * @return ArrayList of showtime info strings; else null if the movie hasn't been selected first.
     */
    public ArrayList<String> getAvailableShowtimes(boolean includeEarlyReg) {
        if (movieSelected == null)
            return null;

        ArrayList<Showtime> showtimes = movieSelected.getShowtimes();
        return convertToShowtimeStrings(showtimes, includeEarlyReg);
    }

    /**
     * Retrieves a 2D array of seat booking status' based on the selected showtime.
     *
     * @return 2D array of booleans where each corresponds to a seat index and seat column.
     */
    public boolean[][] getSeatAvailability() {
        if (showtimeSelected == null)
            return null;

        // Traverse each seat and assign if booked into seat status array
        boolean[][] seatBookedStatusArr = new boolean[ROW_COUNT][COL_COUNT];
        for (Seat seat: showtimeSelected.getTheatreRoom().getSeats()) {
            int rowIdx = convertRowCharToInt(seat.getRowCharacter());
            int colIdx = seat.getSeatNumber() - 1;  // '- 1' since seat number is one-indexed
            seatBookedStatusArr[rowIdx][colIdx] = seat.isBooked();
        }
        return seatBookedStatusArr;
    }

    /**
     * Helper method to map a character to integer ('A' = 0)
     * @param rowChar char value of the seat row (e.g. 'A', 'B', or 'C' capitalized)
     * @return integer value of seat row
     */
    private int convertRowCharToInt(char rowChar) {
        return rowChar - 'A';
    }

    /**
     * @param movies ArrayList of Movies to convert
     * @param includeUnannounced true if movies that haven't been publicly announced should be included
     * @return ArrayList of movie info Strings filtered depending on includeUnannounced flag
     */
    private ArrayList<String> convertToMovieStrings(ArrayList<Movie> movies, boolean includeUnannounced) {
        // Only append movies that have been announced publicly (no early releases)
        ArrayList<String> filteredMovies = new ArrayList<>();
        for (Movie movie: movies) {
            if (movie.hasBeenAnnouncedPublicly() || (!movie.hasBeenAnnouncedPublicly() && includeUnannounced))
                filteredMovies.add(movie.toString());
        }
        return filteredMovies;
    }

    /**
     * @param showtimes ArrayList of Showtimes to convert
     * @param includeEarlyReg true will include showtimes that have early registration open
     * @return ArrayList of showtime info Strings filtered depending on includeEarlyReg flag
     */
    private ArrayList<String> convertToShowtimeStrings(ArrayList<Showtime> showtimes, boolean includeEarlyReg) {
        ArrayList<String> filteredShowtimes = new ArrayList<>();
        // TODO: fix this
        for (Showtime showtime: showtimes) {
//            if (showtime.isEarlyRegistrationOpen() && includeEarlyReg)
//                filteredShowtimes.add(showtime.toString());
//            else if (!showtime.isFull() && !showtime.isEarlyRegistrationOpen())
                filteredShowtimes.add(showtime.toString());
        }
        return filteredShowtimes;
    }

    /**
     * Updates the status of the selected seat to booked.
     */
    public void bookSeat() {
        if (seatSelected != null)
            seatSelected.setBooked(true);
    }

    /**
     * @return ticket ID as an integer; else null if seat hasn't been selected yet
     */
    public int getTicketId() {
        if (seatSelected != null)
            return seatSelected.getTicket().getId();
        else
            return -1;
    }

    public void pullAllTickets() {
        for (int i = 0; i < theatres.size(); i++) {
            Theatre theatre = theatres.get(i);
            ArrayList<Movie> theatreMovies = theatre.getMovies();
            for (int j = 0; j < theatreMovies.size(); j++) {
                Movie movie = theatreMovies.get(i);
                ArrayList<Showtime> showtimes = movie.getShowtimes();
                for (int k = 0; k < showtimes.size(); k++) {
                    Showtime showtime = showtimes.get(i);
                    TheatreRoom theatreRoom = showtime.getTheatreRoom();
                    ArrayList<Seat> seats = theatreRoom.getSeats();
                    for (int l = 0; l < seats.size(); l++) {
                        Seat seat = seats.get(l);
                        Ticket ticket = seat.getTicket();
                        if (ticket != null)
                            allTickets.add(ticket);
                    }
                }
            }
        }
    }

    // Setters and getters below
    public ArrayList<Theatre> getTheatres() {
        return theatres;
    }

    public void setTheatres(ArrayList<Theatre> theatres) {
        this.theatres = theatres;
    }

    public DatabaseController getDbController() {
        return dbController;
    }

    public void setDbController(DatabaseController dbController) {
        this.dbController = dbController;
    }

    public static void main(String[] args) {

    }
}
