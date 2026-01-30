package com.bcefit.projet.domain.wish;

import com.bcefit.projet.domain.user.UserAccount;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@MappedSuperclass
public abstract class WishContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_wish", nullable = false)
    private Long idWish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private UserAccount userAccount;

    private LocalDate dateWsih;

    public WishContent(Long idWish, UserAccount userAccount, LocalDate dateWsih) {
        this.idWish = idWish;
        this.userAccount = userAccount;
        this.dateWsih = dateWsih;
    }

    public WishContent(Long idWish, UserAccount userAccount) {
        this.idWish = idWish;
        this.userAccount = userAccount;
    }

    public WishContent() {

    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Long getIdWish() {
        return idWish;
    }

    public void setIdWish(Long idWish) {
        this.idWish = idWish;
    }

    public LocalDate getDateWsih() {
        return dateWsih;
    }

    public void setDateWsih(LocalDate dateWsih) {
        this.dateWsih = dateWsih;
    }
}
