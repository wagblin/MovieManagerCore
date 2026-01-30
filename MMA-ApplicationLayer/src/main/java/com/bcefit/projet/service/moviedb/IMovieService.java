package com.bcefit.projet.service.moviedb;

import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

public interface IMovieService {

    Movie getMovieDetail(Long idMovie) throws InvalidEntityExeption;


}
