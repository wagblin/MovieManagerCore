package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.wish.WishEpisode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IWishEpisodeRepository extends CrudRepository<WishEpisode, Long> {
    @Query("select w from WishEpisode w where w.episode.idEpisode = ?1")
    WishEpisode findByEpisode_IdEpisode(Long idEpisode);

    @Query("select w from WishEpisode w where w.episode.idEpisode = ?1 and w.userAccount = ?2")
    WishEpisode findByIdEpisodeAndUserAccount(Long idEpisode, UserAccount userAccount);


}
