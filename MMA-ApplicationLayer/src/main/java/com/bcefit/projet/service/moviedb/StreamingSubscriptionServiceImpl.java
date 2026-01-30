package com.bcefit.projet.service.moviedb;

import com.bcefit.projet.domain.moviedb.StreamingSubscription;
import com.bcefit.projet.infrastructure.StreamingSubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class StreamingSubscriptionServiceImpl implements IStreamingSubscriptionService{

    Logger logger = LoggerFactory.getLogger(StreamingSubscription.class);


    @Autowired
    StreamingSubscriptionRepository repository;

    @Override
    public Iterable<StreamingSubscription> findAll() {
        return repository.findAll();
    }

    @Override
    public StreamingSubscription findById(Long id) {
        Optional<StreamingSubscription> optionalStreamingSubscription = repository.findById(id);
        logger.debug("service findbyId {}", id);
        if (optionalStreamingSubscription.isPresent()) {
            return optionalStreamingSubscription.get();
        } else {
            logger.error("pas de genre Streaming subscription avec l'id  {}", id);
            throw new EntityNotFoundException("Le streaming subscription n'existe pas");
        }
    }

    @Override
    public void createStremaingSubscription(StreamingSubscription streamingSubscription) {
        repository.save(streamingSubscription);
    }
}
