package com.bcefit.projet.exposition.user.dto;

import java.util.Set;

public class UserAccountDto {

    private String userName;

    private String email;

    private Integer birthYear;

    private boolean adultContent;


    Set<GenreMovieDto> genreMovieDtoSet;

    Set<GenreTvDto> genreTvDtoSet;

    Set<StreamingSubscriptionDto> streamingSubscriptionDtoSet;

    public UserAccountDto() {
    }

    public UserAccountDto(String userName, String email, Integer birthYear, boolean adultContent, Set<GenreMovieDto> genreMovieDtoSet, Set<GenreTvDto> genreTvDtoSet, Set<StreamingSubscriptionDto> streamingSubscriptionDtoSet) {
        this.userName = userName;
        this.email = email;
        this.birthYear = birthYear;
        this.adultContent = adultContent;
        this.genreMovieDtoSet = genreMovieDtoSet;
        this.genreTvDtoSet = genreTvDtoSet;
        this.streamingSubscriptionDtoSet = streamingSubscriptionDtoSet;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public boolean isAdultContent() {
        return adultContent;
    }

    public void setAdultContent(boolean adultContent) {
        this.adultContent = adultContent;
    }


    public Set<GenreMovieDto> getGenreMovieDtoSet() {
        return genreMovieDtoSet;
    }

    public void setGenreMovieDtoSet(Set<GenreMovieDto> genreMovieDtoSet) {
        this.genreMovieDtoSet = genreMovieDtoSet;
    }

    public Set<GenreTvDto> getGenreTvDtoSet() {
        return genreTvDtoSet;
    }

    public void setGenreTvDtoSet(Set<GenreTvDto> genreTvDtoSet) {
        this.genreTvDtoSet = genreTvDtoSet;
    }

    public Set<StreamingSubscriptionDto> getStreamingSubscriptionDtoSet() {
        return streamingSubscriptionDtoSet;
    }

    public void setStreamingSubscriptionDtoSet(Set<StreamingSubscriptionDto> streamingSubscriptionDtoSet) {
        this.streamingSubscriptionDtoSet = streamingSubscriptionDtoSet;
    }

}
