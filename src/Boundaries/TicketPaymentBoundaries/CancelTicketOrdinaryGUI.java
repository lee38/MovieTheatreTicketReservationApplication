package Boundaries.TicketPaymentBoundaries;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelTicketOrdinaryGUI {
    private JPanel mainPanel;
    private JPanel userInputPanel;
    private JTextField ticketNumberTextField;
    private JTextField emailTextField;
    private JLabel ticketlabel;
    private JLabel emailLabel;
    private JPanel textAreaPanel;
    private JTextArea returnTextArea;
    private JPanel redeemVoucherPanel;
    private JButton redeemVoucherButton;

    private JFrame cancelTicketFrame;

    public CancelTicketOrdinaryGUI() {
        cancelTicketFrame = new JFrame("Ticket Cancellation GUI");
        cancelTicketFrame.setContentPane(mainPanel);
        cancelTicketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cancelTicketFrame.pack();
        cancelTicketFrame.setVisible(true);
    }

    public void close() {
        cancelTicketFrame.dispose();
    }

    //Add Listeners
    public void addVoucherButtonListener(ActionListener e){
        redeemVoucherButton.addActionListener(e);
    }

    //Setters & Getters
    public String getTicketNumberTextField(){
        return ticketNumberTextField.getText();
    }

    public void setTicketNumberTextField(String text){
        ticketNumberTextField.setText(text);
    }

    public String getEmailTextField(){
        return emailTextField.getText();
    }

    public void setEmailTextField(String text){
        emailTextField.setText(text);
    }

    public void setReturnTextArea(String text){
        returnTextArea.setText(text);
    }

    public JButton getRedeemVoucherButton(){
        return redeemVoucherButton;
    }

}
