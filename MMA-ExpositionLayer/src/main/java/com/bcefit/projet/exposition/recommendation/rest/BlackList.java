package com.bcefit.projet.exposition.recommendation.rest;


import com.bcefit.projet.domain.analytic.MovieRecommendationBlackListed;
import com.bcefit.projet.domain.analytic.TvRecommendationBlackListed;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.exposition.recommendation.dto.BlackListedContent;
import com.bcefit.projet.service.analytic.IMovieRecommendationBlackListedService;
import com.bcefit.projet.service.analytic.ITvRecommendationBlackListedService;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.moviedb.IMovieService;
import com.bcefit.projet.service.moviedb.ITvService;
import com.bcefit.projet.service.user.IUserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/blacklist")
public class BlackList {


    @Autowired
    IUserAccountService iUserAccountService;
    @Autowired
    IMovieService iMovieService;
    @Autowired
    ITvService iTvService;
    @Autowired
    ITvRecommendationBlackListedService iTvRecommendationBlackListedService;
    @Autowired
    IMovieRecommendationBlackListedService iMovieRecommendationBlackListedService;


    Logger logger = LoggerFactory.getLogger(BlackList.class);

    @PostMapping("/movie")
    public ResponseEntity<BlackListedContent> createBlacListedMovie(@RequestBody BlackListedContent blackListedContent, @RequestAttribute("userEmail") String userEmail) throws InvalidEntityExeption {

        logger.info("Nouvelle demande d'ajout de Movie en blackList pour le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if (userAccount == null) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        MovieRecommendationBlackListed movieRecommendationBlackListed = new MovieRecommendationBlackListed();
        movieRecommendationBlackListed.setUserAccount(userAccount);
        movieRecommendationBlackListed.setMovie(iMovieService.getMovieDetail(blackListedContent.getIdContent()));

        iMovieRecommendationBlackListedService.createMovieRecommandationBlackListed(movieRecommendationBlackListed);

        return ResponseEntity.status(HttpStatus.OK).body(blackListedContent);
    }

    @PostMapping("/tv")
    public ResponseEntity<BlackListedContent> createBlacListedTv(@RequestBody BlackListedContent blackListedContent, @RequestAttribute("userEmail") String userEmail) throws InvalidEntityExeption {

        logger.info("Nouvelle demande d'ajout de TV en blackList pour le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = iUserAccountService.logToUserAccount(userEmail);
        if (userAccount == null) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        TvRecommendationBlackListed tvRecommendationBlackListed = new TvRecommendationBlackListed();
        tvRecommendationBlackListed.setUserAccount(userAccount);
        tvRecommendationBlackListed.setTv(iTvService.getDetailByIdTv(blackListedContent.getIdContent()));

        iTvRecommendationBlackListedService.createTvRecommandationBlackListed(tvRecommendationBlackListed);

        return ResponseEntity.status(HttpStatus.OK).body(blackListedContent);
    }
}
