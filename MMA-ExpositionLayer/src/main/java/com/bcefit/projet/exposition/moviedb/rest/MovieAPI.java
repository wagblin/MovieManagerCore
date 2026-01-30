package com.bcefit.projet.exposition.moviedb.rest;


import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchMovie;
import com.bcefit.projet.domain.wish.WishMovie;
import com.bcefit.projet.exposition.moviedb.dto.MovieDetailsDto;
import com.bcefit.projet.exposition.moviedb.dto.MovieLightDto;
import com.bcefit.projet.exposition.moviedb.mapper.MovieDetailMapper;
import com.bcefit.projet.exposition.moviedb.mapper.MovieLightMapper;
import com.bcefit.projet.exposition.watch.mapper.WatchMovieMapper;
import com.bcefit.projet.service.moviedb.IMovieService;
import com.bcefit.projet.service.user.IUserAccountService;
import com.bcefit.projet.service.watch.IWatchMovieService;
import com.bcefit.projet.service.wish.IWishMovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
public class    MovieAPI {


    @Autowired
    IWatchMovieService iWatchMovieService;
    @Autowired
    IWishMovieService iWishMovieService;

    @Autowired
    WatchMovieMapper watchMovieMapper;

    @Autowired
    MovieDetailMapper movieDetailMapper;

    @Autowired
    MovieLightMapper movieLightMapper;

    @Autowired
    IUserAccountService iUserAccountService;

    @Autowired
    IMovieService iMovieService;

    Logger logger = LoggerFactory.getLogger(MovieAPI.class);


    @GetMapping("/watchlist")
    public ResponseEntity<List<MovieLightDto>> getWatchMovieList(@RequestAttribute("userEmail") String userEmail){

        logger.info("Nouvelle demande de liste de watch movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }


        logger.info("nouvelle demande de liste de watch movie");
        Iterable<WatchMovie> iterable=iWatchMovieService.findAllByUserAccountId(userAccount);
        List<MovieLightDto> movieLightDtoList = new ArrayList<>();

        iterable.forEach((pEntity)-> movieLightDtoList.add(movieLightMapper.convertWatchEntityToDto(pEntity)));

        // recherche des éventuels wish Movie associé
        for (MovieLightDto movieLightDto : movieLightDtoList) {
            WishMovie wishMovie = iWishMovieService.findByIdMovieAndUserAccount(movieLightDto.getIdMovie(), userAccount);
            if (wishMovie != null) {
                movieLightDto.setIdWish(wishMovie.getIdWish());
                movieLightDto.setDateWish(wishMovie.getDateWsih());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(movieLightDtoList);
    }

    @GetMapping("/wishlist")
    public ResponseEntity<List<MovieLightDto>> getWishMovieList(@RequestAttribute("userEmail") String userEmail){

        logger.info("Nouvelle demande de liste de wish movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }


        logger.info("nouvelle demande de liste de wish movie");
        Iterable<WishMovie> iterable=iWishMovieService.findAllByUserAccountId(userAccount);
        List<MovieLightDto> movieLightDtoList = new ArrayList<>();

        iterable.forEach((pEntity)-> movieLightDtoList.add(movieLightMapper.convertWishEntityToDto(pEntity)));

        // recherche des éventuels wish Movie associé
        for (MovieLightDto movieLightDto : movieLightDtoList) {
            WatchMovie watchMovie = iWatchMovieService.findByIdMovieAndUserAccount(movieLightDto.getIdMovie(), userAccount);
            if (watchMovie != null) {
                movieLightDto.setIdWatch(watchMovie.getIdWatch());
                movieLightDto.setViewingMood(watchMovie.getViewingMood());
                movieLightDto.setViewingPlace(watchMovie.getViewingPlace());
                movieLightDto.setViewingRate(watchMovie.getViewingRate());
                movieLightDto.setDateWatch(watchMovie.getDateWatch());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(movieLightDtoList);
    }

    @GetMapping("/detail/{idMovie}")
    public ResponseEntity<MovieDetailsDto> getMovieDetailsByIdMovie(@PathVariable("idMovie") Long idMovie, @RequestAttribute("userEmail") String userEmail){

        logger.info("Nouvelle demande de detail(watch) movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        MovieDetailsDto movieDetailsDto = new MovieDetailsDto();
        //Recherche de wish et watch associés
        WatchMovie watchMovie = iWatchMovieService.findByIdMovieAndUserAccount(idMovie,userAccount);
        WishMovie wishMovie = iWishMovieService.findByIdMovieAndUserAccount(idMovie, userAccount);
        if (watchMovie!=null && wishMovie!=null){
            movieDetailsDto = movieDetailMapper.convertWatchEntityToDto(watchMovie);
            movieDetailsDto.setIdWish(wishMovie.getIdWish());
        }
        if (watchMovie == null && wishMovie !=null){
            movieDetailsDto = movieDetailMapper.convertWishEntityToDto(wishMovie);
        }
        if (watchMovie !=null && wishMovie == null){
            movieDetailsDto = movieDetailMapper.convertWatchEntityToDto(watchMovie);
        }

        if(movieDetailsDto==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(movieDetailsDto);

    }


}
