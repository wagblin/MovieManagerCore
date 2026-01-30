package com.bcefit.projet.exposition.moviedb.mapper;

import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.exposition.moviedb.dto.TvDetailDto;
import com.bcefit.projet.exposition.user.mapper.GenreTvMapper;
import com.bcefit.projet.exposition.user.mapper.StreamingSubscriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TvDetailMapper {

    @Autowired
    GenreTvMapper genreTvMapper;
    @Autowired
    SeasonDtoMapper seasonDtoMapper;
    @Autowired
    StreamingSubscriptionMapper streamingSubscriptionMapper;


    public TvDetailDto convertEntityToDto(Tv entity){
        TvDetailDto dto = new TvDetailDto();
        dto.setIdTv(entity.getIdTv());
        dto.setEpisodeRuntime(entity.getEpisodeRuntime());
        dto.setFirstAirDate(entity.getFirstAirDate());
        dto.setLastAirDate(entity.getLastAirDate());
        dto.setGenreTvDtoList(genreTvMapper.convertListEntityToDto(entity.getGenreTvList()));
        dto.setStreamingSubscriptionDtoList(streamingSubscriptionMapper.convertListEntityToDto(entity.getStreamingSubscriptionList()));
        dto.setHomepage(entity.getHomepage());
        dto.setOriginalName(entity.getOriginalName());
        dto.setOriginCountry(entity.getOriginCountry());
        dto.setOverview(entity.getOverview());
        dto.setPopularity(entity.getPopularity());
        dto.setBackdropPath(entity.getBackdropPath());
        dto.setPosterPath(entity.getPosterPath());
        dto.setNumberOfEpisodes(entity.getNumberOfEpisodes());
        dto.setNumberOfSeasons(entity.getNumberOfSeasons());
        dto.setVoteAverage(entity.getVoteAverage());
        dto.setVoteCount(entity.getVoteCount());
        dto.setStatus(entity.getStatus());
        dto.setSeasonDtoList(seasonDtoMapper.convertListEntityToDto(entity.getSeasons()));

        return dto;
    }

    public List<TvDetailDto> convertListEntityToDto(List<Tv> entityList){
        List<TvDetailDto> dtoList = new ArrayList<>();
        for(Tv entity : entityList){
            dtoList.add(convertEntityToDto(entity));
        }
        return dtoList;
    }
}
