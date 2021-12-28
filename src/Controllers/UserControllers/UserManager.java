package Controllers.UserControllers;

import Controllers.DatabaseInterface.DatabaseController;
import Entities.BookingEntities.Ticket;
import Entities.PaymentEntities.CardPayment;
import Entities.PaymentEntities.Voucher;
import Entities.UserEntities.RegisteredUser;
import Entities.UserEntities.User;

import java.util.ArrayList;

public class UserManager {
    private ArrayList<RegisteredUser> registeredUserList;
    private DatabaseController dbController;
    private RegisteredUser currentUser;

    public UserManager(DatabaseController dbController) {
        this.dbController = dbController;
        registeredUserList = dbController.loadRegisteredUsers();
    }

    public void createRegisteredUser(String fName, String lName, String address, String email, String password) {
        RegisteredUser ru = new RegisteredUser(fName, lName, address, email, password, new ArrayList<CardPayment>(),
                new ArrayList<Voucher>(), new ArrayList<Ticket>());

        addNewRegisteredUser(ru);
    }

    public boolean login(String email, String password) {
        RegisteredUser ru = searchUser(email);

        if (ru != null) {
            if (ru.getPassword().equals(password)) {
                this.currentUser = ru;
                return true;
            }
        }

        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public void addNewRegisteredUser(RegisteredUser ru) {
        registeredUserList.add(ru);
        // dbController: update database
    }

    public RegisteredUser searchUser(String email) {
        RegisteredUser matchingUser = null;

        for (int i = 0; i < registeredUserList.size(); i++) {
            RegisteredUser ru = registeredUserList.get(i);

            if (ru.getEmail().equalsIgnoreCase(email)) {
                matchingUser = ru;
                break;
            }
        }

        return matchingUser;
    }

    public boolean isUserLoggedIn() {
        return currentUser == null;
    }

    public String getUserEmail() {
        if (currentUser != null)
            return currentUser.getEmail();
        else
            return null;
    }

    public void setLoggedInUser(RegisteredUser user) {
        currentUser = user;
    }

    public ArrayList<RegisteredUser> getRegisteredUserList() {
        return registeredUserList;
    }

    public void setRegisteredUserList(ArrayList<RegisteredUser> registeredUserList) {
        this.registeredUserList = registeredUserList;
    }

    public DatabaseController getDbController() {
        return dbController;
    }

    public void setDbController(DatabaseController dbController) {
        this.dbController = dbController;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(RegisteredUser currentUser) {
        this.currentUser = currentUser;
    }
}
