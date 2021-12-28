package Boundaries.TicketPaymentBoundaries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PayTicketGUI {
    private JPanel mainPanel;
    private JPanel cardLayoutPanel;
    private JPanel creditPaymentCardPanel;
    private JPanel ticketPricePanel;
    private JLabel ticketPriceLabel;
    private JTextField ticketPriceTextField;
    private JPanel userFlowPanel;
    private JButton backButton;
    private JButton confirmButton;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField email;
    private JTextField creditCardNumber;
    private JTextField expiryDate;
    private JTextField CVV;
    private JLabel fNameLabel;
    private JLabel lNameLabel;
    private JLabel emailLabel;
    private JLabel creditCardLabel;
    private JLabel expLabel;
    private JLabel cvvLabel;
    private JPanel voucherPaymentCardPanel;
    private JRadioButton creditCardRadioButton;
    private JRadioButton voucherRadioButton;
    private JPanel paymentTypePanel;
    private JTextField voucherID;
    private JLabel voucherLabel;
    private JTextField billingAddress;
    private JLabel billingAddressLabel;
    private JTextField statusMessage;
    private JPanel statusMessagePanel;
    private JLabel statusMessageLabel;
    private CardLayout cardLayout;


    private JFrame paymentFrame;

    public PayTicketGUI(){
        paymentFrame = new JFrame("Ticket Cancellation GUI");
        paymentFrame.setContentPane(mainPanel);
        paymentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paymentFrame.pack();
        paymentFrame.setVisible(true);
        addListeners();
    }

    public void addListeners(){

    }

    //Define combobox and card layout
    private void createUIComponents() {
        //Setup Card Layout
        cardLayout = new CardLayout();
        cardLayoutPanel = new JPanel(cardLayout);
        cardLayoutPanel.add(creditPaymentCardPanel, "Credit Card");
        cardLayoutPanel.add(voucherPaymentCardPanel, "Voucher");
    }

    public void close() {
        paymentFrame.dispose();
    }



    //Listeners
    public void addBackButtonListener(ActionListener e){
        backButton.addActionListener(e);
    }

    public void addConfirmButtonListener(ActionListener e){
        confirmButton.addActionListener(e);
    }

    public void addCreditRadioButtonListener(ItemListener e){
        creditCardRadioButton.addItemListener(e);
    }

    public void addVoucherRadioButtonListener(ItemListener e){
        voucherRadioButton.addItemListener(e);
    }


    //Setters & Getters
    public String getFirstName(){
        return firstName.getText();
    }

    public String getLastName(){
        return lastName.getText();
    }

    public String getEmail(){
        return email.getText();
    }

    public String getCreditCardNumber(){
        return creditCardNumber.getText();
    }

    public String getStatusMessage(){
        return statusMessage.getText();
    }

    public void setStatusMessage(String message){
        statusMessage.setText(message);
    }

    public String getBillingAddress(){
        return billingAddress.getText();
    }


    public String getExpiry(){
        return expiryDate.getText();
    }

    public String getCVV(){
        return CVV.getText();
    }

    public String getVoucherID(){
        return voucherID.getText();
    }

    public void setVoucherID(String id){
        voucherID.setText(id);
    }

    public JButton getBackButton(){
        return backButton;
    }
    public JButton getConfirmButton(){ return confirmButton;}
}
