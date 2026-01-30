package com.bcefit.projet.exposition.wish.rest;


import com.bcefit.projet.domain.moviedb.Episode;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.wish.WishEpisode;
import com.bcefit.projet.exposition.wish.dto.WishContentDto;
import com.bcefit.projet.exposition.wish.dto.WishEpisodeDto;
import com.bcefit.projet.exposition.wish.mapper.WishEpisodeMapper;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.moviedb.IEpisodeService;
import com.bcefit.projet.service.user.IUserAccountService;
import com.bcefit.projet.service.wish.IWishEpisodeService;
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
public class WishEpisodeAPI {

    @Autowired
    IUserAccountService iUserAccountService;

    @Autowired
    IWishEpisodeService service;

    @Autowired
    WishEpisodeMapper mapper;

    @Autowired
    IEpisodeService iEpisodeService;

    Logger logger = LoggerFactory.getLogger(WishEpisodeAPI.class);


    @GetMapping("/episode/{idWishEpisode}")
    public WishEpisodeDto getWishEpisodeById(@PathVariable("idWishEpisode") Long idWish,@RequestAttribute("userEmail") String userEmail){
        logger.info("Nouvelle demande pour le wish episode {}", idWish);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        WishEpisode wishEpisode = service.findById(idWish);
        logger.debug("DEBUG---ID Wish Episode = {}", wishEpisode.getIdWish());
        return mapper.convertEntityToDto(wishEpisode);
    }

    @PostMapping("/episode")
    public ResponseEntity<WishEpisodeDto> create(@RequestBody WishEpisodeDto wishEpisodeDto,@RequestAttribute("userEmail") String userEmail) throws InvalidEntityExeption {

        logger.info("Nouvelle demande de création de wish Episode  avec le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }
        // Recherche du contexte de l'épisode
        Episode episode = iEpisodeService.getEpisodeDetail(wishEpisodeDto.getIdTv(), wishEpisodeDto.getIdEpisode());

        // Mapping DTO vers Entity
        WishEpisode wishEpisode = mapper.convertDtoToEntity(wishEpisodeDto);

        // Enrichissement de l'entity avec UserAccount et Episode
        wishEpisode.setUserAccount(userAccount);
        wishEpisode.setEpisode(episode);

        // Création du WatchEpisode
        WishEpisodeDto dto = mapper.convertEntityToDto(service.createWishEpisode(wishEpisode));

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/episode/all")
    public ResponseEntity<List<WishEpisodeDto>> getAllWishEpisodess(@RequestAttribute("userEmail") String userEmail){
        logger.info("Nouvelle demande pour le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        logger.info("nouvelle demande de liste de wish Episode");
        Iterable<WishEpisode> iterable=service.findAllByUserAccountId(userAccount);
        List<WishEpisodeDto> wishEpisodeDtoList = new ArrayList<>();

        iterable.forEach((pEntity)-> wishEpisodeDtoList.add(mapper.convertEntityToDto(pEntity)));

        return ResponseEntity.status(HttpStatus.OK).body(wishEpisodeDtoList);
    }

    @DeleteMapping("/episode")
    public ResponseEntity<WishContentDto> deleteWishEpisode(@RequestBody WishContentDto wishContentDto, @RequestAttribute("userEmail") String userEmail){
        logger.info("Nouvelle demande de création de watch movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        logger.info("Nouvelle demande de suppression wish Episode {}",wishContentDto.getWishIdToDelete());
        WishEpisode wishEpisode = new WishEpisode();
        wishEpisode.setIdWish(wishContentDto.getWishIdToDelete());
        service.deleteWishEpisode(wishEpisode);

        return ResponseEntity.status(HttpStatus.OK).body(wishContentDto);
    }

}
