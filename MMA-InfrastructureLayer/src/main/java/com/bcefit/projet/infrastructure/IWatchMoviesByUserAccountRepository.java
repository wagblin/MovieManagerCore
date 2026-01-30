package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchMovie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IWatchMoviesByUserAccountRepository extends CrudRepository<WatchMovie , UserAccount> {

    Optional<List<WatchMovie>> findWatchMoviesByUserAccount(UserAccount userAccount);
}
