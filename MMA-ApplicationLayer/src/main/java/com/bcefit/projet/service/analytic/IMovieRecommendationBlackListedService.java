package com.bcefit.projet.service.analytic;

import com.bcefit.projet.domain.analytic.MovieRecommendationBlackListed;
import com.bcefit.projet.domain.analytic.TvRecommendationBlackListed;
import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

public interface IMovieRecommendationBlackListedService {

    MovieRecommendationBlackListed isBlackListedByMovie(Movie movie);

    void createMovieRecommandationBlackListed(MovieRecommendationBlackListed movieRecommendationBlackListed)throws InvalidEntityExeption;

}
