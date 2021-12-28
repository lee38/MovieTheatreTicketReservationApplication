package Entities.EmailEntities;

import Entities.BookingEntities.Movie;

import java.util.ArrayList;
import java.util.Date;

public class MovieNews extends Email {

    public MovieNews(String emailAddress, Date sentDate, String emailTitle, ArrayList<Movie> movieList) {
        this.emailAddress = emailAddress;
        this.sentDate = sentDate;
        createEmailTitle(emailAddress);
        addMovieListInfo(movieList);
    }

    private void createEmailTitle(String emailAddress) {
        emailTitle = "Movie news for " + emailAddress;
    }

    private void addMovieListInfo(ArrayList<Movie> movieList) {
        emailBody = "NEW RELEASES\n";
        emailBody += "-----------------------------\n";

        for (int i = 0; i < movieList.size(); i++) {
            Movie movie = movieList.get(i);

            emailBody += ("- " + movie.getName() + "\t" + movie.getReleaseDate());
        }
    }
}
