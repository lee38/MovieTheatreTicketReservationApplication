package Boundaries.TicketPaymentBoundaries;

import Controllers.EmailControllers.EmailController;

import javax.swing.*;

public class CompletionStatusViewGUI {
    private JPanel mainPanel;
    private JTextPane messagePane;
    private EmailController emailController;

    private JFrame completionFrame;

    public CompletionStatusViewGUI(){
        completionFrame = new JFrame("Completion Status GUI");
        completionFrame.setContentPane(mainPanel);
        completionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        completionFrame.pack();
        completionFrame.setVisible(true);
    }







}
