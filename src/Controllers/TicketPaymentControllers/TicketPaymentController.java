package Controllers.TicketPaymentControllers;

import Boundaries.TicketPaymentBoundaries.PayTicketGUI;
import Controllers.DatabaseInterface.DatabaseController;
import Controllers.EmailControllers.EmailController;
import Controllers.SelectionControllers.TheatreManager;
import Controllers.UserControllers.UserManager;
import Entities.BookingEntities.Ticket;
import Entities.UserEntities.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketPaymentController {
    private PayTicketGUI ticketPayTicketGUI;

    // Emails the receipt
    private EmailController emailController;

    // Updates the ticket list with the new ticket
    private TheatreManager theatreManager;

    private UserManager userManager;

    public TicketPaymentController() {

    }

//    public Ticket createTicket(char rowChar, int seatNum) {
////        Ticket ticket = theatreManager.createTicket(rowChar, seatNum);
//    }

    public void payTicket(Ticket ticket, User user) {
    }

    public EmailController getEmailController() {
        return emailController;
    }

    public TheatreManager getTheatreManager() {
        return theatreManager;
    }

    public void setTheatreManager(TheatreManager theatreManager) {
        this.theatreManager = theatreManager;
    }

    public void setEmailController(EmailController emailController) {
        this.emailController = emailController;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public PayTicketGUI getTicketPayTicketGUI() {
        return ticketPayTicketGUI;
    }

    public void setTicketPayTicketGUI(PayTicketGUI ticketPayTicketGUI) {
        this.ticketPayTicketGUI = ticketPayTicketGUI;
    }

    //    class backButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == payTicketGUI.getBackButton()) {
//                payTicketGUI.close();
//                //Go to select a seat GUI
//                SeatSelectionGUI seatSelectionGUI = new SeatSelectionGUI();
//            }
//        }
//    }
}
