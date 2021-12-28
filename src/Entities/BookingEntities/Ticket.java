package Entities.BookingEntities;

import Entities.PaymentEntities.Receipt;
import Entities.UserEntities.User;

import java.sql.Timestamp;
import java.util.Date;

public class Ticket {
    private int id;
    private boolean isSold;
    private Timestamp showtimeDate;
    private String movieName;
    private char seatRow;
    private int seatNumber;

    public Ticket(int id, boolean isSold, Timestamp showtimeDate, String movieName, char seatRow, int seatNumber) {
        this.id = id;
        this.isSold = isSold;
        this.showtimeDate = showtimeDate;
        this.movieName = movieName;
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public Date getShowtimeDate() {
        return showtimeDate;
    }

    public void setShowtimeDate(Timestamp showtimeDate) {
        this.showtimeDate = showtimeDate;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public char getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(char seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
