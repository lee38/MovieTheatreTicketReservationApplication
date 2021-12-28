package Boundaries.UserBoundaries;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI {
    private JPanel mainMenuPanel;
    private JButton selectMovieButton;
    private JButton cancelTicketButton;
    private JButton loginRegisterButton;

    private JFrame mainMenuFrame;

    /**
     * Constructs the GUI as a new JFrame.
     */
    public MainMenuGUI() {
        mainMenuFrame = new JFrame("Movie Selection GUI");
        mainMenuFrame.setContentPane(this.mainMenuPanel);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.pack();
        mainMenuFrame.setVisible(true);
    }

    public void close() {
        mainMenuFrame.setVisible(false);
    }

    public void addSelectMovieButtonListener(ActionListener listener) {
        selectMovieButton.addActionListener(listener);
    }

    public void addCancelTicketButtonListener(ActionListener listener) {
        cancelTicketButton.addActionListener(listener);
    }

    public void addLoginRegisterButtonListener(ActionListener listener) {
        loginRegisterButton.addActionListener(listener);
    }

    public static void main(String[] args) {
        new MainMenuGUI();
    }
}
