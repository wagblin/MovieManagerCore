package com.bcefit.projet.service.analytic;

import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchEpisode;
import com.bcefit.projet.domain.watch.WatchMovie;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

public interface IAnalyticService {


    void addWatchMovieAnalytic(Movie movie, UserAccount userAccount)throws InvalidEntityExeption;

    void deleteWatchMovieAnalytic(Movie movie, UserAccount userAccount);

    void addWatchTvAnalytic(Tv tv, UserAccount userAccount)throws InvalidEntityExeption;

    void deleteWatchTvAnalytic(Tv tv, UserAccount userAccount);

    void addWishMovieAnalytic(Movie movie, UserAccount userAccount)throws InvalidEntityExeption;

    void deleteWishMovieAnalytic(Movie movie, UserAccount userAccount);

    void addWishTvAnalytic(Tv tv, UserAccount userAccount)throws InvalidEntityExeption;

    void deleteWishTvAnalytic(Tv tv, UserAccount userAccount);

    void initializeMovieRecommendation(UserAccount userAccount) throws InvalidEntityExeption;

    void initializeTvRecommendation(UserAccount userAccount) throws InvalidEntityExeption;
}
