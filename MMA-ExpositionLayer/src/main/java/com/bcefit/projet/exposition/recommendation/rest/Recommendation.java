package com.bcefit.projet.exposition.recommendation.rest;


import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.exposition.recommendation.dto.MovieRecommendationDto;
import com.bcefit.projet.exposition.recommendation.dto.TvRecommendationDto;
import com.bcefit.projet.exposition.recommendation.mapper.MovieRecommendationMapper;
import com.bcefit.projet.exposition.recommendation.mapper.TvRecommendationMapper;
import com.bcefit.projet.service.analytic.IMovieRecommendationService;
import com.bcefit.projet.service.analytic.ITvRecommendationService;
import com.bcefit.projet.service.user.IUserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendation")
public class Recommendation {

    @Autowired
    IMovieRecommendationService iMovieRecommendationService;
    @Autowired
    ITvRecommendationService iTvRecommendationService;
    @Autowired
    IUserAccountService iUserAccountService;
    @Autowired
    MovieRecommendationMapper movieRecommendationMapper;
    @Autowired
    TvRecommendationMapper tvRecommendationMapper;
    Logger logger = LoggerFactory.getLogger(Recommendation.class);


    @GetMapping("/movie")
    public ResponseEntity<List<MovieRecommendationDto>> getMovieRecommendationList(@RequestAttribute("userEmail") String userEmail) {

        logger.info("Nouvelle demande de recommendation Movie le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }
        List<Movie> movieList = iMovieRecommendationService.getMovieRecommendationByUserAccount(userAccount);
        if (movieList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else {
            List<MovieRecommendationDto> movieRecommendationDtoList = movieRecommendationMapper.convertListEntityToDto(movieList);
            return ResponseEntity.status(HttpStatus.OK).body(movieRecommendationDtoList);
        }
    }

    @GetMapping("/tv")
    public ResponseEntity<List<TvRecommendationDto>> getTvRecommendationList(@RequestAttribute("userEmail") String userEmail) {

        logger.info("Nouvelle demande de recommendation Tv le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if(userAccount ==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }
        List<Tv> tvList = iTvRecommendationService.getTvRecommendationByUserAccount(userAccount);
        if (tvList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else {
            List<TvRecommendationDto> tvRecommendationDtoList = tvRecommendationMapper.convertListEntityToDto(tvList);
            return ResponseEntity.status(HttpStatus.OK).body(tvRecommendationDtoList);
        }
    }

}
