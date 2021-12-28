package Controllers.InitializationControllers;

import Boundaries.SelectionBoundaries.MovieTheatreSelectionGUI;
import Boundaries.UserBoundaries.LoginGUI;
import Boundaries.UserBoundaries.MainMenuGUI;
import Controllers.SelectionControllers.MovieTheatreSelectionController;
import Controllers.SelectionControllers.TheatreManager;
import Controllers.UserControllers.LoginLogoutController;
import Controllers.UserControllers.UserManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController {

    private boolean isUserRegistered;

    private MainMenuGUI mainMenuGUI;

    private TheatreManager theatreManager;

    private UserManager userManager;

    public MainMenuController(MainMenuGUI mainMenuGUI, TheatreManager theatreManager, UserManager userManager) {
        this.isUserRegistered = false;
        this.mainMenuGUI = mainMenuGUI;
        this.theatreManager = theatreManager;
        this.userManager = userManager;
        addListener();
    }

    public void addListener() {
        mainMenuGUI.addLoginRegisterButtonListener(new LoginButtonListener());
        mainMenuGUI.addSelectMovieButtonListener(new SelectMovieButtonListener());
        mainMenuGUI.addCancelTicketButtonListener(new CancelTicketButtonListener());
    }

    class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginLogoutController loginLogoutController = new LoginLogoutController();
            loginLogoutController.setUserManager(userManager);
            loginLogoutController.setLoginView(new LoginGUI());
        }
    }

    class SelectMovieButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MovieTheatreSelectionController movieTheatreSelectionController = new MovieTheatreSelectionController(isUserRegistered);
            movieTheatreSelectionController.setMovieTheatreSelectionGUI(new MovieTheatreSelectionGUI());
            movieTheatreSelectionController.setTheatreManager(theatreManager);
            movieTheatreSelectionController.setTheatreAndAvailableMovies("Cineplex Crowfoot");
            movieTheatreSelectionController.addListeners();
        }
    }

    class CancelTicketButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
