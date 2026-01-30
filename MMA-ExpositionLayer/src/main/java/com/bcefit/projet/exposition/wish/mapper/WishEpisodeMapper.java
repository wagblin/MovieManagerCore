package com.bcefit.projet.exposition.wish.mapper;


import com.bcefit.projet.domain.wish.WishEpisode;
import com.bcefit.projet.exposition.wish.dto.WishEpisodeDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WishEpisodeMapper {

    public WishEpisodeDto convertEntityToDto(WishEpisode entity){
        WishEpisodeDto dto = new WishEpisodeDto();
        dto.setIdWish(Long.valueOf(entity.getIdWish()));
        dto.setDateWish(entity.getDateWsih());
        dto.setIdEpisode(Long.valueOf(entity.getEpisode().getIdEpisode()));
        dto.setSeasonNumber(entity.getEpisode().getSeasonNumber());
        dto.setIdSeason(Long.valueOf(entity.getEpisode().getSeason().getIdSeason()));
        dto.setIdTv((Long.valueOf(entity.getEpisode().getSeriesId())));
        return dto;
    }

    public WishEpisode convertDtoToEntity(WishEpisodeDto dto){
        WishEpisode entity = new WishEpisode();
        entity.setIdWish(dto.getIdWish());
        entity.setDateWsih(dto.getDateWish());
        return entity;
    }

    public List<WishEpisode> convertListDtoToEntity(List<WishEpisodeDto> dtoList){
        List<WishEpisode> entityList = new ArrayList<>();
        for(WishEpisodeDto dto : dtoList){
            entityList.add(convertDtoToEntity(dto));
        }
        return entityList;
    }

    public List<WishEpisodeDto> convertListEntityToDto(List<WishEpisode> entityList){
        List<WishEpisodeDto> dtoList = new ArrayList<>();
        for(WishEpisode entity : entityList){
            dtoList.add(convertEntityToDto(entity));
        }
        return dtoList;
    }

}
