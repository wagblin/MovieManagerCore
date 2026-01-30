package com.bcefit.projet.service.moviedb;

import com.bcefit.projet.domain.moviedb.GenreMovie;

public interface IGenreMovieService {

    Iterable<GenreMovie> findAll();

    GenreMovie findById(Long id);

    void createGenreMovie(GenreMovie genreMovie);
}
