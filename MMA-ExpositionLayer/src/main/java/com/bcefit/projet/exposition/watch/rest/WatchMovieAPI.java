package com.bcefit.projet.exposition.watch.rest;


import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchMovie;
import com.bcefit.projet.exposition.watch.dto.WatchContentDto;
import com.bcefit.projet.exposition.watch.dto.WatchMovieDto;
import com.bcefit.projet.exposition.watch.mapper.WatchMovieMapper;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.moviedb.IMovieService;
import com.bcefit.projet.service.user.IUserAccountService;
import com.bcefit.projet.service.watch.IWatchMovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/watch")
public class WatchMovieAPI {


    @Autowired
    IWatchMovieService service;

    @Autowired
    WatchMovieMapper watchMovieMapper;

    @Autowired
    IUserAccountService iUserAccountService;

    @Autowired
    IMovieService iMovieService;

    Logger logger = LoggerFactory.getLogger(WatchMovieAPI.class);

    @GetMapping("/movie/{idWatchMovie}")
    public ResponseEntity<WatchMovieDto> getWatchMovieById(@PathVariable("idWatchMovie") Long idWatch,@RequestAttribute("userEmail") String userEmail){

        logger.info("Nouvelle demande de création de watch movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        WatchMovie watchMovie = service.findById(idWatch);
        logger.debug("DEBUG---ID Watch movie = {}", watchMovie.getIdWatch());
        WatchMovieDto watchMovieDto = watchMovieMapper.convertEntityToDto(watchMovie);

        return ResponseEntity.status(HttpStatus.OK).body(watchMovieDto);
    }

    @PostMapping("/movie")
    public ResponseEntity<WatchMovieDto> create(@RequestBody WatchMovieDto watchMovieDto,@RequestAttribute("userEmail") String userEmail) throws InvalidEntityExeption {

        logger.info("Nouvelle demande de création de watch movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }
        // Recherche du contexte du movie
        Movie movie = iMovieService.getMovieDetail(watchMovieDto.getIdMovie());

        // Mapping DTO vers Entity
        WatchMovie watchMovie = watchMovieMapper.convertDtoToEntity(watchMovieDto);
        // Enrichissement de l'entity avec UserAccount et Movie
        watchMovie.setUserAccount(userAccount);
        watchMovie.setMovie(movie);

        // Création du WatchMovie
        WatchMovieDto dto = watchMovieMapper.convertEntityToDto(service.createWatchMovie(watchMovie));

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/movie/all")
    public ResponseEntity<List<WatchMovieDto>> getAllWatchMovies(@RequestAttribute("userEmail") String userEmail){

        logger.info("Nouvelle demande de création de watch movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }


        logger.info("nouvelle demande de liste de watch movie");
        Iterable<WatchMovie> iterable=service.findAllByUserAccountId(userAccount);
        List<WatchMovieDto> watchMovieDtoList = new ArrayList<>();

        iterable.forEach((pEntity)-> watchMovieDtoList.add(watchMovieMapper.convertEntityToDto(pEntity)));

        return ResponseEntity.status(HttpStatus.OK).body(watchMovieDtoList);
    }

    @DeleteMapping("/movie")
    public ResponseEntity<WatchContentDto> deleteWatchMovie(@RequestBody WatchContentDto watchContentDto,   @RequestAttribute("userEmail") String userEmail){

        logger.info("Nouvelle demande de création de watch movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        logger.info("Nouvelle demande de suppression watch movie {}",watchContentDto.getWatchIdToDelete());
        WatchMovie watchMovie = new WatchMovie();
        watchMovie.setIdWatch(watchContentDto.getWatchIdToDelete());
        watchMovie.setUserAccount(userAccount);

        service.deleteWatchMovie(watchMovie);

        return ResponseEntity.status(HttpStatus.OK).body(watchContentDto);
    }

}
