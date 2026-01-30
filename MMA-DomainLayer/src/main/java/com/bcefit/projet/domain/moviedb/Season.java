package com.bcefit.projet.domain.moviedb;


import javax.persistence.*;
import java.util.List;

@Entity
public class Season {
    @Id
    @Column(name = "id_season", nullable = false)
    private Long idSeason;

    private String airDate;


    private String posterPath;


    private int seasonNumber;

    @Column(length = 5000)
    private String overview;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tv")
    private Tv tv;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_season",insertable=false, updatable=false)
    private List<Episode> episodeList;

    public Season() {
    }

    public Season(Long idSeason, String airDate, String posterPath, int seasonNumber, String overview, Tv tv) {
        this.idSeason = idSeason;
        this.airDate = airDate;
        this.posterPath = posterPath;
        this.seasonNumber = seasonNumber;
        this.overview = overview;
        this.tv = tv;
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

    public Tv getTv() {
        return tv;
    }

    public void setTv(Tv tv) {
        this.tv = tv;
    }

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }
}
