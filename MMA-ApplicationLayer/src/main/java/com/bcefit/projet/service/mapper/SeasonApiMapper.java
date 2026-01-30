package com.bcefit.projet.service.mapper;

import com.bcefit.projet.domain.moviedb.Episode;
import com.bcefit.projet.domain.moviedb.Season;
import info.movito.themoviedbapi.model.tv.TvEpisode;
import info.movito.themoviedbapi.model.tv.TvSeason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeasonApiMapper {

    @Autowired
    EpisodeApiMapper episodeApiMapper;

    public Season convertSeasonApiToSeason(TvSeason api){
        Season entity = new Season();
        entity.setIdSeason(Long.valueOf(api.getId()));
        entity.setAirDate(api.getAirDate());
        entity.setPosterPath(api.getPosterPath());
        entity.setSeasonNumber(api.getSeasonNumber());
        entity.setOverview(api.getOverview());


        return entity;
    }

    public TvSeason convertSeasonToSeasonApi(Season entity){
        TvSeason api = new TvSeason();
        api.setId(entity.getIdSeason().intValue());
        api.setAirDate(entity.getAirDate());
        api.setPosterPath(entity.getPosterPath());
        api.setSeasonNumber(entity.getSeasonNumber());
        api.setOverview(entity.getOverview());

        return api;
    }

    public List<Season> convertListApiToEntity(List<TvSeason> apiList){
        List<Season> entityList = new ArrayList<>();
        for(TvSeason api : apiList){
            entityList.add(convertSeasonApiToSeason(api));
        }
        return entityList;
    }

    public List<TvSeason> convertListEntityToApi(List<Season> entityList){
        List<TvSeason> apiList = new ArrayList<>();
        for(Season entity : entityList){
            apiList.add(convertSeasonToSeasonApi(entity));
        }
        return apiList;
    }


}
