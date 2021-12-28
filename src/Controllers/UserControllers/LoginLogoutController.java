package Controllers.UserControllers;

import Entities.UserEntities.RegisteredUser;
import Controllers.UserControllers.UserManager;

import Boundaries.UserBoundaries.LoginGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginLogoutController {
    private UserManager userManager;
    private LoginGUI loginView;
    private String emailAddress;
    private String password;

    public LoginLogoutController() {
        emailAddress = null;
        password = null;
    }

    public void addListeners(){
        loginView.addLoginButtonListener(new LoginButtonListener());
    }

    class LoginButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginView.getLoginButton()){
                emailAddress = loginView.getEmailAddressField();
                password = loginView.getPasswordField();

                Boolean loginStatus = userManager.login(emailAddress, password);

                if(loginStatus){
                    loginView.setReturnTextArea("Login Successful");
                    //Close LOGIN GUI
                    loginView.close();
                    //Create MovieTheatreSelectionGUI

                    //Move to next screen.
//                    MovieTheatreSelectionGUI movieTheatreSelectionGUI = new MovieTheatreSelectionGUI();

                }else{
                    loginView.setReturnTextArea("Unsuccessful login attempt.  Please try again.");
                    loginView.setEmailAddress("");
                    loginView.setPasswordField("");
                }
            }
        }
    }

    public void logout() {
        userManager.logout();
    }

    public LoginGUI getLoginView() {
        return loginView;
    }

    public void setLoginGUI(LoginGUI loginGUI) {
        this.loginView = loginGUI;
    }


    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }


    public void setLoginView(LoginGUI loginView) {
        this.loginView = loginView;
    }
}
