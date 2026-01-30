package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.analytic.MovieRecommendation;
import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.user.UserAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMovieRecommendationRepository extends CrudRepository<MovieRecommendation, Long> {
    @Query("select m from MovieRecommendation m where m.userAccount = ?1")
    List<MovieRecommendation> findByUserAccount(UserAccount userAccount);
    @Query("select m from MovieRecommendation m where m.userAccount = ?1 and m.movie = ?2")
    MovieRecommendation findByUserAccountAndMovie(UserAccount userAccount, Movie movie);
    long deleteByUserAccountAndMovie(UserAccount userAccount, Movie movie);
}
