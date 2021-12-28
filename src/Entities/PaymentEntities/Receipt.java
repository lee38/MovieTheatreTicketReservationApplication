package Entities.PaymentEntities;

import Entities.BookingEntities.Ticket;

import java.util.Date;

public class Receipt {
    private Ticket ticket;
    private Payment paymentInfo;

    public Receipt(Ticket ticket, Payment paymentInfo) {
        this.ticket = ticket;
        this.paymentInfo = paymentInfo;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Payment getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(Payment paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String toString() {
        String receiptString = "";

        Date currentDate = new Date();

        receiptString += "RECEIPT\n";
        receiptString += "-----------------------------\n";
        //receiptString += ("Movie Ticket\t\t" + ticket.getPrice() + "\n");
        receiptString += "-----------------------------\n";

        receiptString += ("\nTransaction Date: " + currentDate.toString() + "\n");
        receiptString += ("Payment Method: " + paymentInfo.getPaymentType() + "\n");

        // TODO: Print info about voucher or card number

        return receiptString;
    }
}
