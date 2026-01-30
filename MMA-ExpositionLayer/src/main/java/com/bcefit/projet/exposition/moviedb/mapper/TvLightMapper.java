package com.bcefit.projet.exposition.moviedb.mapper;

import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.exposition.moviedb.dto.TvLightDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TvLightMapper {

        public TvLightDto convertEntityToDto(Tv entity){
            TvLightDto dto = new TvLightDto();
            dto.setIdTv(entity.getIdTv());
            dto.setOriginalName(entity.getOriginalName());
            dto.setPosterPath(entity.getPosterPath());
            dto.setNumberOfEpisodes(entity.getNumberOfEpisodes());
            dto.setNumberOfSeasons(entity.getNumberOfSeasons());

            return dto;
        }

        public Tv convertDtoToEntity(TvLightDto dto){
            Tv entity = new Tv();
            entity.setIdTv(dto.getIdTv());
            entity.setPosterPath(dto.getPosterPath());
            entity.setNumberOfEpisodes(dto.getNumberOfEpisodes());
            entity.setNumberOfSeasons(dto.getNumberOfSeasons());

            return entity;
        }


        public List<Tv> convertListDtoToEntity(List<TvLightDto> dtoList){
            List<Tv> entityList = new ArrayList<>();
            for(TvLightDto dto : dtoList){
                entityList.add(convertDtoToEntity(dto));
            }
            return entityList;
        }

        public List<TvLightDto> convertListEntityToDto(List<Tv> entityList){
            List<TvLightDto> dtoList = new ArrayList<>();
            for(Tv entity : entityList){
                dtoList.add(convertEntityToDto(entity));
            }
            return dtoList;
        }

    }
