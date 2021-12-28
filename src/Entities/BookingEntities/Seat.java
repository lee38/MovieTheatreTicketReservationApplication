package Entities.BookingEntities;

public class Seat {
    private boolean isBooked;
    private char rowCharacter;
    private int seatNumber;
    private Ticket ticket;

    public Seat(char rowCharacter, int seatNumber, Ticket ticket) {
        this.isBooked = false;
        this.rowCharacter = rowCharacter;
        this.seatNumber = seatNumber;
        this.ticket = ticket;
    }

    public void book() {
        isBooked = true;
    }

    public void unbook() {
        isBooked = false;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public char getRowCharacter() {
        return rowCharacter;
    }

    public void setRowCharacter(char rowCharacter) {
        this.rowCharacter = rowCharacter;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String toString() {
        return (rowCharacter + Integer.toString(seatNumber));
    }
}
