package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.analytic.TvRecommendationBlackListed;
import com.bcefit.projet.domain.moviedb.Tv;
import org.springframework.data.repository.CrudRepository;

public interface ITvRecommendationBlackListedRepository extends CrudRepository<TvRecommendationBlackListed, Tv> {
    TvRecommendationBlackListed findByTv(Tv tv);

}
