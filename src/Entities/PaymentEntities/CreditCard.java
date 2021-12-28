package Entities.PaymentEntities;

import Entities.UserEntities.RegisteredUser;

import java.util.Date;

public class CreditCard extends CardPayment {
    private String expirationDate;
    private String cvv;

    public CreditCard(double value, String paymentType, String cardNumber, String cardOwnerName,
                      String billingAddress, String expirationDate, String cvv) {
        this.value = value;
        this.paymentType = paymentType;
        this.cardNumber = cardNumber;
        this.cardOwnerName = cardOwnerName;
        this.billingAddress = billingAddress;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }
}
