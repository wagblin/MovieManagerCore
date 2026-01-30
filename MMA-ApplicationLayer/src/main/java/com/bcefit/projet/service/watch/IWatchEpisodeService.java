package com.bcefit.projet.service.watch;

import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchEpisode;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

public interface IWatchEpisodeService {

    Iterable<WatchEpisode> findAllByUserAccountId(UserAccount userAccount);

    WatchEpisode findById(Long id);


    void deleteWatchEpisode(WatchEpisode watchEpisode);

    WatchEpisode createWatchEpisode(WatchEpisode watchEpisode) throws InvalidEntityExeption;

    WatchEpisode getIdWatchEpisodeByIdSerieAndUserAccount(Long idEpisode, UserAccount userAccount);

    WatchEpisode findByIdEpisodeAndUserAccount(Long idEpisode, UserAccount userAccount);

}
