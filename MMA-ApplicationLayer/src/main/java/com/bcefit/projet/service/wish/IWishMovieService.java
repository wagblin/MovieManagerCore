package com.bcefit.projet.service.wish;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.wish.WishMovie;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

public interface IWishMovieService {

    Iterable<WishMovie> findAllByUserAccountId(UserAccount userAccount);

    WishMovie findById(Long id);

    WishMovie createWishMovie(WishMovie wishMovie)throws InvalidEntityExeption;

    void deleteWishMovie(WishMovie wishMovie);

    WishMovie findByIdMovieAndUserAccount(Long idMovie, UserAccount userAccount);


}
