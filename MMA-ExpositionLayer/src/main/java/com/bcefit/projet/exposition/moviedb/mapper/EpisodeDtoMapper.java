package com.bcefit.projet.exposition.moviedb.mapper;

import com.bcefit.projet.domain.moviedb.Episode;
import com.bcefit.projet.exposition.moviedb.dto.EpisodeDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EpisodeDtoMapper {

    public EpisodeDto convertEntityToDto(Episode entity){
        EpisodeDto dto = new EpisodeDto();
        dto.setIdEpisode(entity.getIdEpisode());
        dto.setSeasonNumber(entity.getSeasonNumber());
        dto.setAirDate(entity.getAirDate());
        dto.setEpisodeNumber(entity.getEpisodeNumber());
        dto.setOverview(entity.getOverview());
        dto.setSeriesId(entity.getSeriesId());
        dto.setStillPath(entity.getStillPath());
        dto.setVoteAverage(entity.getVoteAverage());
        dto.setVoteCount(entity.getVoteCount());
        return dto;
    }

    public Episode convertDtoToEntity(EpisodeDto dto){
        Episode entity = new Episode();
        entity.setIdEpisode(dto.getIdEpisode());
        entity.setSeasonNumber(dto.getSeasonNumber());
        entity.setAirDate(dto.getAirDate());
        entity.setEpisodeNumber(dto.getEpisodeNumber());
        entity.setOverview(dto.getOverview());
        entity.setSeriesId(dto.getSeriesId());
        entity.setStillPath(dto.getStillPath());
        entity.setVoteAverage(dto.getVoteAverage());
        entity.setVoteCount(dto.getVoteCount());

        return entity;
    }

    public List<Episode> convertListDtoToEntity(List<EpisodeDto> dtoList){
        List<Episode> entityList = new ArrayList<>();
        for(EpisodeDto dto : dtoList){
            entityList.add(convertDtoToEntity(dto));
        }
        return entityList;
    }

    public List<EpisodeDto> convertListEntityToDto(List<Episode> entityList){
        List<EpisodeDto> dtoList = new ArrayList<>();
        for(Episode entity : entityList){
            dtoList.add(convertEntityToDto(entity));
        }
        return dtoList;
    }
}
