package com.bcefit.projet.service.wish;

import com.bcefit.projet.domain.moviedb.Episode;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.wish.WishEpisode;
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
public class WishSeasonServiceImpl implements IWishSeasonService{

    @Autowired
    IWishEpisodeService service;

    @Autowired
    ITmdbApiService iTmdbApiService;

    @Autowired
    IEpisodeService iEpisodeService;

    Logger logger = LoggerFactory.getLogger(IWishSeasonService.class);

    @Override
    public List<WishEpisode> createWishEpisodeBySeasonId(Long idTv, Long idSeason, UserAccount userAccount) throws InvalidEntityExeption {
        // Synchroniser la base des Tv/Season/Episode
        iTmdbApiService.synchronizeTvDetailFromApiFromApi(idTv);

        //création de la liste de tous les épisodes de la Season
        List<Episode> episodeListForTvAndSeason = iEpisodeService.getAllEpisodeByIdTvAndIdSeason(idTv, idSeason);

        //création d'une liste pour stocker tous les épisodes d'une série (intérrogation de The Movie DB)
         List<WishEpisode> wishEpisodeCreatedList = new ArrayList<>();

        for (Episode episode : episodeListForTvAndSeason) {
            WishEpisode wishEpisode = new WishEpisode();
            wishEpisode.setUserAccount(userAccount);
            wishEpisode.setEpisode(episode);
            WishEpisode wishEpisodeIsExist = service.getIdWishEpisodeByIdSerieAndUserAccount(episode.getIdEpisode(), userAccount);
            if (wishEpisodeIsExist == null) {
                service.createWishEpisode(wishEpisode);
                wishEpisodeCreatedList.add(wishEpisode);
            }
        }
        return wishEpisodeCreatedList;
    }

    @Override
    public List<WishEpisode> deleteWishEpisodeBySeasonId(Long idTv, Long idSeason,  UserAccount userAccount) {

        //création de la liste de tous les épisodes de la Season
        List<Episode> episodeListForTvAndSeason = iEpisodeService.getAllEpisodeByIdTvAndIdSeason(idTv, idSeason);

        //création de la watchListMovie des watch qui seront supprimés en base
        List<WishEpisode> wishEpisodeDeletedList = new ArrayList<>();

        for (Episode episode : episodeListForTvAndSeason) {
            WishEpisode wishEpisodeToDelete = service.getIdWishEpisodeByIdSerieAndUserAccount(episode.getIdEpisode(),userAccount);
            if (wishEpisodeToDelete != null) {
                service.deleteWishEpisode(wishEpisodeToDelete);
                wishEpisodeDeletedList.add(wishEpisodeToDelete);
            }
        }
        return wishEpisodeDeletedList;
    }


}
