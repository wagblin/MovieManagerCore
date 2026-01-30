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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class WatchTvServiceImpl implements IWatchTvService {


    @Autowired
    IWatchEpisodeService iWatchEpisodeService;

    @Autowired
    IEpisodeService iEpisodeService;

    @Autowired
    ITmdbApiService iTmdbApiService;

    Logger logger = LoggerFactory.getLogger(IWatchTvService.class);

    public List<WatchEpisode> createWatchEpisodeByTvId(Long idTv, UserAccount userAccount) throws InvalidEntityExeption {
        // Synchroniser la base des Tv/Season/Episode
        iTmdbApiService.synchronizeTvDetailFromApiFromApi(idTv);

        //création de la liste de tous les épisodes de la série Tv
        List<Episode> episodeListForTV = iEpisodeService.getAllEpisodeByIdTv(idTv);

        //création de la watchListMovie des watch qui seront ajoutés en base
        List<WatchEpisode> watchEpisodeCreatedList = new ArrayList<>();

        // Boucle sur les episodes de la série Tv et création d'un watch pour chacun d'eux
        for (Episode episode : episodeListForTV) {
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
    public List<WatchEpisode> deleteWatchEpisodeByTvId(Long idTv, UserAccount userAccount) {

        //création de la liste de tous les épisodes de la série Tv
        List<Episode> episodeListForTV = iEpisodeService.getAllEpisodeByIdTv(idTv);

        // Création de la liste des watchEpisode supprimés
        List<WatchEpisode> watchEpisodeDeletedList = new ArrayList<>();

        for (Episode episode : episodeListForTV) {
            WatchEpisode watchEpisodeToDelete = iWatchEpisodeService.getIdWatchEpisodeByIdSerieAndUserAccount(episode.getIdEpisode(), userAccount);
            if (watchEpisodeToDelete != null) {
                iWatchEpisodeService.deleteWatchEpisode(watchEpisodeToDelete);
                watchEpisodeDeletedList.add(watchEpisodeToDelete);
            }
        }
        return watchEpisodeDeletedList;
    }

    @Override
    public Set<Long> getIdTvWatchByUserAccount(UserAccount userAccount) {
        Iterable<WatchEpisode> watchEpisodeIterable = iWatchEpisodeService.findAllByUserAccountId(userAccount);
        Set<Long> idTvWatchList = new HashSet<>();
        for(WatchEpisode watchEpisode : watchEpisodeIterable){
            Episode episode = watchEpisode.getEpisode();
            idTvWatchList.add(Long.valueOf(episode.getSeriesId()));
        }
        return idTvWatchList;
    }

}

