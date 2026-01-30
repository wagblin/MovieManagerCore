package com.bcefit.projet.service.moviedb;

import com.bcefit.projet.domain.moviedb.GenreMovie;
import com.bcefit.projet.infrastructure.IGenreMovieRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.slf4j.Logger;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class GenreMovieServiceImpl implements IGenreMovieService{

    Logger logger = LoggerFactory.getLogger(GenreMovieServiceImpl.class);

    @Autowired
    IGenreMovieRepository repository;

    @Override
    public Iterable<GenreMovie> findAll() {
        return repository.findAll();
    }

    @Override
    public GenreMovie findById(Long id) {
        Optional<GenreMovie> optionalGenreMovie=repository.findById(id);
        logger.debug("service findbyId {}",id);
        if(optionalGenreMovie.isPresent()){
            return optionalGenreMovie.get();
        }else{
            logger.error("pas de genre movie avec l'id  {}",id);
            throw  new EntityNotFoundException("Le genre movie n'existe pas");
        }
    }

    @Override
    public void createGenreMovie(GenreMovie genreMovie) {
        repository.save(genreMovie);
    }
}
