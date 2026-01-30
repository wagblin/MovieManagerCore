package com.bcefit.projet.exposition.moviedb.dto;


import java.time.LocalDate;

public class MovieLightDto {

    private Long idMovie;
    private String title;
    private String posterPath;
    private Long idWatch;
    private String viewingPlace;
    // Note de 1 à 5
    private Integer viewingRate;
    // Nombre associé à des humeurs :
    // 1=Choqué, 2=Frustré, 3= Triste, 4=Songeur, 5=Emu, 6=Amusé, 7= Effrayé, 8=Las, 9=Compris, 10=Ravi, 11= Perdu, 12= Tendu
    private Integer viewingMood;
    private LocalDate dateWatch;

    private Long idWish;

    private LocalDate dateWish;

    public MovieLightDto() {
    }

    public MovieLightDto(Long idMovie, String title, String posterPath, Long idWatch, String viewingPlace, Integer viewingRate, Integer viewingMood, LocalDate dateWatch, Long idWish, LocalDate dateWish) {
        this.idMovie = idMovie;
        this.title = title;
        this.posterPath = posterPath;
        this.idWatch = idWatch;
        this.viewingPlace = viewingPlace;
        this.viewingRate = viewingRate;
        this.viewingMood = viewingMood;
        this.dateWatch = dateWatch;
        this.idWish = idWish;
        this.dateWish = dateWish;
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Long getIdWatch() {
        return idWatch;
    }

    public void setIdWatch(Long idWatch) {
        this.idWatch = idWatch;
    }

    public String getViewingPlace() {
        return viewingPlace;
    }

    public void setViewingPlace(String viewingPlace) {
        this.viewingPlace = viewingPlace;
    }

    public Integer getViewingRate() {
        return viewingRate;
    }

    public void setViewingRate(Integer viewingRate) {
        this.viewingRate = viewingRate;
    }

    public Integer getViewingMood() {
        return viewingMood;
    }

    public void setViewingMood(Integer viewingMood) {
        this.viewingMood = viewingMood;
    }

    public LocalDate getDateWatch() {
        return dateWatch;
    }

    public void setDateWatch(LocalDate dateWatch) {
        this.dateWatch = dateWatch;
    }

    public Long getIdWish() {
        return idWish;
    }

    public void setIdWish(Long idWish) {
        this.idWish = idWish;
    }

    public LocalDate getDateWish() {
        return dateWish;
    }

    public void setDateWish(LocalDate dateWish) {
        this.dateWish = dateWish;
    }
}
