package com.bcefit.projet.service.analytic;

import com.bcefit.projet.domain.analytic.TvRecommendation;
import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

import java.util.List;

public interface ITvRecommendationService {

    void deleteTvRecommendation(Tv tv, UserAccount userAccount);

    TvRecommendation findTvRecommendationByIdTvAndUserAccount(Tv tv, UserAccount userAccount);

    void createTvRecommendation(Tv tv, UserAccount userAccount) throws InvalidEntityExeption;

    List<Tv> getTvRecommendationByUserAccount(UserAccount userAccount);

}
