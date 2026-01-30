package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.moviedb.GenreMovie;
import org.springframework.data.repository.CrudRepository;


public interface IGenreMovieRepository extends CrudRepository<GenreMovie, Long> {

    //List<GenreMovie> findGenreMoviesByUserAccountSet(Long idUser);
}
