package com.bcefit.projet.domain.user;

import com.bcefit.projet.domain.moviedb.GenreMovie;
import com.bcefit.projet.domain.moviedb.GenreTv;
import com.bcefit.projet.domain.moviedb.StreamingSubscription;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    private String userName;

    private String email;

    private Integer birthYear;

    private boolean adultContent;

    private boolean enableAccount;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_account_genre_movie",
            joinColumns =  { @JoinColumn(name = "id_user") },
            inverseJoinColumns = { @JoinColumn(name = "id") })
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<GenreMovie> genreMovieSet= new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_account_genre_tv",
            joinColumns =  { @JoinColumn(name = "id_user") },
            inverseJoinColumns = { @JoinColumn(name = "id") })
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<GenreTv> genreTvSet= new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_account_streaming_subscription",
            joinColumns =  { @JoinColumn(name = "id_user") },
            inverseJoinColumns = { @JoinColumn(name = "id") })
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<StreamingSubscription> streamingSubscriptionSet= new HashSet<>();

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public UserAccount() {
    }

    public UserAccount(Long idUser, String userName, String email, Integer birthYear, boolean adultContent, boolean enableAccount, Set<GenreMovie> genreMovieSet, Set<GenreTv> genreTvSet, Set<StreamingSubscription> streamingSubscriptionSet) {
        this.idUser = idUser;
        this.userName = userName;
        this.email = email;
        this.birthYear = birthYear;
        this.adultContent = adultContent;
        this.enableAccount = enableAccount;
        this.genreMovieSet = genreMovieSet;
        this.genreTvSet = genreTvSet;
        this.streamingSubscriptionSet = streamingSubscriptionSet;
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

    public boolean isEnableAccount() {
        return enableAccount;
    }

    public void setEnableAccount(boolean enableAccount) {
        this.enableAccount = enableAccount;
    }

    public void setGenreMovieSet(Set<GenreMovie> genreMovieSet) {
        this.genreMovieSet = genreMovieSet;
    }

    public Set<GenreTv> getGenreTvSet() {
        return genreTvSet;
    }

    public void setGenreTvSet(Set<GenreTv> genreTvSet) {
        this.genreTvSet = genreTvSet;
    }

    public Set<GenreMovie> getGenreMovieSet() {
        return genreMovieSet;
    }

    public Set<StreamingSubscription> getStreamingSubscriptionSet() {
        return streamingSubscriptionSet;
    }

    public void setStreamingSubscriptionSet(Set<StreamingSubscription> streamingSubscriptionSet) {
        this.streamingSubscriptionSet = streamingSubscriptionSet;
    }

}

