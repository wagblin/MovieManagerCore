package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.wish.WishEpisode;
import com.bcefit.projet.domain.wish.WishMovie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IWishEpisodesByUserAccountRepository extends CrudRepository<WishEpisode, UserAccount> {
    Optional<List<WishEpisode>> findWishEpisodesByUserAccount(UserAccount userAccount);


}
