package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.moviedb.GenreTv;
import org.springframework.data.repository.CrudRepository;

public interface IGenreTvRepository extends CrudRepository<GenreTv, Long> {

    //List<GenreTv> findGenreMoviesByUserAccountSet(Long idUser);
}
