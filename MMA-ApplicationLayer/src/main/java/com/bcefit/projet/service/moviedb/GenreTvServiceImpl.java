package com.bcefit.projet.service.moviedb;


import com.bcefit.projet.domain.moviedb.GenreTv;
import com.bcefit.projet.infrastructure.IGenreTvRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class GenreTvServiceImpl implements IGenreTvService {

    Logger logger = LoggerFactory.getLogger(GenreTvServiceImpl.class);

    @Autowired
    IGenreTvRepository repository;

    @Override
    public Iterable<GenreTv> findAll() {
        return repository.findAll();
    }

    @Override
    public GenreTv findById(Long id) {
        Optional<GenreTv> optionalGenreTv = repository.findById(id);
        logger.debug("service findbyId {}", id);
        if (optionalGenreTv.isPresent()) {
            return optionalGenreTv.get();
        } else {
            logger.error("pas de genre TV avec l'id  {}", id);
            throw new EntityNotFoundException("Le genre TV n'existe pas");
        }
    }

    @Override
    public List<GenreTv> getGenreTv(Long idTv) {
        return null;
    }

    @Override
    public void createGenreTv(GenreTv genreTv) {
        repository.save(genreTv);
    }

}
