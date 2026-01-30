package com.bcefit.projet.exposition.wish.mapper;

import com.bcefit.projet.domain.wish.WishMovie;
import com.bcefit.projet.exposition.wish.dto.WishMovieDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class WishMovieMapper {

    public WishMovieDto convertEntityToDto(WishMovie entity){
        WishMovieDto dto = new WishMovieDto();
        dto.setIdWish(entity.getIdWish());
        dto.setIdMovie(entity.getMovie().getIdMovie());
        dto.setDateWish(entity.getDateWsih());
        return dto;
    }

    public WishMovie convertDtoToEntity(WishMovieDto dto){
        WishMovie entity = new WishMovie();
        entity.setIdWish(dto.getIdWish());
        entity.setDateWsih(dto.getDateWish());
        return entity;
    }

    public List<WishMovie> convertListDtoToEntity(List<WishMovieDto> dtoList){
        List<WishMovie> entityList = new ArrayList<>();
        for(WishMovieDto dto : dtoList){
            entityList.add(convertDtoToEntity(dto));
        }
        return entityList;
    }

    public List<WishMovieDto> convertListEntityToDto(List<WishMovie> entityList){
        List<WishMovieDto> dtoList = new ArrayList<>();
        for(WishMovie entity : entityList){
            dtoList.add(convertEntityToDto(entity));
        }
        return dtoList;
    }
}
