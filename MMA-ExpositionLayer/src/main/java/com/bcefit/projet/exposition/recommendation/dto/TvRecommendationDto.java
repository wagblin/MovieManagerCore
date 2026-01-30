package com.bcefit.projet.exposition.recommendation.dto;

public class TvRecommendationDto {
    private Long idTv;
   private String originalName;
    private String posterPath;
    private int numberOfEpisodes;
    private int numberOfSeasons;

    private Float popularity;

    public TvRecommendationDto() {
    }

    public TvRecommendationDto(Long idTv, String originalName, String posterPath, int numberOfEpisodes, int numberOfSeasons, Float popularity) {
        this.idTv = idTv;
        this.originalName = originalName;
        this.posterPath = posterPath;
        this.numberOfEpisodes = numberOfEpisodes;
        this.numberOfSeasons = numberOfSeasons;
        this.popularity = popularity;
    }

    public Long getIdTv() {
        return idTv;
    }

    public void setIdTv(Long idTv) {
        this.idTv = idTv;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
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

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }
}
