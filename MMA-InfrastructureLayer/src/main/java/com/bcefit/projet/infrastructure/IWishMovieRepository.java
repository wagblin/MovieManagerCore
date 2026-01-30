package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.wish.WishEpisode;
import com.bcefit.projet.domain.wish.WishMovie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface IWishMovieRepository extends CrudRepository<WishMovie , Long> {

    @Query("select w from WishMovie w where w.movie.idMovie = ?1 and w.userAccount = ?2")
    WishMovie findByIdMovieAndUserAccount(Long idMovie, UserAccount userAccount);

}
