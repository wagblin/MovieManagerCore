package com.bcefit.projet.service.moviedb;


import com.bcefit.projet.domain.moviedb.Episode;
import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.infrastructure.IEpisodeRepository;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.moviedb.api.ITmdbApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IEpisodeServiceImpl implements IEpisodeService {

    @Autowired
    ITvService iTvService;

    @Autowired
    IEpisodeRepository iEpisodeRepository;
    @Autowired
    ITmdbApiService iTmdbApiService;

    @Override
    public Episode getEpisodeDetail(Long idTv, Long idEpisode) throws InvalidEntityExeption {
        Episode episode = new Episode();
        episode = iEpisodeRepository.findByIdEpisode(idEpisode);
        if (episode == null){
            Tv tv = iTmdbApiService.synchronizeTvDetailFromApiFromApi(idTv);
            episode = iEpisodeRepository.findByIdEpisode(idEpisode);
        }
        return episode;
    }

    @Override
    public List<Episode> getAllEpisodeByIdTv(Long idTv) {
        List<Episode> episodeList = new ArrayList<>();
        episodeList = iEpisodeRepository.findBySeriesIdOrderBySeasonNumberAsc(idTv.intValue());
        return episodeList;
    }

    @Override
    public List<Episode> getAllEpisodeByIdTvAndIdSeason(Long idTv, Long idSeason) {
        List<Episode> episodeList = new ArrayList<>();
        episodeList = iEpisodeRepository.findBySeriesIdAndSeasonId(idTv.intValue(),idSeason);
        return episodeList;
    }

    @Override
    public Episode getEpisodeDetailByEpisodeId(Long idEpisode) {
        return iEpisodeRepository.findByIdEpisode(idEpisode);
    }
}
