package com.bcefit.projet.service.mapper;


import com.bcefit.projet.domain.moviedb.GenreMovie;
import info.movito.themoviedbapi.model.Genre;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMovieApiMapper {

    public GenreMovie convertGenreMovieApiToGenreMovie(Genre api) {
        GenreMovie entity = new GenreMovie();
        entity.setId(Long.valueOf(api.getId()));
        entity.setName(api.getName());
        return entity;
    }

    public Genre convertGenreMovieToGenreMovieApi(GenreMovie entity) {
        Genre api = new Genre();
        api.setId(entity.getId().intValue());
        api.setName(entity.getName());
        return api;
    }

    public List<GenreMovie> convertListApiToEntity(List<Genre> apiList){
        List<GenreMovie> entityList = new ArrayList<>();
        for(Genre api : apiList){
            entityList.add(convertGenreMovieApiToGenreMovie(api));
        }
        return entityList;
    }

    public List<Genre> convertListEntityToApi(List<GenreMovie> entityList){
        List<Genre> apiList = new ArrayList<>();
        for(GenreMovie entity : entityList){
            apiList.add(convertGenreMovieToGenreMovieApi(entity));
        }
        return apiList;
    }
}