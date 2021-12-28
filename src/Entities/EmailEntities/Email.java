package Entities.EmailEntities;

import java.util.Date;

public abstract class Email {
    protected String emailAddress;
    protected Date sentDate;
    protected String emailTitle;
    protected String emailBody;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getEmailTitle() {
        return emailTitle;
    }

    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String toString() {
        String emailString = "";

        emailString += ("To: " + getEmailAddress() + "\n");
        emailString += ("Title: " + getEmailTitle() + "\n\n");
        emailString += "-----------------------------\n";
        emailString += getEmailBody();

        return emailString;
    }
}
