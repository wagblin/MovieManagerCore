package com.bcefit.projet.domain.watch;

import com.bcefit.projet.domain.moviedb.Episode;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchContent;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;

@Entity
@Table(name = "watch_episode")
public class WatchEpisode extends WatchContent {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_episode")
    private Episode episode;

    public WatchEpisode() {

    }

    public WatchEpisode(Episode episode) {
        this.episode = episode;
    }

    public WatchEpisode(Long idWatch, UserAccount userAccount, Episode episode) {
        super(idWatch, userAccount);
        this.episode = episode;
    }

    public WatchEpisode(Long idWatch, UserAccount userAccount, LocalDate dateWatch, String viewingPlace, Integer viewingRate, Integer viewingMood, Duration duration, Episode episode) {
        super(idWatch, userAccount, dateWatch, viewingPlace, viewingRate, viewingMood, duration);
        this.episode = episode;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
}
