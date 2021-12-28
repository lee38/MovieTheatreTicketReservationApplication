package Boundaries.FinancialBoundaries;

import Entities.PaymentEntities.CardPayment;

import java.util.concurrent.TimeUnit;

public class FinancialInstituteConnection {

    public static void sendPayment(CardPayment paymentInfo) {
        try {
            TimeUnit.SECONDS.sleep(1);
        }
        catch(InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
