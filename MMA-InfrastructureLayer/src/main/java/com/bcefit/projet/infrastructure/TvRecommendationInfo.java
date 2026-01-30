package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.moviedb.Tv;

/**
 * A Projection for the {@link com.bcefit.projet.domain.analytic.TvRecommendation} entity
 */
public interface TvRecommendationInfo {
    Tv getTv();
}