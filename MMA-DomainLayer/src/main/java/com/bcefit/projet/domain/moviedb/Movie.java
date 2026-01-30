package com.bcefit.projet.domain.moviedb;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @Column(name = "id_movie", nullable = false)
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

    @Column(length = 5000)
    private String overview;

    private String imdbID;

    private String originalLanguage;

    private int runtime;

    private String tagline;


    private float voteAverage;

    private int voteCount;

    private String status;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "movie_genre_movie",
            joinColumns =  { @JoinColumn(name = "id_movie") },
            inverseJoinColumns = { @JoinColumn(name = "id") })
    private List<GenreMovie> genreMovieList= new ArrayList<>();

    public Movie() {
    }

    public Movie(Long idMovie, String title, String originalTitle, float popularity, String backdropPath, String posterPath, String releaseDate, boolean adult, long budget, String homepage, String overview, String imdbID, String originalLanguage, int runtime, String tagline, float voteAverage, int voteCount, String status, List<GenreMovie> genreMovieList) {
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
        this.genreMovieList = genreMovieList;
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

    public List<GenreMovie> getGenreMovieList() {
        return genreMovieList;
    }

    public void setGenreMovieList(List<GenreMovie> genreMovieList) {
        this.genreMovieList = genreMovieList;
    }
}
