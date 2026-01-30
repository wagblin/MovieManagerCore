package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.moviedb.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IMovieRepository extends CrudRepository<Movie , Long> {
    @Query("select m from Movie m where m.idMovie = ?1")
    Movie findByIdMovie(Long idMovie);
}
