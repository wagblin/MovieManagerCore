package com.bcefit.projet.exposition.moviedb.dto;

import com.bcefit.projet.exposition.user.dto.GenreTvDto;
import com.bcefit.projet.exposition.user.dto.StreamingSubscriptionDto;

import java.util.ArrayList;
import java.util.List;

public class TvDetailDto {
    private Long idTv;
    private List<Integer> episodeRuntime;
    private String firstAirDate;
    private String lastAirDate;
    private List<GenreTvDto> genreTvDtoList= new ArrayList<>();
    private List<StreamingSubscriptionDto> streamingSubscriptionDtoList= new ArrayList<>();
    private String homepage;
    private String originalName;
    private List<String> originCountry;
    private String overview;
    private float popularity;
    private String backdropPath;
    private String posterPath;
    private int numberOfEpisodes;
    private int numberOfSeasons;
    private float voteAverage;
    private int voteCount;
    private String status;
    private Long idWatch;
    private Long idWish;
    private List<SeasonDto> seasonDtoList;

    public TvDetailDto() {
    }

    public TvDetailDto(Long idTv, List<Integer> episodeRuntime, String firstAirDate, String lastAirDate, List<GenreTvDto> genreTvDtoList, List<StreamingSubscriptionDto> streamingSubscriptionDtoList, String homepage, String originalName, List<String> originCountry, String overview, float popularity, String backdropPath, String posterPath, int numberOfEpisodes, int numberOfSeasons, float voteAverage, int voteCount, String status, Long idWatch, Long idWish, List<SeasonDto> seasonDtoList) {
        this.idTv = idTv;
        this.episodeRuntime = episodeRuntime;
        this.firstAirDate = firstAirDate;
        this.lastAirDate = lastAirDate;
        this.genreTvDtoList = genreTvDtoList;
        this.streamingSubscriptionDtoList = streamingSubscriptionDtoList;
        this.homepage = homepage;
        this.originalName = originalName;
        this.originCountry = originCountry;
        this.overview = overview;
        this.popularity = popularity;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.numberOfEpisodes = numberOfEpisodes;
        this.numberOfSeasons = numberOfSeasons;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.status = status;
        this.idWatch = idWatch;
        this.idWish = idWish;
        this.seasonDtoList = seasonDtoList;
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

    public List<GenreTvDto> getGenreTvDtoList() {
        return genreTvDtoList;
    }

    public void setGenreTvDtoList(List<GenreTvDto> genreTvDtoList) {
        this.genreTvDtoList = genreTvDtoList;
    }

    public List<StreamingSubscriptionDto> getStreamingSubscriptionDtoList() {
        return streamingSubscriptionDtoList;
    }

    public void setStreamingSubscriptionDtoList(List<StreamingSubscriptionDto> streamingSubscriptionDtoList) {
        this.streamingSubscriptionDtoList = streamingSubscriptionDtoList;
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

    public List<SeasonDto> getSeasonDtoList() {
        return seasonDtoList;
    }

    public void setSeasonDtoList(List<SeasonDto> seasonDtoList) {
        this.seasonDtoList = seasonDtoList;
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
