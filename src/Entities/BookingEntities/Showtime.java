package Entities.BookingEntities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Showtime {
    private Timestamp date;
    private boolean isFull;
    private TheatreRoom theatreRoom;
    private boolean earlyRegistrationOpen;

    public Showtime(Timestamp date, TheatreRoom room) {
        this.date = date;
        this.isFull = false;
        this.theatreRoom = room;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public TheatreRoom getTheatreRoom() {
        return theatreRoom;
    }

    public void setTheatreRoom(TheatreRoom theatreRoom) {
        this.theatreRoom = theatreRoom;
    }

    public boolean isEarlyRegistrationOpen() {
        ArrayList<Seat> seats = theatreRoom.getSeats();
        int totalSeats = seats.size();

        int soldCounter = 0;
        for (int i = 0; i < totalSeats; i++) {
            if (seats.get(i).isBooked()) {
                soldCounter++;
            }
        }

        //TODO: Cast the sold percent to double
        double soldPercent = ((double) soldCounter) / totalSeats;
        if (soldPercent > 0.1) {
            earlyRegistrationOpen = false;
        }
        else {
            earlyRegistrationOpen = true;
        }

        return earlyRegistrationOpen;
    }

    public String toString() {
        return date.toString();
    }
}
