package Boundaries.EmailServerBoundaries;

import Entities.EmailEntities.Email;

import java.io.FileWriter;
import java.io.IOException;


public class EmailServerConnection {



    public EmailServerConnection() {

    }

    public void sendEmail(Email email) {
        try {
            String filename = ("mailto_" + email.getEmailAddress());
            FileWriter emailWriter = new FileWriter(filename);
            emailWriter.write(email.toString());
            emailWriter.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
