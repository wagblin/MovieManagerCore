package com.bcefit.projet.exposition.watch.rest;


import com.bcefit.projet.domain.moviedb.Episode;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchEpisode;
import com.bcefit.projet.exposition.watch.dto.WatchContentDto;
import com.bcefit.projet.exposition.watch.dto.WatchEpisodeDto;
import com.bcefit.projet.exposition.watch.mapper.WatchEpisodeMapper;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.moviedb.IEpisodeService;
import com.bcefit.projet.service.user.IUserAccountService;
import com.bcefit.projet.service.watch.IWatchEpisodeService;
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
public class WatchEpisodeAPI {

    @Autowired
    IWatchEpisodeService service;

    @Autowired
    IUserAccountService iUserAccountService;

    @Autowired
    WatchEpisodeMapper watchEpisodeMapper;


    @Autowired
    IEpisodeService iEpisodeService;

    Logger logger = LoggerFactory.getLogger(WatchEpisodeAPI.class);


    @GetMapping("/episode/{idWatchEpisode}")
    public ResponseEntity<WatchEpisodeDto> getWatchEpisodeById(@PathVariable("idWatchEpisode") Long idWatch, @RequestAttribute("userEmail") String userEmail){

        logger.info("Nouvelle demande de création de watch movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        WatchEpisode watchEpisode = service.findById(idWatch);
        logger.debug("DEBUG---ID Watch Episode = {}", watchEpisode.getIdWatch());
        WatchEpisodeDto watchEpisodeDto = watchEpisodeMapper.convertEntityToDto(watchEpisode);

        return ResponseEntity.status(HttpStatus.OK).body(watchEpisodeDto);


    }

    @PostMapping("/episode")
    public ResponseEntity<WatchEpisodeDto> create(@RequestBody WatchEpisodeDto watchEpisodeDto, @RequestAttribute("userEmail") String userEmail) throws InvalidEntityExeption {

        logger.info("Nouvelle demande de création de watch movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }
        // Recherche du contexte de l'épisode
        Episode episode = iEpisodeService.getEpisodeDetail(watchEpisodeDto.getIdTv(), watchEpisodeDto.getIdEpisode());

        // Mapping DTO vers Entity
        WatchEpisode watchEpisode = watchEpisodeMapper.convertDtoToEntity(watchEpisodeDto);

        // Enrichissement de l'entity avec UserAccount et Episode
        watchEpisode.setUserAccount(userAccount);
        watchEpisode.setEpisode(episode);

        // Création du WatchEpisode
        WatchEpisodeDto dto = watchEpisodeMapper.convertEntityToDto(service.createWatchEpisode(watchEpisode));

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/episode/all")
    public ResponseEntity<List<WatchEpisodeDto>> getAllWatchEpisodes(@RequestAttribute("userEmail") String userEmail){

        logger.info("Nouvelle demande de création de watch movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        logger.info("nouvelle demande de liste de watch movie");
        Iterable<WatchEpisode> iterable=service.findAllByUserAccountId(userAccount);
        List<WatchEpisodeDto> watchEpisodeDtoList = new ArrayList<>();

        iterable.forEach((pEntity)-> watchEpisodeDtoList.add(watchEpisodeMapper.convertEntityToDto(pEntity)));

        return ResponseEntity.status(HttpStatus.OK).body(watchEpisodeDtoList);
    }

    @DeleteMapping("/episode")
    public ResponseEntity<WatchContentDto> deleteWatchEpisode(@RequestBody WatchContentDto watchContentDto,  @RequestAttribute("userEmail") String userEmail){

        logger.info("Nouvelle demande de création de watch movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        logger.info("Nouvelle demande de suppression watch Episode {}",watchContentDto.getWatchIdToDelete());
        WatchEpisode watchEpisode = new WatchEpisode();
        watchEpisode.setIdWatch(watchContentDto.getWatchIdToDelete());
        service.deleteWatchEpisode(watchEpisode);

        return ResponseEntity.status(HttpStatus.OK).body(watchContentDto);
    }

}
