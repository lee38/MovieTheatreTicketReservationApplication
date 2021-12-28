package Controllers.DatabaseInterface;

import Boundaries.DatabaseBoundaries.DatabaseConnection;
import Entities.BookingEntities.Theatre;
import Entities.BookingEntities.Ticket;
import Entities.BookingEntities.Movie;
import Entities.BookingEntities.Showtime;
import Entities.BookingEntities.Seat;
import Entities.BookingEntities.TheatreRoom;
import Entities.PaymentEntities.Voucher;
import Entities.UserEntities.RegisteredUser;
import Entities.PaymentEntities.CardPayment;
import Entities.PaymentEntities.CreditCard;
import Entities.PaymentEntities.DebitCard;


import java.util.ArrayList;
import java.sql.*;

public class DatabaseController {
    private DatabaseConnection dbConnection;

    public DatabaseController() {
        dbConnection = DatabaseConnection.getInstance();
    }

    public ArrayList<RegisteredUser> loadRegisteredUsers() {
        // Should pull this from the database (dbConnection > method)
        ArrayList<RegisteredUser> users = new ArrayList<>();
        ResultSet reguserRS = dbConnection.loadRegisteredUsers();

        try {
            while(reguserRS.next()) {
                String userEmail = reguserRS.getString("Email");
                // get the accounts, vouchers, tickets associated with the current registered user
                ArrayList<CardPayment> accounts = loadCardPayments(userEmail);
                ArrayList<Voucher> vouchers = loadVouchers(userEmail);

                users.add(new RegisteredUser(reguserRS.getString("Fname"),
                                             reguserRS.getString("LName"),
                                             reguserRS.getString("Address"),
                                             reguserRS.getString("Email"),
                                             reguserRS.getString("Password"),
                                             accounts,
                                             vouchers,
                                             null));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

    public ArrayList<CardPayment> loadCardPayments(String userEmail) {
        ArrayList<CardPayment> accounts = new ArrayList<CardPayment>();

        ResultSet accountRS = dbConnection.loadCardPayments(userEmail);

        try {
            while(accountRS.next()) {
                String accountType = accountRS.getString("AccountType");

                if(accountType.equals("Credit")) {
                    accounts.add(new CreditCard(0.0,
                            accountRS.getString("AccountType"),
                            accountRS.getString("CardNumber"),
                            (accountRS.getString("FName") + " " + accountRS.getString("LName")),
                            accountRS.getString("Address"),
                            accountRS.getString("ExpDate"),
                            accountRS.getString("CVV")));
                } else if(accountType.equals("Debit")) {
                    accounts.add(new DebitCard(0.0,
                            accountRS.getString("AccountType"),
                            accountRS.getString("CardNumber"),
                            (accountRS.getString("FName") + " " + accountRS.getString("LName")),
                            accountRS.getString("Address"),
                            accountRS.getString("ExpDate"),
                            accountRS.getString("CVV")));
                }

                /*
                System.out.println(accountRS.getString("AccountType"));
                System.out.println(accountRS.getString("CardNumber"));
                System.out.println((accountRS.getString("FName") + accountRS.getString("LName")));
                System.out.println(accountRS.getString("Address"));
                System.out.println(accountRS.getString("ExpDate"));
                System.out.println(accountRS.getString("CVV"));
                */

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return accounts;
    }

    public ArrayList<Voucher> loadVouchers(String userEmail) {
        ArrayList<Voucher> vouchers = new ArrayList<Voucher>();

        ResultSet voucherRS = dbConnection.loadVouchers(userEmail);

        try {
            while(voucherRS.next()) {
                vouchers.add(new Voucher(voucherRS.getInt("VoucherNumber"),
                        voucherRS.getDouble("Amount"),
                        voucherRS.getDate("IssuedDate"),
                        voucherRS.getDate("ExpDate"),
                        (voucherRS.getString("ClaimedStatus").equals("Claimed") ? true : false)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return vouchers;
    }

    public ArrayList<Theatre> loadTheatres() {
        ArrayList<Theatre> theatres = new ArrayList<Theatre>();
        ResultSet theatreRS = dbConnection.loadTheatre();

        try {
            if(theatreRS.next()) {
                ArrayList<Movie> movies = loadMovies();

                theatres.add(new Theatre(theatreRS.getString("Name"),
                                         movies));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(theatres);

        return theatres;
    }

    public ArrayList<Movie> loadMovies() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        ResultSet movieRS = dbConnection.loadMovies();

        try {
            while(movieRS.next()) {
                String movieName = movieRS.getString("Name");

                ArrayList<Showtime> showtimes = loadShowtimes(movieName);

                movies.add(new Movie(movieRS.getString("Name"),
                                     movieRS.getString("Description"),
                                     movieRS.getDate("ReleaseDate"),
                                     showtimes,
                                     movieRS.getDouble("Price")
                                     ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(movies);

        return movies;
    }

    public ArrayList<Showtime> loadShowtimes(String movieName) {
        ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
        ResultSet showtimeRS = dbConnection.loadShowtime(movieName);

        try {
            while(showtimeRS.next()) {
                String showtime = showtimeRS.getString("Time");
                int roomNumber = showtimeRS.getInt("RoomNumber");

                TheatreRoom tr = createTheatreRoom(roomNumber);
                setSeatTickets(tr, dbConnection.loadTickets(movieName, showtime, roomNumber));

                showtimes.add(new Showtime(showtimeRS.getTimestamp("Time"), tr));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(showtimes);
        return showtimes;

    }

    public TheatreRoom createTheatreRoom(int roomNumber) {
        TheatreRoom tr = new TheatreRoom(createSeats());
        tr.setRoomNumber(roomNumber);
        return tr;
    }

    public ArrayList<Seat> createSeats() {
        ArrayList<Seat> seats = new ArrayList<Seat>();

        // create all 9 seats in a theatre room (row A-C, col 1-3)
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                seats.add(new Seat((char)('A'+i), j+1, null));
            }
        }

        return seats;
    }

    public void setSeatTickets(TheatreRoom tr, ResultSet ticketRS) {
        try {
            while(ticketRS.next()) {
                if(ticketRS.getString("Status").equals("Cancelled")) {
                    continue;
                }

                char rowCh = ticketRS.getString("SeatRow").charAt(0);
                int seatCol = ticketRS.getInt("SeatCol");

                Ticket ticket = new Ticket(ticketRS.getInt("TicketNumber"),
                                           true,
                                           ticketRS.getTimestamp("ShowTime"),
                                           ticketRS.getString("MovieName"),
                                           rowCh,
                                           seatCol);

                for(Seat s : tr.getSeats()) {
                    if(s.getRowCharacter() == rowCh && s.getSeatNumber() == seatCol) {
                        s.setTicket(ticket);
                        s.book();
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateVoucher(Voucher voucher) {
        // Call database method (dbConnection > method)
    }

    public void updateTicket(Ticket ticket) {
        // Call database method (dbConnection > method)
    }

    public static void main(String[] args) {
        DatabaseController theDBController = new DatabaseController();
        theDBController.loadRegisteredUsers();

        ArrayList<Theatre> theTheatres = theDBController.loadTheatres();

        for(Theatre t : theTheatres) {
            for(Movie m : t.getMovies()) {
                for(Showtime s : m.getShowtimes()) {
                    TheatreRoom tr = s.getTheatreRoom();
                    for (Seat seat : tr.getSeats()) {
                        if(seat.isBooked()) {
                            System.out.println(seat);
                        }
                        //System.out.println(seat);
                    }
                    System.out.println();
                }
            }
        }


    }

}
