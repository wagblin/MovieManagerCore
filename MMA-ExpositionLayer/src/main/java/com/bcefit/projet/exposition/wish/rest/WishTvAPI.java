package com.bcefit.projet.exposition.wish.rest;


import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.exposition.wish.dto.WishEpisodeDto;
import com.bcefit.projet.exposition.wish.dto.WishTvSeasonDto;
import com.bcefit.projet.exposition.wish.mapper.WishEpisodeMapper;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.user.IUserAccountService;
import com.bcefit.projet.service.wish.IWishTvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wish")
public class WishTvAPI {

    @Autowired
    IUserAccountService iUserAccountService;

    @Autowired
    IWishTvService iWishTvService;

    @Autowired
    WishEpisodeMapper mapperEpisode;

    Logger logger = LoggerFactory.getLogger(WishTvAPI.class);


    @PostMapping("/tv")
    public ResponseEntity<List<WishEpisodeDto>> createWishByIdTv(@RequestBody WishTvSeasonDto wishTvSeasonDto, @RequestAttribute("userEmail") String userEmail) throws InvalidEntityExeption {

        Integer idTv = wishTvSeasonDto.getIdTv();
        logger.info("Nouvelle demande d'ajout de wish episode by idTv {}",idTv);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        List<WishEpisodeDto> wishEpisodeDtoList = mapperEpisode.convertListEntityToDto(iWishTvService.createWishEpisodeByTvId(Long.valueOf(idTv), userAccount));

        return ResponseEntity.status(HttpStatus.CREATED).body(wishEpisodeDtoList);
    }

    @DeleteMapping("/tv")
    public ResponseEntity<List<WishEpisodeDto>> deleteWishByIdTv(@RequestBody WishTvSeasonDto wishTvSeasonDto,@RequestAttribute("userEmail") String userEmail){

        Integer idTv = wishTvSeasonDto.getIdTv();

        logger.info("Nouvelle demande de suppression de wish episode by idTv {}",idTv);
         // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        List<WishEpisodeDto> wishEpisodeDtoList = mapperEpisode.convertListEntityToDto(iWishTvService.deleteWishEpisodeByTvId(Long.valueOf(idTv), userAccount));

        return ResponseEntity.status(HttpStatus.OK).body(wishEpisodeDtoList);
    }


}
