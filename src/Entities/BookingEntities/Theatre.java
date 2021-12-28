package Entities.BookingEntities;

import java.util.ArrayList;

public class Theatre {
    private String name;
    private ArrayList<Movie> movies;

    public Theatre(String name, ArrayList<Movie> movies) {
        this.name = name;
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public String toString() {
        return name;
    }
}
