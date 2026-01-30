package com.bcefit.projet.exposition.moviedb.dto;

import java.util.List;

public class SeasonDto {
    private Long idSeason;
    private String airDate;
    private String posterPath;
    private int seasonNumber;
    private String overview;
    private Long idWatch;
    private Long idWish;
    private List<EpisodeDto> episodeDtoList;

    public SeasonDto() {
    }

    public SeasonDto(Long idSeason, String airDate, String posterPath, int seasonNumber, String overview, Long idWatch, Long idWish, List<EpisodeDto> episodeDtoList) {
        this.idSeason = idSeason;
        this.airDate = airDate;
        this.posterPath = posterPath;
        this.seasonNumber = seasonNumber;
        this.overview = overview;
        this.idWatch = idWatch;
        this.idWish = idWish;
        this.episodeDtoList = episodeDtoList;
    }

    public Long getIdSeason() {
        return idSeason;
    }

    public void setIdSeason(Long idSeason) {
        this.idSeason = idSeason;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<EpisodeDto> getEpisodeDtoList() {
        return episodeDtoList;
    }

    public void setEpisodeDtoList(List<EpisodeDto> episodeDtoList) {
        this.episodeDtoList = episodeDtoList;
    }

    public Long getIdWatch() {
        return idWatch;
    }

    public void setIdWatch(Long idWatch) {
        this.idWatch = idWatch;
    }

    public Long getIdWish() {
        return idWish;
    }

    public void setIdWish(Long idWish) {
        this.idWish = idWish;
    }

}
