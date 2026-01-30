package com.bcefit.projet.exposition.user.mapper;

import com.bcefit.projet.domain.moviedb.GenreTv;
import com.bcefit.projet.exposition.user.dto.GenreTvDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class GenreTvMapper {

    public GenreTvDto convertEntityToDto(GenreTv entity){
        return new GenreTvDto(entity.getId(), entity.getName());
    }

    public GenreTv convertDtoToEntity(GenreTvDto dto){
        GenreTv entity = new GenreTv();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    public Set<GenreTv> convertSetDtoToEntity(Set<GenreTvDto> dtoSet){
        Set<GenreTv> entitySet = new HashSet<>();
        for(GenreTvDto dto:dtoSet){
            entitySet.add(convertDtoToEntity(dto));
        }
        return entitySet;
    }

    public Set<GenreTvDto> convertSetEntityToDto(Set<GenreTv> entitySet) {
        Set<GenreTvDto> dtoSet = new HashSet<>();
        for (GenreTv entity : entitySet) {
            dtoSet.add(convertEntityToDto(entity));
        }
        return dtoSet;
    }


    public List<GenreTv> convertListDtoToEntity(List<GenreTvDto> dtoList){
        List<GenreTv> entityList = new ArrayList<>();
        for(GenreTvDto dto:dtoList){
            entityList.add(convertDtoToEntity(dto));
        }
        return entityList;
    }

    public List<GenreTvDto> convertListEntityToDto(List<GenreTv> entityList) {
        List<GenreTvDto> dtoList = new ArrayList<>();
        for (GenreTv entity : entityList) {
            dtoList.add(convertEntityToDto(entity));
        }
        return dtoList;
    }

}
