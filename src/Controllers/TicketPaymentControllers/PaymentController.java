package Controllers.TicketPaymentControllers;

import Boundaries.FinancialBoundaries.FinancialInstituteConnection;
import Boundaries.TicketPaymentBoundaries.CompletionStatusViewGUI;
import Boundaries.TicketPaymentBoundaries.PayTicketGUI;
import Controllers.EmailControllers.EmailController;

import Controllers.UserControllers.UserManager;
import Entities.BookingEntities.Ticket;
import Entities.PaymentEntities.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PaymentController {
    private PayTicketGUI payTicketGUI;
    private String paymentType = "";
    private CardPayment cardInfo;
    final double admissionPrice = 12.5;

    private EmailController emailController;

    private UserManager userManager;

    class confirmButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == payTicketGUI.getConfirmButton()) {
                if (paymentType.equals("Credit Card")) {
                    CreditCard paymentInfo = generatePaymentInfo();
                    sendToFinancialInstitution(paymentInfo);

                    //Do we send a ticket or a receipt (toString)?
//                    sendConfirmation(ticket);

                    payTicketGUI.close();
                    CompletionStatusViewGUI completionStatusView = new CompletionStatusViewGUI();
                }

                if (paymentType.equals("Voucher")) {
                    Boolean voucherCheck = checkValidVoucher(payTicketGUI.getVoucherID());
                    if (voucherCheck) {
                        //Cancel Ticket

                        //Cancel Voucher


                        //Do we send a ticket or a receipt?
//                        sendConfirmation(ticket);

                        payTicketGUI.close();
                        CompletionStatusViewGUI completionStatusView = new CompletionStatusViewGUI();
                    } else {
                        payTicketGUI.setVoucherID("");
                        payTicketGUI.setStatusMessage("Invalid voucher. Please try again.");
                    }

                }
            }
        }
    }

    class creditCardRadioButtonListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            paymentType = "Credit Card";

//            CardLayout cl = (CardLayout)(cardLayoutPanel.getLayout());
//            cl.show(cardLayoutPanel, (String)e.getItem());
            }
        }


    class voucherRadioButtonListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            paymentType = "Voucher";

//            CardLayout cl = (CardLayout) (cardLayoutPanel.getLayout());
//            cl.show(cardLayoutPanel, (String) e.getItem());
        }
    }


    public CreditCard generatePaymentInfo(){
        String firstName = payTicketGUI.getFirstName();
        String lastName = payTicketGUI.getLastName();
        String creditCardNumber = payTicketGUI.getCreditCardNumber();
        String expiryDate = payTicketGUI.getExpiry();
        String CVV = payTicketGUI.getCVV();
        String billingAddress = payTicketGUI.getBillingAddress();

        String cardOwnerName = firstName + " " + lastName;

        CreditCard paymentInfo = new CreditCard(admissionPrice, paymentType, creditCardNumber, cardOwnerName, billingAddress, expiryDate, CVV);
        return paymentInfo;
    }


    public boolean checkValidVoucher(String voucherID) {
        //Check from voucher list
        return true;
    }

    public void cancelVoucher(){
        //Remove voucher from voucher list
    }

    public void cancelTicket(){
        //Remove ticket from ticket list
    }

    public Receipt payTicket(Payment paymentInfo, Ticket ticketInfo) {
        //Generate receipt from paymentInfo and ticketInfo
        Receipt receipt = new Receipt(ticketInfo, paymentInfo);
        return receipt;
    }

//    public void payAccountFee(Payment paymentInfo, RegisteredUser user) {
//        //
//    }

//    public Voucher createVoucher(double value, String expirationDate) { }

    public void sendToFinancialInstitution(CreditCard paymentInfo) {
        FinancialInstituteConnection.sendPayment(paymentInfo);
    }

    public void sendConfirmation(Ticket ticket){
        //Need ticket info
        emailController.sendReservationConfirmationEmail(ticket);
    }

    public EmailController getEmailController() {
        return emailController;
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
}

