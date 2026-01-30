package com.bcefit.projet.service.moviedb;

import com.bcefit.projet.domain.moviedb.StreamingSubscription;

public interface IStreamingSubscriptionService {

    Iterable<StreamingSubscription> findAll();

    StreamingSubscription findById(Long id);

    void createStremaingSubscription(StreamingSubscription streamingSubscription);
}
