package com.bcefit.projet.service.watch;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchEpisode;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

import java.util.List;

public interface IWatchSeasonService {
    List<WatchEpisode> createWatchEpisodeBySeasonId(Long idTv, Long idSeason, UserAccount userAccount) throws InvalidEntityExeption;

    List<WatchEpisode> deleteWatchEpisodeBySeasonId(Long idTv, Long idSeason, UserAccount userAccount);
}
