package Entities.EmailEntities;

import Entities.BookingEntities.Ticket;
import Entities.PaymentEntities.Receipt;

import java.util.Date;

public class ReservationConfirmation extends Email {

    public ReservationConfirmation(String emailAddress, Date sentDate, Ticket ticket, Receipt receipt) {
        this.emailAddress = emailAddress;
        this.sentDate = sentDate;
        createEmailTitle(emailAddress);
        createBody(ticket, receipt);
    }

    private void createEmailTitle(String emailAddress) {
        emailTitle = "Reservation confirmation for " + emailAddress;
    }

    private void createBody(Ticket ticket, Receipt receipt) {
        emailBody = "RESERVATION CONFIRMATION\n\n";
        addTicketInfo(ticket);
        emailBody += "\n";
        addReceiptInfo(receipt);
    }

    private void addTicketInfo(Ticket ticket) {
        return;
    }

    private void addReceiptInfo(Receipt receipt) {
        emailBody += receipt.toString();
    }
}
