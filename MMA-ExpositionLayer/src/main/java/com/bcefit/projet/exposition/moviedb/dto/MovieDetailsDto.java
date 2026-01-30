package com.bcefit.projet.exposition.moviedb.dto;

import com.bcefit.projet.exposition.user.dto.GenreMovieDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieDetailsDto {

    private Long idMovie;
    private String title;
    private String originalTitle;
    private float popularity;
    private String backdropPath;
    private String posterPath;
    private String releaseDate;
    private boolean adult;
    private long budget;
    private String homepage;
    private String overview;
    private String imdbID;
    private String originalLanguage;
    private int runtime;
    private String tagline;
    private float voteAverage;
    private int voteCount;
    private String status;
    private List<GenreMovieDto> genreMovieDtoList= new ArrayList<>();
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

    public MovieDetailsDto() {
    }

    public MovieDetailsDto(Long idMovie, String title, String originalTitle, float popularity, String backdropPath, String posterPath, String releaseDate, boolean adult, long budget, String homepage, String overview, String imdbID, String originalLanguage, int runtime, String tagline, float voteAverage, int voteCount, String status, List<GenreMovieDto> genreMovieDtoList, Long idWatch, String viewingPlace, Integer viewingRate, Integer viewingMood, LocalDate dateWatch, Long idWish, LocalDate dateWish) {
        this.idMovie = idMovie;
        this.title = title;
        this.originalTitle = originalTitle;
        this.popularity = popularity;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.adult = adult;
        this.budget = budget;
        this.homepage = homepage;
        this.overview = overview;
        this.imdbID = imdbID;
        this.originalLanguage = originalLanguage;
        this.runtime = runtime;
        this.tagline = tagline;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.status = status;
        this.genreMovieDtoList = genreMovieDtoList;
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

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
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

    public List<GenreMovieDto> getGenreMovieDtoList() {
        return genreMovieDtoList;
    }

    public void setGenreMovieDtoList(List<GenreMovieDto> genreMovieDtoList) {
        this.genreMovieDtoList = genreMovieDtoList;
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
