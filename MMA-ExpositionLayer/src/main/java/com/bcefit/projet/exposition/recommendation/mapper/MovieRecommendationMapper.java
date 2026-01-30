package com.bcefit.projet.exposition.recommendation.mapper;

import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.exposition.recommendation.dto.MovieRecommendationDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieRecommendationMapper {

    public MovieRecommendationDto convertEntityToDto(Movie entity){

        MovieRecommendationDto dto = new MovieRecommendationDto();
        dto.setIdMovie(entity.getIdMovie());
        dto.setTitle(entity.getTitle());
        dto.setPosterPath(entity.getPosterPath());
        dto.setPopularity(entity.getPopularity());

        return dto;
    }

    public List<MovieRecommendationDto> convertListEntityToDto(List<Movie> entityList){
        List<MovieRecommendationDto> dtoList = new ArrayList<>();
        for(Movie entity : entityList){
            dtoList.add(convertEntityToDto(entity));
        }
        return dtoList;
    }

}
