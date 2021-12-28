package Entities.PaymentEntities;

import java.util.Date;

public class CreditVoucher extends Payment {
    private String voucherId;
    private Date expirationDate;

    public CreditVoucher(double value, String paymentType, String voucherId, Date expirationDate) {
        this.value = value;
        this.paymentType = paymentType;
        this.voucherId = voucherId;
        this.expirationDate = expirationDate;
    }
}
