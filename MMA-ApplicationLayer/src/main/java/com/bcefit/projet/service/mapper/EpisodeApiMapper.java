package com.bcefit.projet.service.mapper;

import com.bcefit.projet.domain.moviedb.Episode;
import info.movito.themoviedbapi.model.tv.TvEpisode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EpisodeApiMapper {

    public Episode convertEpisodeApiToEpisode(TvEpisode api){
        Episode entity = new Episode();
        entity.setIdEpisode(Long.valueOf(api.getId()));
        entity.setEpisodeNumber(api.getEpisodeNumber());
        entity.setOverview(api.getOverview());
        entity.setAirDate(api.getAirDate());
        entity.setSeriesId(api.getSeriesId());
        entity.setSeasonNumber(api.getSeasonNumber());
        entity.setStillPath(api.getStillPath());
        entity.setVoteCount(api.getVoteCount());
        entity.setVoteAverage(api.getVoteAverage());
        return entity;
    }

    public TvEpisode convertEpisodeToEpisodeApi(Episode entity){
        TvEpisode api = new TvEpisode();
        api.setId(entity.getIdEpisode().intValue());
        api.setEpisodeNumber(api.getEpisodeNumber());
        api.setOverview(entity.getOverview());
        api.setAirDate(entity.getAirDate());
        api.setSeriesId(entity.getSeriesId());
        api.setSeasonNumber(entity.getSeasonNumber());
        api.setStillPath(entity.getStillPath());
        api.setVoteCount(entity.getVoteCount());
        api.setVoteAverage(entity.getVoteAverage());
        return api;
    }

    public List<Episode> convertListApiToEntity(List<TvEpisode> apiList){
        List<Episode> entityList = new ArrayList<>();
        for(TvEpisode api : apiList){
            entityList.add(convertEpisodeApiToEpisode(api));
        }
        return entityList;
    }

    public List<TvEpisode> convertListEntityToApi(List<Episode> entityList){
        List<TvEpisode> apiList = new ArrayList<>();
        for(Episode entity : entityList){
            apiList.add(convertEpisodeToEpisodeApi(entity));
        }
        return apiList;
    }

}
