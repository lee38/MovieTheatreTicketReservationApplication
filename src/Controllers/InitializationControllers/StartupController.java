package Controllers.InitializationControllers;

import Boundaries.UserBoundaries.MainMenuGUI;
import Controllers.DatabaseInterface.DatabaseController;
import Controllers.EmailControllers.EmailController;
import Controllers.SelectionControllers.TheatreManager;
import Controllers.TicketPaymentControllers.PaymentController;
import Controllers.TicketPaymentControllers.TicketCancellationController;
import Controllers.TicketPaymentControllers.TicketPaymentController;
import Controllers.UserControllers.UserManager;

public class StartupController {
    public StartupController() {
    }

    public void initTicketReservationSystem() {
        DatabaseController dbController = new DatabaseController();
        UserManager userManager = new UserManager(dbController);
        TheatreManager theatreManager = new TheatreManager(dbController);
        EmailController emailController = new EmailController();

        TicketPaymentController ticketPaymentController = new TicketPaymentController();
        ticketPaymentController.setTheatreManager(theatreManager);
        ticketPaymentController.setEmailController(emailController);
        ticketPaymentController.setUserManager(userManager);

        TicketCancellationController ticketCancellationController = new TicketCancellationController();
        ticketCancellationController.setEmailController(emailController);
        ticketCancellationController.setTheaterManager(theatreManager);
        ticketCancellationController.setUserManager(userManager);

        PaymentController paymentController = new PaymentController();
        paymentController.setEmailController(emailController);
        paymentController.setUserManager(userManager);


        MainMenuController mainMenuController = new MainMenuController(new MainMenuGUI(), theatreManager, userManager);
    }
}
