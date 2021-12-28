package Boundaries.SelectionBoundaries;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;

public class MovieTheatreSelectionGUI {
    // GUI-related components
    private JPanel bodyPanel;
    private JPanel searchMoviePanel;
    private JPanel searchMovieNamePanel;
    private JTextField searchMovieNameField;
    private JButton searchMovieButton;
    private JButton listAllMoviesButton;
    private JList<String> movieResultsList;
    private JScrollPane movieResultsScrollPane;
    private JPanel movieSelectedPanel;
    private JTextField selectedMovieField;
    private JPanel headerPanel;
    private JButton proceedToShowtimesButton;
    private JPanel proceedToShowtimePanel;
    private JPanel titlePanel;
    private JLabel titleLabel;

    private JPanel movieSelectionPanel;
    private JFrame movieSelectionFrame;

    /**
     * Constructs the GUI as a new JFrame.
     */
    public MovieTheatreSelectionGUI() {
        movieSelectionFrame = new JFrame("Movie Selection GUI");
        movieSelectionFrame.setContentPane(this.movieSelectionPanel);
        movieSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        movieSelectionFrame.pack();
        movieSelectionFrame.setVisible(true);
    }

    /**
     * Closes the GUI by destroying the associated frame.
     */
    public void close() {
        movieSelectionFrame.dispose();
    }

    /**
     * Add listener to the 'Main Menu' button.
     * @param listener ActionListener object to add to mainMenuButton
     */
    public void addSearchMovieButtonListener(ActionListener listener) {
        searchMovieButton.addActionListener(listener);
    }

    /**
     * Add listener to the 'List all movies' button
     * @param listener ActionListener object to add to listAllMoviesButton
     */
    public void addListAllMoviesListener(ActionListener listener) {
        listAllMoviesButton.addActionListener(listener);
    }

    /**
     * Add listener to the 'View Available Showtimes' button.
     * @param listener ActionListener object to add to proceedToShowtimesButton
     */
    public void addProceedToShowtimesButtonListener(ActionListener listener) {
        proceedToShowtimesButton.addActionListener(listener);
    }

    /**
     * Add listener to listen for user's movie result selection in the result list.
     * @param listener ListSelectionListener object to add to movie results JList
     */
    public void addMovieResultSelectedListener(ListSelectionListener listener) {
        ListSelectionModel listSelectionModel = movieResultsList.getSelectionModel();
        listSelectionModel.addListSelectionListener(listener);
    }

    /**
     * Getter method
     * @return reference to the list all movies button
     */
    public JButton getListAllMoviesButton() {
        return listAllMoviesButton;
    }

    /**
     * Retrieve text within movie name field
     * @return the movie name String input
     */
    public String getMovieNameText() {
        return searchMovieNameField.getText();
    }

    /**
     * Sets the selected movie field
     * @param movieName the movie name String to set
     */
    public void setSelectedMovieText(String movieName) {
        selectedMovieField.setText(movieName);
    }

    /**
     * Retrieve the currently selected movie.
     * @return String of movie info selected in the movie results list
     */
    public String getSearchResultSelected() {
        return movieResultsList.getSelectedValue();
    }

    /**
     * Clears items in the movie results list.
     */
    public void clearSearchResult() {
        movieResultsList.clearSelection();
        ((DefaultListModel<String>) movieResultsList.getModel()).clear();
    }

    /**
     * Appends an item to the movie search result list
     * @param movieResult line of text describing the movie result
     */
    public void addMovieSearchResult(String movieResult) {
        ((DefaultListModel<String>) movieResultsList.getModel()).addElement(movieResult);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(movieSelectionPanel, message);
    }


    public static void main(String[] args) {
        new MovieTheatreSelectionGUI();
    }
}
