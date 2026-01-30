package com.bcefit.projet.service.wish;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.wish.WishEpisode;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

public interface IWishEpisodeService {

    Iterable<WishEpisode> findAllByUserAccountId(UserAccount userAccount);

    WishEpisode findById(Long id);


    void deleteWishEpisode(WishEpisode wishEpisode);

    WishEpisode createWishEpisode(WishEpisode wishEpisode)throws InvalidEntityExeption;

    WishEpisode getIdWishEpisodeByIdSerieAndUserAccount(Long idEpisode, UserAccount userAccount);

    WishEpisode findByIdEpisodeAndUserAccount(Long idEpisode, UserAccount userAccount);
}
