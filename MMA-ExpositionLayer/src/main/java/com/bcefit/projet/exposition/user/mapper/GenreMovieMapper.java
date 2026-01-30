package com.bcefit.projet.exposition.user.mapper;

import com.bcefit.projet.domain.moviedb.GenreMovie;
import com.bcefit.projet.exposition.user.dto.GenreMovieDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class GenreMovieMapper {

    public GenreMovieDto convertEntityToDto(GenreMovie entity){
        return new GenreMovieDto(entity.getId(), entity.getName());
    }

    public GenreMovie convertDtoToEntity(GenreMovieDto dto){
        GenreMovie entity = new GenreMovie();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }



    public Set<GenreMovieDto> convertSetEntityToDto(Set<GenreMovie> entitySet) {
        Set<GenreMovieDto> dtoSet = new HashSet<>();
        for (GenreMovie entity : entitySet) {
            dtoSet.add(convertEntityToDto(entity));
        }
        return dtoSet;
    }

    public Set<GenreMovie> convertSetDtoToEntity(Set<GenreMovieDto> dtoSet){
        Set<GenreMovie> entitySet = new HashSet<>();
        for(GenreMovieDto dto:dtoSet){
            entitySet.add(convertDtoToEntity(dto));
        }
        return entitySet;
    }

    public List<GenreMovie> convertListDtoToEntity(List<GenreMovieDto> dtoList){
        List<GenreMovie> entityList = new ArrayList<>();
        for(GenreMovieDto dto:dtoList){
            entityList.add(convertDtoToEntity(dto));
        }
        return entityList;
    }


    public List<GenreMovieDto> convertListEntityToDto(List<GenreMovie> entityList) {
        List<GenreMovieDto> dtoList = new ArrayList<>();
        for (GenreMovie entity : entityList) {
            dtoList.add(convertEntityToDto(entity));
        }
        return dtoList;
    }

}
