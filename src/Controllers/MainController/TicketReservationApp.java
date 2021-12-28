package Controllers.MainController;

import Controllers.InitializationControllers.StartupController;

public class TicketReservationApp {

    public TicketReservationApp() {
        StartupController startupController = new StartupController();
        startupController.initTicketReservationSystem();
    }

    public static void main(String[] args) {
        TicketReservationApp app = new TicketReservationApp();
    }
}
