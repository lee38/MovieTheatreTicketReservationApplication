package Controllers.SelectionControllers;

import Boundaries.SelectionBoundaries.MovieTheatreSelectionGUI;
import Boundaries.SelectionBoundaries.ShowtimeSelectionGUI;
import Entities.BookingEntities.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

public class MovieTheatreSelectionController {
    /**
     * Keeps track whether the user is a registered user.
     */
    private boolean isUserRegistered;

    /**
     * Used to retrieve theatres and movies.
     */
    private TheatreManager theatreManager;

    /**
     * Prompts for user selections and displays search results.
     */
    private MovieTheatreSelectionGUI movieTheatreSelectionGUI;

    /**
     * List of available movies to select from.
     */
    private ArrayList<String> availableMovies;

    /**
     * Movie selected by the user.
     */
    private String movieSelected;

    public MovieTheatreSelectionController(boolean isUserRegistered) {
        setUserRegistered(isUserRegistered);
        setAvailableMovies(null);
        setMovieSelected(null);
        setAvailableMovies(null);
    }

    /**
     * Setter for theatre name and retrieving the available movies based on theatre name.
     * @param theatreName name of the theatre to retrieve available movies from
     */
    public void setTheatreAndAvailableMovies(String theatreName) {
        theatreManager.setTheatreSelected(theatreName);
        setAvailableMovies(theatreManager.getAvailableMoviesList(isUserRegistered));
    }

    /**
     * Add listeners for buttons/list in GUI.
     */
    public void addListeners() {
        movieTheatreSelectionGUI.addSearchMovieButtonListener(new SearchMovieListener());
        movieTheatreSelectionGUI.addListAllMoviesListener(new SearchMovieListener());
        movieTheatreSelectionGUI.addMovieResultSelectedListener(new SearchResultSelectedListener());
        movieTheatreSelectionGUI.addProceedToShowtimesButtonListener(new ProceedToShowtimesListener());
    }

    class SearchMovieListener implements ActionListener {
        /**
         * Reads user input for movie search, retrieves results, and display them to GUI result list.
         * @param e ActionEvent corresponding to a user pressing the search button or list all button
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String movieSearchParameter = movieTheatreSelectionGUI.getMovieNameText();

            if (e.getSource() == movieTheatreSelectionGUI.getListAllMoviesButton()) { // list all movies
                movieTheatreSelectionGUI.clearSearchResult();
                displayMovieResults(availableMovies);
            }
            else if (movieSearchParameter.equals("")) { // search field is empty
                movieTheatreSelectionGUI.displayMessage("Please enter a movie name first!");
            }
            else {
                movieTheatreSelectionGUI.clearSearchResult();
                // Determine a filtered result list, then display results
                ArrayList<String> matchingMovies = searchForMatchingMovies(movieSearchParameter);
                displayMovieResults(matchingMovies);
            }
        }

        /**
         * Helper method to search for all movies that match the user's search parameter.
         * @param movieSearchParameter a String to search for movie name matches
         * @return ArrayList of Movies that contain the search parameter string in their movie name
         */
        private ArrayList<String> searchForMatchingMovies(String movieSearchParameter) {
            // Search for movies that contain the movie search string and append them
            ArrayList<String> filteredMovies = new ArrayList<>();
            for (String movie: availableMovies) {
                if (movie.contains(movieSearchParameter))
                    filteredMovies.add(movie);
            }
            return filteredMovies;
        }

        /**
         * Helper method to display movie results
         * @param movieResults ArrayList of Movies to display their toString() method
         */
        private void displayMovieResults(ArrayList<String> movieResults) {
            for (String movieResult: movieResults)
                movieTheatreSelectionGUI.addMovieSearchResult(movieResult);
        }
    }

    class SearchResultSelectedListener implements ListSelectionListener {
        /**
         * Reads the selected movie option and sets the selected movie field
         * @param e ListSelectionEvent where a user selected an item in the movie result list
         */
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                // Retrieve user input, assign the selected movie, then display
                String movieSelectedString = movieTheatreSelectionGUI.getSearchResultSelected();
                if (isValidSelection(movieSelectedString)) {
                    movieSelected = movieSelectedString;
                    movieTheatreSelectionGUI.setSelectedMovieText(movieSelected);
                }
                else {
                    movieSelected = null;
                    movieTheatreSelectionGUI.setSelectedMovieText("");
                }
            }
        }

        /**
         * Helper method to validate if a user selected an available movie
         * @param movieSelectedString user input for movie selection
         * @return true if movie name is found in available movies; else false.
         */
        private boolean isValidSelection(String movieSelectedString) {
            for (String movieResult: availableMovies) {
                if (movieResult.equals(movieSelectedString))
                    return true;
            }
            return false;
        }
    }

    class ProceedToShowtimesListener implements ActionListener {
        /**
         * Proceeds to showtime selection once user has selected a valid movie
         * @param e event where user clicked the button to proceed to showtime selection
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (movieSelected != null) {
                // Notify the movie selected
                theatreManager.setMovieSelected(movieSelected);
                // Create Showtime selection GUI
                ShowtimeSelectionGUI showtimeSelectionGUI = new ShowtimeSelectionGUI();
                showtimeSelectionGUI.setSelectedMovieText(movieSelected);

                // Create showtime controller
                ShowtimeSelectionController showtimeController = new ShowtimeSelectionController(isUserRegistered);
                showtimeController.setShowtimeSelectionGUI(showtimeSelectionGUI);
                showtimeController.setTheatreManager(theatreManager);
                showtimeController.setAvailableShowtimes(theatreManager.getAvailableShowtimes(isUserRegistered));

                // Load available showtimes based on theatre and movie selection
                showtimeController.displayAvailableShowtimes();
                showtimeController.addListeners();
                movieTheatreSelectionGUI.close();
            }
            else
                movieTheatreSelectionGUI.displayMessage("Select a movie first!");
        }
    }

    // Getters and setter below
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

    public MovieTheatreSelectionGUI getMovieTheatreSelectionGUI() {
        return movieTheatreSelectionGUI;
    }

    public void setMovieTheatreSelectionGUI(MovieTheatreSelectionGUI theatreGUI) {
        this.movieTheatreSelectionGUI = theatreGUI;
    }

    public ArrayList<String> getAvailableMovies() {
        return availableMovies;
    }

    public void setAvailableMovies(ArrayList<String> availableMovies) {
        this.availableMovies = availableMovies;
    }

    public String getMovieSelected() {
        return movieSelected;
    }

    public void setMovieSelected(String movieSelected) {
        this.movieSelected = movieSelected;
    }

    public static void main(String[] args) {
        // For testing seats and rooms
        ArrayList<Showtime> testShowtimes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Showtime testShowtime = new Showtime(new Timestamp(i * 10000000), null);
            ArrayList<Seat> testSeats = new ArrayList<>();
            for (char j = 'A'; j <= 'C'; j++) {
                for (int k = 1; k <= 3; k++)
                    testSeats.add(new Seat(j, k, null));
            }
            testSeats.get(0).setBooked(true);

            TheatreRoom room = new TheatreRoom(testSeats);
            testShowtime.setTheatreRoom(room);
            testShowtimes.add(testShowtime);
        }

        // For testing movies and showtimes
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            movies.add(new Movie("A good movie #" + i, null, new Date(), testShowtimes, 13.50));
        }


        // Extra movie and showtimes
        ArrayList<Showtime> extraShowtimes = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            // Add an early announced showtime
            ArrayList<Seat> extraSeats = new ArrayList<>();
            for (char j = 'A'; j <= 'C'; j++) {
                for (int k = 1; k <= 3; k++)
                    extraSeats.add(new Seat(j, k, null));
            }

            for (int j = 0; j < i; j++) {
                extraSeats.get(j).setBooked(true);
                TheatreRoom extraRoom = new TheatreRoom(extraSeats);
                Showtime extraShowtime = new Showtime(new Timestamp(System.currentTimeMillis()), extraRoom);
                extraShowtimes.add(extraShowtime);
            }
        }

        Calendar futureCal = Calendar.getInstance();
        futureCal.setTime(new Date());
        futureCal.add(Calendar.DATE, 10); // 10 days from now
        Movie extraMovie = new Movie("An unannounced movie", null, futureCal.getTime(), extraShowtimes, 16.50);
        movies.add(extraMovie);

        Theatre theatre = new Theatre("theatre name", movies);
        ArrayList<Theatre> theatres = new ArrayList<>();
        theatres.add(theatre);

        TheatreManager theatreManager = new TheatreManager(null);
        theatreManager.setTheatres(theatres);

        MovieTheatreSelectionController movieSelectionController = new MovieTheatreSelectionController(true);
        movieSelectionController.setMovieTheatreSelectionGUI(new MovieTheatreSelectionGUI());
        movieSelectionController.setTheatreManager(theatreManager);
        movieSelectionController.setTheatreAndAvailableMovies("theatre name");
        movieSelectionController.addListeners();

        if (movieSelectionController.isUserRegistered()) {
            System.out.println("Debug: user is registered.");
        }
        else {
            System.out.println("Debug: user is not registered.");
        }
    }
}
