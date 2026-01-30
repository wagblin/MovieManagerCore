package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.moviedb.Movie;

/**
 * A Projection for the {@link com.bcefit.projet.domain.analytic.MovieRecommendation} entity
 */
public interface MovieRecommendationInfo {
    Movie getMovie();
}