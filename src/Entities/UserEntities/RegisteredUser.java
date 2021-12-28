package Entities.UserEntities;

import Entities.BookingEntities.Ticket;
import Entities.PaymentEntities.CardPayment;
import Entities.PaymentEntities.Voucher;

import java.util.ArrayList;

public class RegisteredUser extends Entities.UserEntities.User {
    private String fName;
    private String lName;
    private String address;
    private String email;
    private String password;
    private ArrayList<CardPayment> accounts;
    private boolean annualFeePaid;
    private ArrayList<Voucher> vouchers;
    private ArrayList<Ticket> tickets;

    public RegisteredUser(String fName, String lName, String address, String email, String password, ArrayList<CardPayment> accounts,
                          ArrayList<Voucher> vouchers, ArrayList<Ticket> tickets) {
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.email = email;
        this.password = password;
        this.accounts = accounts;
        this.annualFeePaid = false;
        this.vouchers = vouchers;
        this.tickets = tickets;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<CardPayment> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<CardPayment> accounts) {
        this.accounts = accounts;
    }

    public boolean isAnnualFeePaid() {
        return annualFeePaid;
    }

    public void setAnnualFeePaid(boolean annualFeePaid) {
        this.annualFeePaid = annualFeePaid;
    }

    public ArrayList<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(ArrayList<Voucher> vouchers) {
        this.vouchers = vouchers;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
}
