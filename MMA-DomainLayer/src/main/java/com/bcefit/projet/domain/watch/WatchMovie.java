package com.bcefit.projet.domain.watch;

import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.user.UserAccount;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;

@Entity
@Table(name = "watch_movie")
public class WatchMovie extends WatchContent {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_movie")
    private Movie movie;


    public WatchMovie() {
    }

    public WatchMovie(Movie movie) {
        this.movie = movie;
    }

    public WatchMovie(Long idWatch, UserAccount userAccount, Movie movie) {
        super(idWatch, userAccount);
        this.movie = movie;
    }

    public WatchMovie(Long idWatch, UserAccount userAccount, LocalDate dateWatch, String viewingPlace, Integer viewingRate, Integer viewingMood, Duration duration, Movie movie) {
        super(idWatch, userAccount, dateWatch, viewingPlace, viewingRate, viewingMood, duration);
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
