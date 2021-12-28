package Entities.BookingEntities;

import java.util.ArrayList;
import java.util.Date;

public class Movie {
    private String name;
    private String description;
    private Date releaseDate;
    private ArrayList<Showtime> showtimes;
    private double ticketPrice;
    private boolean hasBeenPubliclyAnnounced;


    public Movie(String name, String description, Date releaseDate, ArrayList<Showtime> showtimes, double price) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.showtimes = showtimes;
        this.ticketPrice = price;
        this.hasBeenPubliclyAnnounced = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ArrayList<Showtime> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(ArrayList<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    public boolean hasBeenAnnouncedPublicly() {
        return hasBeenPubliclyAnnounced;
    }

    public String toString() {
        return name;
    }
}
