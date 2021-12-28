package Boundaries.DatabaseBoundaries;

import Entities.PaymentEntities.CreditCard;
import Entities.PaymentEntities.DebitCard;

import java.sql.*;

public class DatabaseConnection {
    private static DatabaseConnection instance = null;
    private Connection conn;
    private ResultSet rs;

    private DatabaseConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/ticket_registration_system", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public ResultSet loadRegisteredUsers() {
        String query = "select * from reguser";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public ResultSet loadCardPayments(String userEmail) {
        String query = "select R.*, A.* from reguser as R, account as A where R.Email=A.RUEmail and RUEmail=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userEmail);

            rs = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public ResultSet loadVouchers(String userEmail) {
        String query = "select U.*, V.* from reguser as U, ruvoucher as R, voucher as V where U.Email=R.RUEmail and R.VoucherNumber=V.VoucherNumber and Email=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userEmail);

            rs = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public ResultSet loadTheatre() {
        String query = "select * from theatre";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public ResultSet loadMovies() {
        String query = "select * from movie";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public ResultSet loadShowtime(String movieName) {
        String query = "select * from showtime where MovieName=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, movieName);

            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public ResultSet loadTickets(String movieName, String showtime, int roomNumber) {
        String query = "select * from ticket where MovieName=? and ShowTime=? and RoomNumber=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, movieName);
            ps.setString(2, showtime);
            ps.setInt(3, roomNumber);

            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }
}
