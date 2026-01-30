package com.bcefit.projet.exposition.wish.rest;


import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.wish.WishMovie;
import com.bcefit.projet.exposition.wish.dto.WishContentDto;
import com.bcefit.projet.exposition.wish.dto.WishMovieDto;
import com.bcefit.projet.exposition.wish.mapper.WishMovieMapper;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.moviedb.IMovieService;
import com.bcefit.projet.service.user.IUserAccountService;
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
@RequestMapping("/api/v1/wish")
public class WishMovieAPI {

    @Autowired
    IUserAccountService iUserAccountService;

    @Autowired
    IWishMovieService iWishMovieService;

    @Autowired
    WishMovieMapper wishMovieMapper;
    @Autowired
    IMovieService iMovieService;

    Logger logger = LoggerFactory.getLogger(WishMovieAPI.class);

    @GetMapping("/movie/{idWishMovie}")
    public WishMovieDto getWishMovieById(@PathVariable("idWishMovie") Long idWish,@RequestAttribute("userEmail") String userEmail){
        logger.info("Nouvelle demande pour le wish movie {}", idWish);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        WishMovie wishMovie = iWishMovieService.findById(idWish);
        logger.debug("DEBUG---ID Wish movie = {}", wishMovie.getIdWish());
        return wishMovieMapper.convertEntityToDto(wishMovie);
    }

    @PostMapping("/movie")
    public ResponseEntity<WishMovieDto> create(@RequestBody WishMovieDto wishMovieDto,@RequestAttribute("userEmail") String userEmail) throws InvalidEntityExeption {

        logger.info("Nouvelle demande de création de wish movie avec le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        // Recherche du contexte du movie
        Movie movie = iMovieService.getMovieDetail(wishMovieDto.getIdMovie());

        // Mapping DTO vers Entity
        WishMovie wishMovie = wishMovieMapper.convertDtoToEntity(wishMovieDto);
        // Enrichissement de l'entity avec UserAccount et Movie
        wishMovie.setUserAccount(userAccount);
        wishMovie.setMovie(movie);

        // Création du WishMovie
        WishMovieDto dto = wishMovieMapper.convertEntityToDto(iWishMovieService.createWishMovie(wishMovie));

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/movie/all")
    public ResponseEntity<List<WishMovieDto>> getAllWishMovies(@RequestAttribute("userEmail") String userEmail){

        logger.info("Nouvelle demande pour le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        logger.info("nouvelle demande de liste de wish movie");
        Iterable<WishMovie> iterable=iWishMovieService.findAllByUserAccountId(userAccount);
        List<WishMovieDto> wishMovieDtoList = new ArrayList<>();

        iterable.forEach((pEntity)-> wishMovieDtoList.add(wishMovieMapper.convertEntityToDto(pEntity)));

        return ResponseEntity.status(HttpStatus.OK).body(wishMovieDtoList);
    }

    @DeleteMapping( "/movie")
    public ResponseEntity<WishContentDto> deleteWishMovie(@RequestBody WishContentDto wishContentDto, @RequestAttribute("userEmail") String userEmail){
        logger.info("Nouvelle demande de création de watch movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        logger.info("Nouvelle demande de suppression wish movie {}",wishContentDto.getWishIdToDelete());
        WishMovie wishMovie = new WishMovie();
        wishMovie.setIdWish(wishContentDto.getWishIdToDelete());
        iWishMovieService.deleteWishMovie(wishMovie);

        return ResponseEntity.status(HttpStatus.OK).body(wishContentDto);
    }

}
