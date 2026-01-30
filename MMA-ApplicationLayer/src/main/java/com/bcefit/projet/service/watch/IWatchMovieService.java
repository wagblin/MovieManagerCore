package com.bcefit.projet.service.watch;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchMovie;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

public interface IWatchMovieService {

    Iterable<WatchMovie> findAllByUserAccountId(UserAccount userAccount);

    WatchMovie findById(Long id);

    WatchMovie createWatchMovie(WatchMovie watchMovie) throws InvalidEntityExeption;

    void deleteWatchMovie(WatchMovie watchMovie);


    WatchMovie findByIdMovieAndUserAccount(Long idMovie, UserAccount userAccount);
}
