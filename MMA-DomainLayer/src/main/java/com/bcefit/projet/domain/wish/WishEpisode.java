package com.bcefit.projet.domain.wish;

import com.bcefit.projet.domain.moviedb.Episode;
import com.bcefit.projet.domain.user.UserAccount;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "wish_episode")
public class WishEpisode extends WishContent{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_episode")
    private Episode episode;

    public WishEpisode(Long idWish, UserAccount userAccount, LocalDate dateWsih, Episode episode) {
        super(idWish, userAccount, dateWsih);
        this.episode = episode;
    }

    public WishEpisode(Long idWish, UserAccount userAccount, Episode episode) {
        super(idWish, userAccount);
        this.episode = episode;
    }

    public WishEpisode(Episode episode) {
        this.episode = episode;
    }

    public WishEpisode() {

    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
}
