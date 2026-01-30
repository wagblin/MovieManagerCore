package com.bcefit.projet.exposition.moviedb.mapper;


import com.bcefit.projet.domain.moviedb.Season;
import com.bcefit.projet.exposition.moviedb.dto.SeasonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeasonDtoMapper {

    @Autowired
    EpisodeDtoMapper episodeDtoMapper;

    public SeasonDto convertEntityToDto(Season entity){
        SeasonDto dto = new SeasonDto();
        dto.setIdSeason(entity.getIdSeason());
        dto.setAirDate(entity.getAirDate());
        dto.setPosterPath(entity.getPosterPath());
        dto.setSeasonNumber(entity.getSeasonNumber());
        dto.setOverview(entity.getOverview());
        dto.setEpisodeDtoList(episodeDtoMapper.convertListEntityToDto(entity.getEpisodeList()));

        return dto;
    }

    public List<SeasonDto> convertListEntityToDto(List<Season> entityList){
        List<SeasonDto> dtoList = new ArrayList<>();
        for(Season entity : entityList){
            dtoList.add(convertEntityToDto(entity));
        }
        return dtoList;
    }

}
