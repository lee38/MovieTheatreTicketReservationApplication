package Entities.EmailEntities;

import Entities.BookingEntities.Ticket;
import Entities.PaymentEntities.Receipt;
import Entities.PaymentEntities.Voucher;

import java.util.Date;

public class CancellationConfirmation extends Email {

    public CancellationConfirmation(String emailAddress, Date sentDate, Ticket ticket, Voucher voucher, Receipt receipt) {
        this.emailAddress = emailAddress;
        this.sentDate = sentDate;
        createEmailTitle(emailAddress);
        createEmailBody(ticket, voucher, receipt);
    }

    private void createEmailTitle(String emailAddress) {
        emailTitle = "Cancellation confirmation for " + emailAddress;
    }

    private void createEmailBody(Ticket ticket, Voucher voucher, Receipt receipt) {
        emailBody = "CANCELLATION CONFIRMATION\n\n";
        addTicketInfo(ticket);
        addVoucherInfo(voucher);
        emailBody += "\n";
        addReceiptInfo(receipt);
    }

    public void addTicketInfo(Ticket ticket) {
        return;
    }


    public void addVoucherInfo(Voucher voucher) {
        return;
    }

    public void addReceiptInfo(Receipt receipt) {
        emailBody += receipt.toString();
    }



}
