package com.bcefit.projet.exposition.recommendation.mapper;

import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.exposition.recommendation.dto.TvRecommendationDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TvRecommendationMapper {

        public TvRecommendationDto convertEntityToDto(Tv entity){
            TvRecommendationDto dto = new TvRecommendationDto();
            dto.setIdTv(entity.getIdTv());
            dto.setOriginalName(entity.getOriginalName());
            dto.setPosterPath(entity.getPosterPath());
            dto.setNumberOfEpisodes(entity.getNumberOfEpisodes());
            dto.setNumberOfSeasons(entity.getNumberOfSeasons());
            dto.setPopularity(entity.getPopularity());

            return dto;
        }

        public List<TvRecommendationDto> convertListEntityToDto(List<Tv> entityList){
            List<TvRecommendationDto> dtoList = new ArrayList<>();
            for(Tv entity : entityList){
                dtoList.add(convertEntityToDto(entity));
            }
            return dtoList;
        }

    }
