package com.bcefit.projet.service.analytic;

import com.bcefit.projet.domain.analytic.TvRecommendationBlackListed;
import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

public interface ITvRecommendationBlackListedService {

    TvRecommendationBlackListed isBlackListedByTv(Tv tv);

    void createTvRecommandationBlackListed(TvRecommendationBlackListed tvRecommendationBlackListed)throws InvalidEntityExeption;

}
