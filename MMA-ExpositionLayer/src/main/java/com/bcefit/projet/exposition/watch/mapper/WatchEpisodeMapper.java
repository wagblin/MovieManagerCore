package com.bcefit.projet.exposition.watch.mapper;


import com.bcefit.projet.domain.watch.WatchEpisode;
import com.bcefit.projet.exposition.watch.dto.WatchEpisodeDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WatchEpisodeMapper {



    public WatchEpisodeDto convertEntityToDto(WatchEpisode entity){
        WatchEpisodeDto dto = new WatchEpisodeDto();
        dto.setIdWatch(Long.valueOf(entity.getIdWatch()));
        dto.setIdEpisode(Long.valueOf(entity.getEpisode().getIdEpisode()));
        dto.setSeasonNumber(entity.getEpisode().getSeasonNumber());
        dto.setIdSeason(Long.valueOf(entity.getEpisode().getSeason().getIdSeason()));
        dto.setIdTv(Long.valueOf(entity.getEpisode().getSeriesId()));
        dto.setViewingPlace(entity.getViewingPlace());
        dto.setViewingMood(entity.getViewingMood());
        dto.setViewingRate(entity.getViewingRate());
        dto.setDateWatch(entity.getDateWatch());
        return dto;
    }

    public WatchEpisode convertDtoToEntity(WatchEpisodeDto dto){
        WatchEpisode entity = new WatchEpisode();
        entity.setIdWatch(dto.getIdWatch());
        entity.setViewingMood(dto.getViewingMood());
        entity.setViewingRate(dto.getViewingRate());
        entity.setViewingPlace(dto.getViewingPlace());
        entity.setDateWatch(dto.getDateWatch());
        return entity;
    }

    public List<WatchEpisode> convertListDtoToEntity(List<WatchEpisodeDto> dtoList){
        List<WatchEpisode> entityList = new ArrayList<>();
        for(WatchEpisodeDto dto : dtoList){
            entityList.add(convertDtoToEntity(dto));
        }
        return entityList;
    }

    public List<WatchEpisodeDto> convertListEntityToDto(List<WatchEpisode> entityList){
        List<WatchEpisodeDto> dtoList = new ArrayList<>();
        for(WatchEpisode entity : entityList){
            dtoList.add(convertEntityToDto(entity));
        }
        return dtoList;
    }

}
