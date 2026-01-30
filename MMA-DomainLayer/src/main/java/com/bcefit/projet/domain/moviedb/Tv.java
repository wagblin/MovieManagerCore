package com.bcefit.projet.domain.moviedb;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tv")
public class Tv {
    @Id
    @Column(name = "id_tv", nullable = false)
    private Long idTv;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Integer> episodeRuntime;
    private String firstAirDate;
    private String lastAirDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "tv_genre_tv",
            joinColumns =  { @JoinColumn(name = "id_tv") },
            inverseJoinColumns = { @JoinColumn(name = "id") })
    private List<GenreTv> genreTvList= new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "tv_streaming_subscription",
            joinColumns =  { @JoinColumn(name = "id_tv") },
            inverseJoinColumns = { @JoinColumn(name = "id") })
    private List<StreamingSubscription> streamingSubscriptionList= new ArrayList<>();

    private String homepage;
    private String originalName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<String> originCountry;

    @Column(length = 5000)
    private String overview;
    private float popularity;
    private String backdropPath;
    private String posterPath;
    private int numberOfEpisodes;
    private int numberOfSeasons;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tv",insertable=false, updatable=false)
    private List<Season> seasons;

    private float voteAverage;
    private int voteCount;
    private String status;

    public Tv() {
    }

    public Tv(Long idTv, List<Integer> episodeRuntime, String firstAirDate, String lastAirDate, List<GenreTv> genreTvList, List<StreamingSubscription> streamingSubscriptionList, String homepage, String originalName, List<String> originCountry, String overview, float popularity, String backdropPath, String posterPath, int numberOfEpisodes, int numberOfSeasons, List<Season> seasons, float voteAverage, int voteCount, String status) {
        this.idTv = idTv;
        this.episodeRuntime = episodeRuntime;
        this.firstAirDate = firstAirDate;
        this.lastAirDate = lastAirDate;
        this.genreTvList = genreTvList;
        this.streamingSubscriptionList = streamingSubscriptionList;
        this.homepage = homepage;
        this.originalName = originalName;
        this.originCountry = originCountry;
        this.overview = overview;
        this.popularity = popularity;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.numberOfEpisodes = numberOfEpisodes;
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.status = status;
    }

    public Tv(List<Integer> episodeRuntime) {
        this.episodeRuntime = episodeRuntime;
    }

    public Long getIdTv() {
        return idTv;
    }

    public void setIdTv(Long idTv) {
        this.idTv = idTv;
    }

    public List<Integer> getEpisodeRuntime() {
        return episodeRuntime;
    }

    public void setEpisodeRuntime(List<Integer> episodeRuntime) {
        this.episodeRuntime = episodeRuntime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }


    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GenreTv> getGenreTvList() {
        return genreTvList;
    }

    public void setGenreTvList(List<GenreTv> genreTvList) {
        this.genreTvList = genreTvList;
    }

    public List<StreamingSubscription> getStreamingSubscriptionList() {
        return streamingSubscriptionList;
    }

    public void setStreamingSubscriptionList(List<StreamingSubscription> streamingSubscriptionList) {
        this.streamingSubscriptionList = streamingSubscriptionList;
    }
}
