package com.bcefit.projet.domain.moviedb;



import javax.persistence.*;

@Entity
@Table(name = "episode")
public class Episode {
    @Id
    @Column(name = "id_episode", nullable = false)
    private Long idEpisode;

    private int episodeNumber;

    @Column(length = 5000)
    private String overview;


    private String airDate;


    private int seriesId;


    private int seasonNumber;


    private String stillPath;



    private float voteAverage;


    private int voteCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_season")
    private Season season;



    public Episode() {
    }

    public Episode(Long idEpisode, int episodeNumber, String overview, String airDate, int seriesId, int seasonNumber, String stillPath, float voteAverage, int voteCount, Season season) {
        this.idEpisode = idEpisode;
        this.episodeNumber = episodeNumber;
        this.overview = overview;
        this.airDate = airDate;
        this.seriesId = seriesId;
        this.seasonNumber = seasonNumber;
        this.stillPath = stillPath;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.season = season;
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

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}
