package com.bcefit.projet.service.wish;

import com.bcefit.projet.domain.moviedb.Episode;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchEpisode;
import com.bcefit.projet.domain.wish.WishEpisode;
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
public class WishTvServiceImpl implements IWishTvService {

    @Autowired
    IWishEpisodeService service;

    @Autowired
    ITmdbApiService iTmdbApiService;

    @Autowired
    IEpisodeService iEpisodeService;

    Logger logger = LoggerFactory.getLogger(IWishTvService.class);

    public List<WishEpisode> createWishEpisodeByTvId(Long idTv, UserAccount userAccount) throws InvalidEntityExeption {
        // Synchroniser la base des Tv/Season/Episode
        iTmdbApiService.synchronizeTvDetailFromApiFromApi(idTv);

        //création de la liste de tous les épisodes de la série Tv
        List<Episode> episodeListForTV = iEpisodeService.getAllEpisodeByIdTv(idTv);

        List<WishEpisode> wishEpisodeCreatedList = new ArrayList<>();

        // Boucle sur les episodes de la série Tv et création d'un wish pour chacun d'eux
        for (Episode episode : episodeListForTV) {
            WishEpisode wishEpisode = new WishEpisode();
            // ajout du UserAccount au watchEpisode
            wishEpisode.setUserAccount(userAccount);
            // ajout de l'épisode au watchEpisode
            wishEpisode.setEpisode(episode);
            // Si le wish n'existe pas déjà alors il est créé et ajout& à la liste wishEpisodeCreatedList
            WishEpisode wishEpisodeIsExist = service.getIdWishEpisodeByIdSerieAndUserAccount(episode.getIdEpisode(), userAccount);
            if (wishEpisodeIsExist == null) {
                service.createWishEpisode(wishEpisode);
                wishEpisodeCreatedList.add(wishEpisode);
            }
        }
        return wishEpisodeCreatedList;
    }

    @Override
    public List<WishEpisode> deleteWishEpisodeByTvId(Long idTv, UserAccount userAccount) {

        //création de la liste de tous les épisodes de la série Tv
        List<Episode> episodeListForTV = iEpisodeService.getAllEpisodeByIdTv(idTv);

        // Création de la liste des watchEpisode supprimés
        List<WishEpisode> wishEpisodeDeletedList = new ArrayList<>();

        for (Episode episode : episodeListForTV) {
            WishEpisode wishEpisodeToDelete = service.getIdWishEpisodeByIdSerieAndUserAccount(episode.getIdEpisode(), userAccount);
            if (wishEpisodeToDelete != null) {
                service.deleteWishEpisode(wishEpisodeToDelete);
                wishEpisodeDeletedList.add(wishEpisodeToDelete);
            }
        }
        return wishEpisodeDeletedList;
    }

    @Override
    public Set<Long> getIdTvByUserAccount(UserAccount userAccount) {
        Iterable<WishEpisode> wishEpisodeIterable = service.findAllByUserAccountId(userAccount);
        Set<Long> idTvWishList = new HashSet<>();
        for(WishEpisode wishEpisode : wishEpisodeIterable){
            Episode episode = wishEpisode.getEpisode();
            idTvWishList.add(Long.valueOf(episode.getSeriesId()));
        }
        return idTvWishList;
    }

}

