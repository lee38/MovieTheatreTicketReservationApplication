package Boundaries.UserBoundaries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI {
    private JTextField passwordField;
    private JTextField emailAddressField;
    private JTextField returnTextArea;
    private JPanel mainPanel;
    private JPanel loginCredentialPanel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JPanel messagePanel;
    private JPanel LoginPanel;
    private JFrame loginFrame;

    public LoginGUI() {
        loginFrame = new JFrame("LOGIN GUI");
        loginFrame.setContentPane(mainPanel);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setVisible(true);
    }

    public void close() {
        loginFrame.dispose();
    }

    //Add Listeners
    public void addLoginButtonListener(ActionListener e){
        loginButton.addActionListener(e);
    }


    //Setters & Getters
    public String getPasswordField() {
        return passwordField.getText();
    }

    public void setPasswordField(String text){
        passwordField.setText(text);
    }

    public String getEmailAddressField() {
        return emailAddressField.getText();
    }

    public void setEmailAddress(String text){
        emailAddressField.setText(text);
    }

    public void setReturnTextArea(String status) {
        returnTextArea.setText(status);
    }

    public JButton getLoginButton(){
        return loginButton;
    }
}
                