package com.bcefit.projet.exposition.moviedb.dto;

import java.time.LocalDate;

public class EpisodeDto {

    private Long idEpisode;
    private int episodeNumber;
    private String overview;
    private String airDate;
    private int seriesId;
    private int seasonNumber;
    private String stillPath;
    private float voteAverage;
    private int voteCount;
    private Long idWatch;
    private LocalDate dateWatch;
    private String viewingPlace;
    // Note de 1 à 5
    private Integer viewingRate;
    // Nombre associé à des humeurs :
    // 1=Choqué, 2=Frustré, 3= Triste, 4=Songeur, 5=Emu, 6=Amusé, 7= Effrayé, 8=Las, 9=Compris, 10=Ravi, 11= Perdu, 12= Tendu
    private Integer viewingMood;
    private Long idWish;

    private LocalDate DateWish;

    public EpisodeDto() {
    }

    public EpisodeDto(Long idEpisode, int episodeNumber, String overview, String airDate, int seriesId, int seasonNumber, String stillPath, float voteAverage, int voteCount, Long idWatch, LocalDate dateWatch, String viewingPlace, Integer viewingRate, Integer viewingMood, Long idWish, LocalDate dateWish) {
        this.idEpisode = idEpisode;
        this.episodeNumber = episodeNumber;
        this.overview = overview;
        this.airDate = airDate;
        this.seriesId = seriesId;
        this.seasonNumber = seasonNumber;
        this.stillPath = stillPath;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.idWatch = idWatch;
        this.dateWatch = dateWatch;
        this.viewingPlace = viewingPlace;
        this.viewingRate = viewingRate;
        this.viewingMood = viewingMood;
        this.idWish = idWish;
        DateWish = dateWish;
    }

    public Long getIdEpisode() {
        return idEpisode;
    }

    public void setIdEpisode(Long idEpisode) {
        this.idEpisode = idEpisode;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getStillPath() {
        return stillPath;
    }

    public void setStillPath(String stillPath) {
        this.stillPath = stillPath;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public Long getIdWatch() {
        return idWatch;
    }

    public void setIdWatch(Long idWatch) {
        this.idWatch = idWatch;
    }

    public LocalDate getDateWatch() {
        return dateWatch;
    }

    public void setDateWatch(LocalDate dateWatch) {
        this.dateWatch = dateWatch;
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

    public Long getIdWish() {
        return idWish;
    }

    public void setIdWish(Long idWish) {
        this.idWish = idWish;
    }

    public LocalDate getDateWish() {
        return DateWish;
    }

    public void setDateWish(LocalDate dateWish) {
        DateWish = dateWish;
    }
}
