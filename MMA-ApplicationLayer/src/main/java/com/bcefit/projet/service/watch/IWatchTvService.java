package com.bcefit.projet.service.watch;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchEpisode;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

import java.util.List;
import java.util.Set;

public interface IWatchTvService {

    List<WatchEpisode> createWatchEpisodeByTvId(Long idTv, UserAccount userAccount) throws InvalidEntityExeption;

    List<WatchEpisode> deleteWatchEpisodeByTvId(Long idSeason, UserAccount userAccount);

    Set<Long> getIdTvWatchByUserAccount(UserAccount userAccount);
}
