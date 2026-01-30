package com.bcefit.projet.exposition.watch.dto;

public class WatchTvSeasonDto {

    private Integer idTv;

    private Integer idSeason;

    public WatchTvSeasonDto() {
    }

    public WatchTvSeasonDto(Integer idTv, Integer idSeason) {
        this.idTv = idTv;
        this.idSeason = idSeason;
    }

    public Integer getIdTv() {
        return idTv;
    }

    public void setIdTv(Integer idTv) {
        this.idTv = idTv;
    }

    public Integer getIdSeason() {
        return idSeason;
    }

    public void setIdSeason(Integer idSeason) {
        this.idSeason = idSeason;
    }
}
