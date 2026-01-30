package com.bcefit.projet.exposition.user.mapper;

import com.bcefit.projet.domain.moviedb.StreamingSubscription;
import com.bcefit.projet.exposition.user.dto.StreamingSubscriptionDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class StreamingSubscriptionMapper {

    public StreamingSubscriptionDto convertEntityToDto(StreamingSubscription entity){
        return new StreamingSubscriptionDto(entity.getId(), entity.getName());
    }

    public StreamingSubscription convertDtoToEntity(StreamingSubscriptionDto dto){
        StreamingSubscription entity = new StreamingSubscription();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    public Set<StreamingSubscription> convertSetDtoToEntity(Set<StreamingSubscriptionDto> dtoSet){
        Set<StreamingSubscription> entitySet = new HashSet<>();
        for (StreamingSubscriptionDto dto : dtoSet){
            entitySet.add(convertDtoToEntity(dto));
        }
        return entitySet;
    }




    public Set<StreamingSubscriptionDto> convertSetEntityToDto(Set<StreamingSubscription> entitySet){
        Set<StreamingSubscriptionDto> dtoSet = new HashSet<>();
        for (StreamingSubscription entity : entitySet){
            dtoSet.add(convertEntityToDto(entity));
        }
        return dtoSet;
    }

    public List<StreamingSubscriptionDto> convertListEntityToDto(List<StreamingSubscription> entityList){
        List<StreamingSubscriptionDto> dtoList = new ArrayList<>();
        for (StreamingSubscription entity : entityList){
            dtoList.add(convertEntityToDto(entity));
        }
        return dtoList;
    }

    public List<StreamingSubscription> convertListDtoToEntity(List<StreamingSubscriptionDto> dtoList){
        List<StreamingSubscription> entityList = new ArrayList<>();
        for (StreamingSubscriptionDto dto : dtoList){
            entityList.add(convertDtoToEntity(dto));
        }
        return entityList;
    }
}


