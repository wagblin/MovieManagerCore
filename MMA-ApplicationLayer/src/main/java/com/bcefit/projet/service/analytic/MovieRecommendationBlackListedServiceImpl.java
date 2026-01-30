package com.bcefit.projet.service.analytic;

import com.bcefit.projet.domain.analytic.MovieRecommendation;
import com.bcefit.projet.domain.analytic.MovieRecommendationBlackListed;
import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.infrastructure.IMovieRecommendationBlackListedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MovieRecommendationBlackListedServiceImpl implements IMovieRecommendationBlackListedService{

    @Autowired
    IMovieRecommendationBlackListedRepository iMovieRecommendationBlackListedRepository;
    @Autowired
    IMovieRecommendationService iMovieRecommendationService;

    @Override
    public MovieRecommendationBlackListed isBlackListedByMovie(Movie movie) {
        return iMovieRecommendationBlackListedRepository.findByMovie(movie);
    }

    @Override
    public void createMovieRecommandationBlackListed(MovieRecommendationBlackListed movieRecommendationBlackListed) {
        // Retrait éventuel de la liste des Recommendations
        iMovieRecommendationService.deleteMovieRecommendation(movieRecommendationBlackListed.getMovie(),movieRecommendationBlackListed.getUserAccount());
        //Ajout de sysdate à l'enregistrement
        movieRecommendationBlackListed.setDate(LocalDate.now());
        // Ajout du movie blacklisté dans la base
        iMovieRecommendationBlackListedRepository.save(movieRecommendationBlackListed);
    }
}
