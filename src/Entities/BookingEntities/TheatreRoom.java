package Entities.BookingEntities;

import java.util.ArrayList;

public class TheatreRoom {
    private int roomNumber;
    private ArrayList<Seat> seats;

    public TheatreRoom(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeat(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public String toString() {
        return Integer.toString(roomNumber);
    }

}
