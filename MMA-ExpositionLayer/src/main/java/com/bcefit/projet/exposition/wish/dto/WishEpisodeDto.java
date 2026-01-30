package com.bcefit.projet.exposition.wish.dto;

import java.time.LocalDate;

public class WishEpisodeDto {

    private Long idWish;

    private LocalDate dateWish;


    private Long idEpisode;

    private Long idSeason;

    private int seasonNumber;

    private Long idTv;

    public WishEpisodeDto() {
    }

    public WishEpisodeDto(Long idWish, LocalDate dateWish, Long idEpisode, Long idSeason, int seasonNumber, Long idTv) {
        this.idWish = idWish;
        this.dateWish = dateWish;
        this.idEpisode = idEpisode;
        this.idSeason = idSeason;
        this.seasonNumber = seasonNumber;
        this.idTv = idTv;
    }

    public Long getIdWish() {
        return idWish;
    }

    public void setIdWish(Long idWish) {
        this.idWish = idWish;
    }

    public Long getIdEpisode() {
        return idEpisode;
    }

    public void setIdEpisode(Long idEpisode) {
        this.idEpisode = idEpisode;
    }

    public Long getIdSeason() {
        return idSeason;
    }

    public void setIdSeason(Long idSeason) {
        this.idSeason = idSeason;
    }


    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public LocalDate getDateWish() {
        return dateWish;
    }

    public void setDateWish(LocalDate dateWish) {
        this.dateWish = dateWish;
    }

    public Long getIdTv() {
        return idTv;
    }

    public void setIdTv(Long idTv) {
        this.idTv = idTv;
    }
}
