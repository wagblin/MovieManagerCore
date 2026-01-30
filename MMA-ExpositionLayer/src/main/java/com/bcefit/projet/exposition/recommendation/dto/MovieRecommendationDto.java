package com.bcefit.projet.exposition.recommendation.dto;


public class MovieRecommendationDto {

    private Long idMovie;
    private String title;
    private String posterPath;

    private Float popularity;

    public MovieRecommendationDto() {
    }

    public MovieRecommendationDto(Long idMovie, String title, String posterPath, Float popularity) {
        this.idMovie = idMovie;
        this.title = title;
        this.posterPath = posterPath;
        this.popularity = popularity;
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

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }
}
