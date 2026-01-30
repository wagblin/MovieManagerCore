package com.bcefit.projet.exposition.user.rest;

import com.bcefit.projet.domain.moviedb.StreamingSubscription;
import com.bcefit.projet.exposition.user.dto.StreamingSubscriptionDto;
import com.bcefit.projet.exposition.user.mapper.StreamingSubscriptionMapper;
import com.bcefit.projet.service.moviedb.IStreamingSubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/streaming")
public class StreamingSubscriptionAPI {

    @Autowired
    IStreamingSubscriptionService service;

    @Autowired
    StreamingSubscriptionMapper mapper;

    Logger logger = LoggerFactory.getLogger(StreamingSubscriptionAPI.class);

    @GetMapping("/all")
    public List<StreamingSubscriptionDto> getAllStreaming(){
        logger.info(("Nouvelle demande de streaming"));
        Iterable<StreamingSubscription> iterable = service.findAll();
        List<StreamingSubscriptionDto> streamingSubscriptionDtoList=new ArrayList<>();

        iterable.forEach((pEntity)-> streamingSubscriptionDtoList.add(mapper.convertEntityToDto(pEntity)));

        return streamingSubscriptionDtoList;
    }
}
