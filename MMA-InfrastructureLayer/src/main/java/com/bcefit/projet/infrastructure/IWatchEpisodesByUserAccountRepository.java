package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchEpisode;
import com.bcefit.projet.domain.wish.WishEpisode;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface IWatchEpisodesByUserAccountRepository extends CrudRepository<WatchEpisode , UserAccount> {

    Optional<List<WatchEpisode>> findWatchEpisodesByUserAccount(UserAccount userAccount);

}
