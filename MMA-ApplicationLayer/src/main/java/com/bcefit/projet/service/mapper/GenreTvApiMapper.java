package com.bcefit.projet.service.mapper;


import com.bcefit.projet.domain.moviedb.GenreTv;
import info.movito.themoviedbapi.model.Genre;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreTvApiMapper {

    public GenreTv convertGenreTvApiToGenreTv(Genre api) {
        GenreTv entity = new GenreTv();
        entity.setId(Long.valueOf(api.getId()));
        entity.setName(api.getName());
        return entity;
    }

    public Genre convertGenreTvToGenreTvApi(GenreTv entity) {
        Genre api = new Genre();
        api.setId(entity.getId().intValue());
        api.setName(entity.getName());
        return api;
    }

    public List<GenreTv> convertListApiToEntity(List<Genre> apiList){
        List<GenreTv> entityList = new ArrayList<>();
        for(Genre api : apiList){
            entityList.add(convertGenreTvApiToGenreTv(api));
        }
        return entityList;
    }

    public List<Genre> convertListEntityToApi(List<GenreTv> entityList){
        List<Genre> apiList = new ArrayList<>();
        for(GenreTv entity : entityList){
            apiList.add(convertGenreTvToGenreTvApi(entity));
        }
        return apiList;
    }

}
