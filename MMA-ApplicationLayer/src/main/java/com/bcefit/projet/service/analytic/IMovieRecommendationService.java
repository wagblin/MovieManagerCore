package com.bcefit.projet.service.analytic;

import com.bcefit.projet.domain.analytic.MovieRecommendation;
import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

import java.util.List;

public interface IMovieRecommendationService {

    void deleteMovieRecommendation(Movie movie, UserAccount userAccount);

    MovieRecommendation findMovieRecommendationByIdMovieAndUserAccount(Movie movie, UserAccount userAccount);

    void createMovieRecommendation(Movie movie, UserAccount userAccount)throws InvalidEntityExeption;

    List<Movie> getMovieRecommendationByUserAccount(UserAccount userAccount);

}
