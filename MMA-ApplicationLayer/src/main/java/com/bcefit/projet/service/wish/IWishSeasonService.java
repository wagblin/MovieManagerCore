package com.bcefit.projet.service.wish;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.wish.WishEpisode;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

import java.util.List;

public interface IWishSeasonService {

    List<WishEpisode> createWishEpisodeBySeasonId(Long idTv, Long idSeason, UserAccount userAccount)throws InvalidEntityExeption;

    List<WishEpisode> deleteWishEpisodeBySeasonId(Long idTv, Long idSeason, UserAccount userAccount);
}
