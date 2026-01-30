package com.bcefit.projet.service.moviedb.api;

import com.bcefit.projet.domain.analytic.MovieRecommendation;
import com.bcefit.projet.domain.moviedb.GenreMovie;
import com.bcefit.projet.domain.moviedb.GenreTv;
import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.domain.watch.WatchEpisode;
import com.bcefit.projet.domain.wish.WishEpisode;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import info.movito.themoviedbapi.TmdbApi;

import java.util.List;

public interface ITmdbApiService {


    Movie synchronizeMovieDetailFromApi(Long idMovie) throws InvalidEntityExeption;
    Tv synchronizeTvDetailFromApiFromApi(Long idTv) throws InvalidEntityExeption;
    List<Movie> getAllMovieRecommendation(Long idMovie);
    List<Tv> getAllTvRecommendation(Long idTv);

    void synchronizeGenreMovieFromApi(TmdbApi tmdbApi);
    void synchroniseGenreTvFromApi(TmdbApi tmdbApi);
    void synchroniseStreamingSubscriptionFromApi(TmdbApi tmdbApi);

    List<Movie> getAllMovieTrendByGenreMovieFromApi(List<GenreMovie> genreMovieList) throws InvalidEntityExeption;
    List<Tv> getAllTvTrendsByGenreTvFromApi(List<GenreTv> genreTvList) throws InvalidEntityExeption;
}