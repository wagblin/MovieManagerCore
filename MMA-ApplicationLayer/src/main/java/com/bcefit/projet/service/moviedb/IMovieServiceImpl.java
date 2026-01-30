package com.bcefit.projet.service.moviedb;

import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.infrastructure.IMovieRepository;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.moviedb.api.ITmdbApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IMovieServiceImpl implements IMovieService{

    @Autowired
    IMovieRepository iMovieRepository;

    @Autowired
    ITmdbApiService iTmdbApiService;

    @Override
    public Movie getMovieDetail(Long idMovie) throws InvalidEntityExeption {
        Movie movie = iMovieRepository.findByIdMovie(idMovie);
        if (movie==null){
            movie = iTmdbApiService.synchronizeMovieDetailFromApi(idMovie);
        }
        return iTmdbApiService.synchronizeMovieDetailFromApi(idMovie);
    }
}
