package com.bcefit.projet.service.mapper;


import com.bcefit.projet.domain.moviedb.StreamingSubscription;
import info.movito.themoviedbapi.model.tv.Network;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StreamingSubscriptionApiMapper {

    public StreamingSubscription convertStreamingSubscriptionApiToStreamingSubscription(Network api) {
        StreamingSubscription entity = new StreamingSubscription();
        entity.setId(Long.valueOf(api.getId()));
        entity.setName(api.getName());
        return entity;
    }

    public Network convertStreamingSubscriptionToStreamingSubscriptionApi(StreamingSubscription entity) {
        Network api = new Network();
        api.setId(entity.getId().intValue());
        api.setName(entity.getName());
        return api;
    }

    public List<StreamingSubscription> convertListApiToEntity(List<Network> apiList){
        List<StreamingSubscription> entityList = new ArrayList<>();
        for(Network api : apiList){
            entityList.add(convertStreamingSubscriptionApiToStreamingSubscription(api));
        }
        return entityList;
    }

    public List<Network> convertListEntityToApi(List<StreamingSubscription> entityList){
        List<Network> apiList = new ArrayList<>();
        for(StreamingSubscription entity : entityList){
            apiList.add(convertStreamingSubscriptionToStreamingSubscriptionApi(entity));
        }
        return apiList;
    }

}

