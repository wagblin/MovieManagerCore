package com.bcefit.projet.exposition.moviedb.dto;

public class TvLightDto {
    private Long idTv;
   private String originalName;
    private String posterPath;
    private int numberOfEpisodes;
    private int numberOfSeasons;

    private Long idWatch;
    private Long idWish;

    public TvLightDto() {
    }

    public TvLightDto(Long idTv, String originalName, String posterPath, int numberOfEpisodes, int numberOfSeasons, Long idWatch, Long idWish) {
        this.idTv = idTv;
        this.originalName = originalName;
        this.posterPath = posterPath;
        this.numberOfEpisodes = numberOfEpisodes;
        this.numberOfSeasons = numberOfSeasons;
        this.idWatch = idWatch;
        this.idWish = idWish;
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
