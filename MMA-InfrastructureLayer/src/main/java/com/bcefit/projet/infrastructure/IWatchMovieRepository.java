package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchMovie;
import com.bcefit.projet.domain.wish.WishMovie;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;


public interface IWatchMovieRepository extends CrudRepository<WatchMovie, Long> {

    @Query("select w from WatchMovie w where w.movie.idMovie = ?1 and w.userAccount = ?2")
    WatchMovie findByIdMovieAndUserAccount(Long idMovie, UserAccount userAccount);

}
