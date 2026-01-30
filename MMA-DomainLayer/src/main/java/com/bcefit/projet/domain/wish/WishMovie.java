package com.bcefit.projet.domain.wish;

import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.user.UserAccount;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "wish_movie")
public class WishMovie extends WishContent{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_movie")
    private Movie movie;

    public WishMovie(Long idWish, UserAccount userAccount, LocalDate dateWsih, Movie movie) {
        super(idWish, userAccount, dateWsih);
        this.movie = movie;
    }

    public WishMovie(Long idWish, UserAccount userAccount, Movie movie) {
        super(idWish, userAccount);
        this.movie = movie;
    }

    public WishMovie(Movie movie) {
        this.movie = movie;
    }


    public WishMovie(Long idWish, UserAccount userAccount) {
        super(idWish, userAccount);
    }

    public WishMovie() {
    }



    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
