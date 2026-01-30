package com.bcefit.projet.service.moviedb;


import com.bcefit.projet.domain.moviedb.GenreTv;

import java.util.List;

public interface IGenreTvService {

    Iterable<GenreTv> findAll();

    GenreTv findById(Long id);

    List<GenreTv> getGenreTv(Long idTv);

    void createGenreTv(GenreTv genreTv);
}
