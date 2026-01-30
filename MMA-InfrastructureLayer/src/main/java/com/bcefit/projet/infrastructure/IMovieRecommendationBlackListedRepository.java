package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.analytic.MovieRecommendationBlackListed;
import com.bcefit.projet.domain.moviedb.Movie;
import org.springframework.data.repository.CrudRepository;

public interface IMovieRecommendationBlackListedRepository extends CrudRepository<MovieRecommendationBlackListed, Movie> {
    MovieRecommendationBlackListed findByMovie(Movie movie);


}
