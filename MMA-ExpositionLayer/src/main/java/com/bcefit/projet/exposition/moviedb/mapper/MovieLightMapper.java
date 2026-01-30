package com.bcefit.projet.exposition.moviedb.mapper;

import com.bcefit.projet.domain.watch.WatchMovie;
import com.bcefit.projet.domain.wish.WishMovie;
import com.bcefit.projet.exposition.moviedb.dto.MovieLightDto;
import org.springframework.stereotype.Component;

@Component
public class MovieLightMapper {

    public MovieLightDto convertWatchEntityToDto(WatchMovie entity){

        MovieLightDto dto = new MovieLightDto();
        dto.setIdMovie(entity.getMovie().getIdMovie());
        dto.setTitle(entity.getMovie().getTitle());
        dto.setPosterPath(entity.getMovie().getPosterPath());
        dto.setIdWatch(entity.getIdWatch());
        dto.setViewingPlace(entity.getViewingPlace());
        dto.setViewingRate(entity.getViewingRate());
        dto.setViewingMood(entity.getViewingMood());
        dto.setDateWatch(entity.getDateWatch());

        return dto;
    }

    public MovieLightDto convertWishEntityToDto(WishMovie entity){

        MovieLightDto dto = new MovieLightDto();
        dto.setIdMovie(entity.getMovie().getIdMovie());
        dto.setTitle(entity.getMovie().getTitle());
        dto.setPosterPath(entity.getMovie().getPosterPath());
        dto.setIdWish(entity.getIdWish());
        dto.setDateWish(entity.getDateWsih());

        return dto;
    }

}
