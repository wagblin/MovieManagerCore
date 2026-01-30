package com.bcefit.projet.service.moviedb;

import com.bcefit.projet.domain.moviedb.GenreTv;
import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

import java.util.List;

public interface ITvService {

    Tv getDetailByIdTv(Long idTv) throws InvalidEntityExeption;

    Tv getAllDetailByIdTv(Long idTv) throws InvalidEntityExeption;


}
