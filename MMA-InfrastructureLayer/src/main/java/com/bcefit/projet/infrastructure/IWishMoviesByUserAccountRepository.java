package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.wish.WishMovie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IWishMoviesByUserAccountRepository extends CrudRepository<WishMovie, UserAccount> {

    Optional<List<WishMovie>> findWishMoviesByUserAccount(UserAccount userAccount);
}
