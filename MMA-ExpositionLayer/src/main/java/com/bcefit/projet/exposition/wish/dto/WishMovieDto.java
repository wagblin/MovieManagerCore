package com.bcefit.projet.exposition.wish.dto;

import java.time.LocalDate;


public class WishMovieDto {

    private Long idWish;

    private LocalDate dateWish;

    private Long idMovie;



    public WishMovieDto() {
    }

    public WishMovieDto(Long idWish, LocalDate dateWish, Long idMovie) {
        this.idWish = idWish;
        this.dateWish = dateWish;
        this.idMovie = idMovie;
    }

    public Long getIdWish() {
        return idWish;
    }

    public void setIdWish(Long idWish) {
        this.idWish = idWish;
    }


    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    public LocalDate getDateWish() {
        return dateWish;
    }

    public void setDateWish(LocalDate dateWish) {
        this.dateWish = dateWish;
    }
}