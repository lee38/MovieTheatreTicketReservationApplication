package Boundaries.TicketPaymentBoundaries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CancelTicketRegisteredGUI {
    private JTextField ticketNumberTextField;
    private JTextArea messageTextArea;
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel ticketNumberPanel;
    private JLabel ticketNumberLabel;
    private JPanel messagePanel;
    private JButton redeemVoucherButton;
    private JPanel redeemVoucherPanel;
    private JFrame cancelTicketFrame;

    public CancelTicketRegisteredGUI() {
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

    public void setMessageTextArea(String text){
        messageTextArea.setText(text);
    }

    public JButton getRedeemVoucherButton(){
        return redeemVoucherButton;
    }

}