package com.bcefit.projet.service.mapper;

import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.moviedb.Tv;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.tv.TvEpisode;
import info.movito.themoviedbapi.model.tv.TvSeason;
import info.movito.themoviedbapi.model.tv.TvSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TvApiMapper {

    @Autowired
    GenreTvApiMapper genreTvApiMapper;
    @Autowired
    StreamingSubscriptionApiMapper streamingSubscriptionApiMapper;
    @Autowired
    SeasonApiMapper seasonApiMapper;


    public Tv convertTvApiToTv(TvSeries api){
        Tv entity = new Tv();
        entity.setIdTv(Long.valueOf(api.getId()));
        entity.setEpisodeRuntime(api.getEpisodeRuntime());
        entity.setFirstAirDate(api.getFirstAirDate());
        entity.setLastAirDate(api.getLastAirDate());
        entity.setGenreTvList(genreTvApiMapper.convertListApiToEntity(api.getGenres()));
        entity.setStreamingSubscriptionList(streamingSubscriptionApiMapper.convertListApiToEntity(api.getNetworks()));
        entity.setHomepage(api.getHomepage());
        entity.setOriginalName(api.getOriginalName());
        entity.setOriginCountry(api.getOriginCountry());
        entity.setOverview(api.getOverview());
        entity.setPopularity(api.getPopularity());
        entity.setBackdropPath(api.getBackdropPath());
        entity.setPosterPath(api.getPosterPath());
        entity.setNumberOfEpisodes(api.getNumberOfEpisodes());
        entity.setNumberOfSeasons(api.getNumberOfSeasons());
        entity.setVoteAverage(api.getVoteAverage());
        entity.setVoteCount(api.getVoteCount());
        entity.setStatus(api.getStatus());

        return entity;
    }

    public Tv convertTvRecommendationApiToTv(TvSeries api){
        Tv entity = new Tv();
        entity.setIdTv(Long.valueOf(api.getId()));
        entity.setEpisodeRuntime(api.getEpisodeRuntime());
        entity.setFirstAirDate(api.getFirstAirDate());
        entity.setLastAirDate(api.getLastAirDate());
        entity.setHomepage(api.getHomepage());
        entity.setOriginalName(api.getOriginalName());
        entity.setOriginCountry(api.getOriginCountry());
        entity.setOverview(api.getOverview());
        entity.setPopularity(api.getPopularity());
        entity.setBackdropPath(api.getBackdropPath());
        entity.setPosterPath(api.getPosterPath());
        entity.setNumberOfEpisodes(api.getNumberOfEpisodes());
        entity.setNumberOfSeasons(api.getNumberOfSeasons());
        entity.setVoteAverage(api.getVoteAverage());
        entity.setVoteCount(api.getVoteCount());
        entity.setStatus(api.getStatus());

        return entity;
    }

    public TvSeries convertTvToTvApi(Tv entity){
        TvSeries api = new TvSeries();
        api.setId(entity.getIdTv().intValue());
        api.setEpisodeRuntime(entity.getEpisodeRuntime());
        api.setFirstAirDate(entity.getFirstAirDate());
        api.setLastAirDate(entity.getLastAirDate());
        api.setGenres(genreTvApiMapper.convertListEntityToApi(entity.getGenreTvList()));
        api.setNetworks(streamingSubscriptionApiMapper.convertListEntityToApi(entity.getStreamingSubscriptionList()));
        api.setHomepage(entity.getHomepage());
        api.setOriginalName(entity.getOriginalName());
        api.setOriginCountry(entity.getOriginCountry());
        api.setOverview(entity.getOverview());
        api.setPopularity(entity.getPopularity());
        api.setBackdropPath(entity.getBackdropPath());
        api.setPosterPath(entity.getPosterPath());
        api.setNumberOfEpisodes(entity.getNumberOfEpisodes());
        api.setNumberOfSeasons(entity.getNumberOfSeasons());
        api.setSeasons(seasonApiMapper.convertListEntityToApi(entity.getSeasons()));
        api.setVoteAverage(entity.getVoteAverage());
        api.setVoteCount(entity.getVoteCount());
        api.setStatus(entity.getStatus());

        return api;
    }

    public List<Tv> convertTvRecommendationApiToTv(List<TvSeries> tvSeriesList){
        List<Tv> entityList = new ArrayList<>();
        for(TvSeries dto : tvSeriesList){
            entityList.add(convertTvRecommendationApiToTv(dto));
        }
        return entityList;
    }
}
