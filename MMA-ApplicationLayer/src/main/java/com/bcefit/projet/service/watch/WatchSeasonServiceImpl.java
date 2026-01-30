package com.bcefit.projet.service.watch;

import com.bcefit.projet.domain.moviedb.Episode;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchEpisode;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.moviedb.IEpisodeService;
import com.bcefit.projet.service.moviedb.api.ITmdbApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WatchSeasonServiceImpl implements IWatchSeasonService {


    @Autowired
    IWatchEpisodeService service;

    @Autowired
    ITmdbApiService iTmdbApiService;
    @Autowired
    IEpisodeService iEpisodeService;
    @Autowired
    IWatchEpisodeService iWatchEpisodeService;

    Logger logger = LoggerFactory.getLogger(IWatchSeasonService.class);

    @Override
    public List<WatchEpisode> createWatchEpisodeBySeasonId(Long idTv, Long idSeason, UserAccount userAccount) throws InvalidEntityExeption {
        // Synchroniser la base des Tv/Season/Episode
        iTmdbApiService.synchronizeTvDetailFromApiFromApi(idTv);

        //création de la liste de tous les épisodes de la Season
        List<Episode> episodeListForTvAndSeason = iEpisodeService.getAllEpisodeByIdTvAndIdSeason(idTv, idSeason);

        //création de la watchListMovie des watch qui seront ajoutés en base
        List<WatchEpisode> watchEpisodeCreatedList = new ArrayList<>();

        for (Episode episode : episodeListForTvAndSeason) {
            WatchEpisode watchEpisode = new WatchEpisode();
            // ajout du UserAccount au watchEpisode
            watchEpisode.setUserAccount(userAccount);
            // ajout de l'épisode au watchEpisode
            watchEpisode.setEpisode(episode);
            // Si le watch n'existe pas déjà alors il est créé et ajout& à la liste watchEpisodeCreatedList
            WatchEpisode watchEpisodeIsExist = iWatchEpisodeService.getIdWatchEpisodeByIdSerieAndUserAccount(episode.getIdEpisode(), userAccount);
            if (watchEpisodeIsExist == null) {
                iWatchEpisodeService.createWatchEpisode(watchEpisode);
                watchEpisodeCreatedList.add(watchEpisode);
            }
        }
        return watchEpisodeCreatedList;
    }

    @Override
    public List<WatchEpisode> deleteWatchEpisodeBySeasonId(Long idTv, Long idSeason,  UserAccount userAccount) {

        //création de la liste de tous les épisodes de la Season
        List<Episode> episodeListForTvAndSeason = iEpisodeService.getAllEpisodeByIdTvAndIdSeason(idTv, idSeason);

        //création de la watchListMovie des watch qui seront supprimés en base
        List<WatchEpisode> watchEpisodeDeletedList = new ArrayList<>();

        for (Episode episode : episodeListForTvAndSeason) {
            WatchEpisode watchEpisodeToDelete = iWatchEpisodeService.getIdWatchEpisodeByIdSerieAndUserAccount(episode.getIdEpisode(), userAccount);
            if (watchEpisodeToDelete != null) {
                iWatchEpisodeService.deleteWatchEpisode(watchEpisodeToDelete);
                watchEpisodeDeletedList.add(watchEpisodeToDelete);
            }
        }
        return watchEpisodeDeletedList;
    }

}
