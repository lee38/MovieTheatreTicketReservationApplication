package Controllers.TicketPaymentControllers;

import Boundaries.TicketPaymentBoundaries.CancelTicketRegisteredGUI;
import Boundaries.TicketPaymentBoundaries.CancelTicketOrdinaryGUI;

import Controllers.EmailControllers.EmailController;
import Entities.PaymentEntities.Voucher;
import Controllers.UserControllers.UserManager;
import Controllers.SelectionControllers.TheatreManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Random;

import Boundaries.UserBoundaries.MainMenuGUI;

public class TicketCancellationController {
    private Voucher voucher;
    private CancelTicketOrdinaryGUI cancelTicketOrdinaryGUI;
    private CancelTicketRegisteredGUI cancelTicketRegisteredGUI;
    private EmailController emailController;
    private String ticketNumber;
    private String email;

    // Gets current User
    private UserManager userManager;

    // Validate that the ticket can be cancelled
    private TheatreManager theaterManager;

    final double admissionPrice = 12.5;
    final double administrationFee = 0.15;
    final static long DAY_IN_MILLISEC = 259200000;
    final static long YEAR_IN_MILLISEC = DAY_IN_MILLISEC * 365;

    public TicketCancellationController() {
        ticketNumber = null;
        email = null;
    }

    class VoucherButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cancelTicketOrdinaryGUI.getRedeemVoucherButton()){
                ticketNumber = cancelTicketOrdinaryGUI.getTicketNumberTextField();
                email = cancelTicketOrdinaryGUI.getEmailTextField();

                //Call Check Ticket to validate Voucher
                Boolean ticketCheck = checkTicket(ticketNumber, email);

                if(ticketCheck){
                    cancelTicketOrdinaryGUI.setReturnTextArea("Cancellation successful. Voucher has been emailed to: " + email);
                    voucher = convertTicketOrdinary(ticketNumber, email);
//                    sendVoucherEmail(ticketNumber, email);
                    //Close GUI
                    cancelTicketOrdinaryGUI.close();
                    //Start instance of MainMenuGUI
                    MainMenuGUI mainMenuGUI = new MainMenuGUI();
                }else{
                    cancelTicketOrdinaryGUI.setReturnTextArea("Cancellation is unsuccessful.  Please try again.");
                    cancelTicketOrdinaryGUI.setEmailTextField("");
                    cancelTicketOrdinaryGUI.setTicketNumberTextField("");
                }
            }

            if (e.getSource() == cancelTicketRegisteredGUI.getRedeemVoucherButton()){
                ticketNumber = cancelTicketRegisteredGUI.getTicketNumberTextField();

                //Call checkTicket() to validate Voucher
                boolean ticketCheck = checkTicket(ticketNumber);

                //Call searchUser() to validate registered user by email
                //*** Don't know how to track current registered user and access email address
                boolean userCheck = userManager.isUserLoggedIn();


                if(ticketCheck && userCheck){
                    //*** Don't know how to pull current registered user's email
                    cancelTicketRegisteredGUI.setMessageTextArea("Cancellation Successful. Voucher has been emailed to: " + userManager.getUserEmail());
                    voucher = convertTicketRegistered(ticketNumber);
                    sendVoucherEmail(ticketNumber);
                    //Close GUI
                    cancelTicketRegisteredGUI.close();
                    //Start instance of MainMenuGUI
                    MainMenuGUI mainMenuGUI = new MainMenuGUI();

                }else{
                    cancelTicketOrdinaryGUI.setReturnTextArea("Cancellation is unsuccessful.  Please try again.");
                    cancelTicketOrdinaryGUI.setEmailTextField("");
                    cancelTicketOrdinaryGUI.setTicketNumberTextField("");
                }
            }
        }
    }

    public Voucher convertTicketRegistered(String ticketNumber) {
        cancelTicket(ticketNumber);
        // *** Need int voucherId, double amount, String issuedDate, String expDate, boolean claimedStatus
        // voucherId = random integer? or query database for voucher arraylist and find next voucherId?
        // amount for registered users = 100% ticket value
        // issuedDate generate current timestamp
        // claimedStatus = false

        int voucherId = getVoucherId();
        double amount = getAmountRegistered();
        Date issuedDate = getCurrentDate();
        Date expDate = getExpiryDate(issuedDate);

        voucher = new Voucher(voucherId, amount, issuedDate, expDate, false);
        return voucher;
    }

    public Voucher convertTicketOrdinary(String ticketNumber, String email) {
        cancelTicket(ticketNumber, email);
        // *** Need int voucherId, double amount, String issuedDate, String expDate, boolean claimedStatus
        // voucherId = random integer? or query database for voucher arraylist and find next voucherId?
        // amount for ordinary users = 85% ticket value
        // issuedDate generate current timestamp
        // claimedStatus = false

        int voucherId = getVoucherId();
        double amount = getAmountOrdinary();
        Date issuedDate = getCurrentDate();
        Date expDate = getExpiryDate(issuedDate);

        voucher = new Voucher(voucherId, amount, issuedDate, expDate, false);
        return voucher;
    }

    public boolean checkTicket(String ticketId){
        // Check for ticketId in ticketId list
        return true;
    }

    public boolean checkTicket(String ticketId, String emailAddress) {
        // Check for ticketId in ticketId list
        return true;
    }

    public void cancelTicket(String ticketId) {
        //Tickets can only be cancelled 72 hours prior to the show
        //Compare current date with ticket showtimeDate - 72 hours

    }

    public void cancelTicket(String ticketId, String emailAddress) {
        //Tickets can only be cancelled 72 hours prior to the show
        //Compare current date with ticket showtimeDate - 72 hours
    }

    public int getVoucherId(){
        //Option 1: Random Integer
        Random rand = new Random();
        int max = 10000;
        int min = 1;
        int voucherId = rand.nextInt((max - min) + 1) + min;
        return voucherId;

        //Option 2: Query database for next VoucherId
    }

    public double getAmountOrdinary(){
        //Ordinary users receive 85% ticket value
        return admissionPrice*(1-administrationFee);
    }

    public double getAmountRegistered(){
        //Registered users receive 100% of ticket value
        return admissionPrice;
    }

    public Date getCurrentDate(){
        return new Date();
    }

    public Date getExpiryDate(Date issuedDate){
        //One year expiration date
        //Expiration date timestamp is one year more than issued date timestamp

        Date expiry = new Date();
        expiry.setTime(issuedDate.getTime() + YEAR_IN_MILLISEC);
        return expiry;
    }

    //Setters and Getters
    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }


    public void sendVoucherEmail(String ticketNumber){
//        emailController.sendCancellationConfirmationEmail(ticket);
    }


    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public TheatreManager getTheaterManager() {
        return theaterManager;
    }

    public void setTheaterManager(TheatreManager theaterManager) {
        this.theaterManager = theaterManager;
    }

    public EmailController getEmailController() {
        return emailController;
    }

    public void setEmailController(EmailController emailController) {
        this.emailController = emailController;
    }
}
