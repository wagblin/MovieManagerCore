package com.bcefit.projet.service.analytic;

import com.bcefit.projet.domain.analytic.MovieRecommendation;
import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.infrastructure.IMovieRecommendationRepository;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class IMovieRecommendationServiceImpl implements IMovieRecommendationService{

    @Autowired
    IMovieRecommendationRepository iMovieRecommendationRepository;

    @Override
    @Transactional
    public void deleteMovieRecommendation(Movie movie, UserAccount userAccount) {
        iMovieRecommendationRepository.deleteByUserAccountAndMovie(userAccount,movie);
    }

    @Override
    public MovieRecommendation findMovieRecommendationByIdMovieAndUserAccount(Movie movie, UserAccount userAccount) {
        return iMovieRecommendationRepository.findByUserAccountAndMovie(userAccount,movie);
    }

    @Override
    public void createMovieRecommendation(Movie movie, UserAccount userAccount) throws InvalidEntityExeption {
        MovieRecommendation movieRecommendation = new MovieRecommendation();
        movieRecommendation.setUserAccount(userAccount);
        movieRecommendation.setMovie(movie);
        movieRecommendation.setDate(LocalDate.now());
        iMovieRecommendationRepository.save(movieRecommendation);
    }

    @Override
    public List<Movie> getMovieRecommendationByUserAccount(UserAccount userAccount) {
        List<MovieRecommendation> movieRecommendationList = iMovieRecommendationRepository.findByUserAccount(userAccount);
        List<Movie> movieList = new ArrayList<>();
        if(! movieRecommendationList.isEmpty()) {
            for (MovieRecommendation movieRecommendation : movieRecommendationList) {
                Movie movie = movieRecommendation.getMovie();
                movieList.add(movie);
            }
        }
        // on trie la liste en fonction de la popularité des Movies
        Collections.sort(movieList, new Comparator<Movie>() {
            public int compare(Movie m1, Movie m2) {
                return Float.compare(m2.getPopularity(), m1.getPopularity());
            }
        });
        return movieList;
    }

}
