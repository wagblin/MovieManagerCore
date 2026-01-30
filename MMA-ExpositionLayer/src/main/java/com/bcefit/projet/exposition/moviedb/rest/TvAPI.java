package com.bcefit.projet.exposition.moviedb.rest;


import com.bcefit.projet.domain.moviedb.Episode;
import com.bcefit.projet.domain.moviedb.Season;
import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchEpisode;
import com.bcefit.projet.domain.wish.WishEpisode;
import com.bcefit.projet.exposition.moviedb.dto.EpisodeDto;
import com.bcefit.projet.exposition.moviedb.dto.SeasonDto;
import com.bcefit.projet.exposition.moviedb.dto.TvDetailDto;
import com.bcefit.projet.exposition.moviedb.dto.TvLightDto;
import com.bcefit.projet.exposition.moviedb.mapper.EpisodeDtoMapper;
import com.bcefit.projet.exposition.moviedb.mapper.SeasonDtoMapper;
import com.bcefit.projet.exposition.moviedb.mapper.TvDetailMapper;
import com.bcefit.projet.exposition.moviedb.mapper.TvLightMapper;
import com.bcefit.projet.exposition.user.mapper.GenreTvMapper;
import com.bcefit.projet.exposition.wish.mapper.WishEpisodeMapper;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.moviedb.IEpisodeService;
import com.bcefit.projet.service.moviedb.ITvService;
import com.bcefit.projet.service.user.IUserAccountService;
import com.bcefit.projet.service.watch.IWatchEpisodeService;
import com.bcefit.projet.service.wish.IWishEpisodeService;
import com.bcefit.projet.service.wish.IWishSeasonService;
import com.bcefit.projet.service.wish.IWishTvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/tv")
public class TvAPI {

    @Autowired
    IWishTvService iWishTvService;
    @Autowired
    IWishSeasonService iWishSeasonService;
    @Autowired
    WishEpisodeMapper wishEpisodeMapper;
    @Autowired
    IWishEpisodeService iWishEpisodeService;
    @Autowired
    IWatchEpisodeService iWatchEpisodeService;
    @Autowired
    IUserAccountService iUserAccountService;
    @Autowired
    TvLightMapper tvLightMapper;
    @Autowired
    TvDetailMapper tvDetailMapper;
    @Autowired
    SeasonDtoMapper seasonDtoMapper;
    @Autowired
    EpisodeDtoMapper episodeDtoMapper;
    @Autowired
    ITvService tvService;
    @Autowired
    IEpisodeService iEpisodeService;
    @Autowired
    GenreTvMapper genreTvMapper;

    Logger logger = LoggerFactory.getLogger(TvAPI.class);

    @GetMapping("/watchlist")
    public ResponseEntity<List<TvLightDto>> getWatchTvList(@RequestAttribute("userEmail") String userEmail) throws InvalidEntityExeption {

        logger.info("Nouvelle demande de liste de watch movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){ResponseEntity.status(HttpStatus.UNAUTHORIZED);}
        logger.info("nouvelle demande de liste de watch movie");
        Iterable<WatchEpisode> iterable=iWatchEpisodeService.findAllByUserAccountId(userAccount);

        // création d'un Set des idTv des WatchEpisodes
        Set<Long> idTvSet = new HashSet<>();
        for(WatchEpisode watchEpisode : iterable){
            idTvSet.add(Long.valueOf(watchEpisode.getEpisode().getSeriesId()));
        }
        List<TvLightDto> tvLightDtoList = new ArrayList<>();
        List<Long> listIdTv = new ArrayList<>();
        //Pour chaque IdTv on map vers tvDto et on alimente la liste des résultats
        // En plus on initialise idWatch à 0 pour indiquer qu'il y a des Episode Watch dans cette liste
        for(Long idTv : idTvSet) {
            //récupération de l'entité Tv
            Tv tv = tvService.getDetailByIdTv(idTv);
            TvLightDto tvLightDto = new TvLightDto();
            tvLightDto = tvLightMapper.convertEntityToDto(tv);
            tvLightDto.setIdWatch(0L);
            tvLightDtoList.add(tvLightDto);

        }
        return ResponseEntity.status(HttpStatus.OK).body(tvLightDtoList);
    }

    @GetMapping("/wishlist")
    public ResponseEntity<List<TvLightDto>> getWishTvList(@RequestAttribute("userEmail") String userEmail) throws InvalidEntityExeption {

        logger.info("Nouvelle demande de liste de watch movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){ResponseEntity.status(HttpStatus.UNAUTHORIZED);}
        logger.info("nouvelle demande de liste de watch movie");
        Iterable<WishEpisode> iterable=iWishEpisodeService.findAllByUserAccountId(userAccount);
        // création d'un Set des idTv des WatchEpisodes
        Set<Long> idTvSet = new HashSet<>();
        for(WishEpisode wishEpisode : iterable){
            idTvSet.add(Long.valueOf(wishEpisode.getEpisode().getSeriesId()));
        }
        List<TvLightDto> tvLightDtoList = new ArrayList<>();
        List<Long> listIdTv = new ArrayList<>();
        //Pour chaque IdTv on map vers tvDto et on alimente la liste des résultats
        // En plus on initialise idWish à 0 pour indiquer qu'il y a des Episode Watch dans cette liste
        for(Long idTv : idTvSet) {
            //récupération de l'entité Tv
            Tv tv = tvService.getDetailByIdTv(idTv);
            TvLightDto tvLightDto = new TvLightDto();
            tvLightDto = tvLightMapper.convertEntityToDto(tv);
            tvLightDto.setIdWish(0L);
            tvLightDtoList.add(tvLightDto);

        }
        return ResponseEntity.status(HttpStatus.OK).body(tvLightDtoList);
    }

    @GetMapping("/detail/{idTv}")
    public ResponseEntity<TvDetailDto> getTvDetailsByIdMovie(@PathVariable("idTv") Long idMovie, @RequestAttribute("userEmail") String userEmail) throws InvalidEntityExeption {

        logger.info("Nouvelle demande de detail(watch) movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        // Récupérer l'objet Tv et le mapper vers TvDto
        Tv tv = tvService.getAllDetailByIdTv(idMovie);
        TvDetailDto tvDetailDto = tvDetailMapper.convertEntityToDto(tv);
        // Récupération de la liste des season
        List<Season> seasonList= tv.getSeasons();
        // création d'un flag WishTv et d'un flag watchTv pour permettre indiquer l'éventuelle présence
        // de wish ou wash sur les éposides de cette série Tv
        Boolean flagWishTv = false;
        Boolean flagWatchTv = false;
        // création de la liste SeasonDto pour ajouter les éléments
        List<SeasonDto> seasonDtoList = new ArrayList<>();
        // On boucle sur les saisons
        for (Season season : seasonList){
            // création d'un SeasonDto et instanciation avec le mapper
            SeasonDto seasonDto = new SeasonDto();
            seasonDto = seasonDtoMapper.convertEntityToDto(season);
            // création d'un flag WishSeason et d'un flag watchSeason pour permettre indiquer l'éventuelle présence
            // de wish ou wash sur les éposides de cette season
            Boolean flagWishSeason = false;
            Boolean flagWatchSeason = false;
            //pour chaque season on récupère la liste des épisodes
            List<Episode> episodeList = season.getEpisodeList();
            // création de la liste des EpisodeDto
            List<EpisodeDto> episodeDtoList = new ArrayList<>();
            //pour chaque épisode on récupère les éventuels wish et watch associés
            for(Episode episode : episodeList){
                //récupération de l'episode et mapping vers EpisodeDto
                EpisodeDto episodeDto = episodeDtoMapper.convertEntityToDto(iEpisodeService.getEpisodeDetailByEpisodeId(episode.getIdEpisode()));
                WatchEpisode watchEpisode = iWatchEpisodeService.findByIdEpisodeAndUserAccount(episode.getIdEpisode(), userAccount);
                if(watchEpisode != null){
                    episodeDto.setIdWatch(watchEpisode.getIdWatch());
                    episodeDto.setViewingMood(watchEpisode.getViewingMood());
                    episodeDto.setViewingPlace(watchEpisode.getViewingPlace());
                    episodeDto.setViewingRate(watchEpisode.getViewingRate());
                    episodeDto.setDateWatch(watchEpisode.getDateWatch());
                    flagWatchSeason = true;
                    flagWatchTv = true;
                }
                WishEpisode wishEpisode = iWishEpisodeService.findByIdEpisodeAndUserAccount(episode.getIdEpisode(), userAccount);
                if(wishEpisode != null){
                    episodeDto.setIdWish(wishEpisode.getIdWish());
                    episodeDto.setDateWish(wishEpisode.getDateWsih());
                    flagWishSeason = true;
                    flagWishTv = true;
                }
               episodeDtoList.add(episodeDto);
            }
            seasonDto.setEpisodeDtoList(episodeDtoList);
            if (flagWatchSeason){seasonDto.setIdWatch(0L);}
            if (flagWishSeason){seasonDto.setIdWish(0L);}
            seasonDtoList.add(seasonDto);
        }
        // Ajout de la liste des SeasonDto à TvDto
        tvDetailDto.setSeasonDtoList(seasonDtoList);
        if (flagWatchTv){tvDetailDto.setIdWatch(0L);}
        if (flagWishTv){tvDetailDto.setIdWish(0L);}

        return ResponseEntity.status(HttpStatus.OK).body(tvDetailDto);

    }

}

