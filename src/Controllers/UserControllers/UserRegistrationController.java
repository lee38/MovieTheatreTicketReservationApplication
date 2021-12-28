package Controllers.UserControllers;

/**
 * NOT NECESSARY, NOT IMPLEMENTING REGISTRATION
 */

public class UserRegistrationController {
    UserManager userManager;
//    UserRegistrationFormGUI reigstrationView;

    public UserRegistrationController() {

    }

    public void createRegisteredUser(String fname, String lname, String address, String email, String password) {
        userManager.createRegisteredUser(fname, lname, address, email, password);
    }

//    public boolean validateUserInfo() {
//
//    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

//    public UserRegistrationFormGUI getReigstrationView() {
//        return reigstrationView;
//    }
//
//    public void setReigstrationView(UserRegistrationFormGUI reigstrationView) {
//        this.reigstrationView = reigstrationView;
//    }
}
