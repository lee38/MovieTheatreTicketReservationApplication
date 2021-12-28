package Entities.PaymentEntities;

import java.util.Date;

public class Voucher {
    private int voucherId;
    private double amount;
    private Date issuedDate;
    private Date expDate;
    private boolean claimedStatus;

    public Voucher(int voucherId, double amount, Date issuedDate, Date expDate, boolean claimedStatus) {
        this.voucherId = voucherId;
        this.amount = amount;
        this.issuedDate = issuedDate;
        this.expDate = expDate;
        this.claimedStatus = claimedStatus;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) { this.voucherId = voucherId; }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public boolean getClaimedStatus() {
        return claimedStatus;
    }

    public void setClaimedStatus(boolean claimedStatus) {
        this.claimedStatus = claimedStatus;
    }
}
