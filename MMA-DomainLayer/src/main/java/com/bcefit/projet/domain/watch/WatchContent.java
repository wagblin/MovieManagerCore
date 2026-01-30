package com.bcefit.projet.domain.watch;

import com.bcefit.projet.domain.user.UserAccount;


import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;


@MappedSuperclass
public abstract class WatchContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_watch", nullable = false)
    private Long idWatch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private UserAccount userAccount;


    private LocalDate dateWatch;


    private String viewingPlace;

    // Note de 1 à 5
    private Integer viewingRate;

    // Nombre associé à des humeurs :
    // 1=Choqué, 2=Frustré, 3= Triste, 4=Songeur, 5=Emu, 6=Amusé, 7= Effrayé, 8=Las, 9=Compris, 10=Ravi, 11= Perdu, 12= Tendu
    private Integer viewingMood;


    public WatchContent() {

    }

    public WatchContent(Long idWatch, UserAccount userAccount) {
        this.idWatch = idWatch;
        this.userAccount = userAccount;
    }

    public WatchContent(Long idWatch, UserAccount userAccount, LocalDate dateWatch, String viewingPlace, Integer viewingRate, Integer viewingMood, Duration duration) {
        this.idWatch = idWatch;
        this.userAccount = userAccount;
        this.dateWatch = dateWatch;
        this.viewingPlace = viewingPlace;
        this.viewingRate = viewingRate;
        this.viewingMood = viewingMood;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
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


}

