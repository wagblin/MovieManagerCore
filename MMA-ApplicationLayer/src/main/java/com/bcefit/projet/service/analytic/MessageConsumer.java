package com.bcefit.projet.service.analytic;


import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.mapper.MovieMessageMapper;
import com.bcefit.projet.service.mapper.TvMessageMapper;

import com.bcefit.projet.service.mapper.UserIdMessageMapper;
import com.bcefit.projet.service.user.UserAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

@Autowired
MovieMessageMapper movieMessageMapper;

@Autowired
TvMessageMapper tvMessageMapper;

@Autowired
UserIdMessageMapper userIdMessageMapper;
@Autowired
UserAccountServiceImpl userAccountService;


@Autowired
IAnalyticService iAnalyticService;

@JmsListener(destination = "Q_ADD_UserAccount")
    public void consumeAddUserAccountMessage(String message) throws InvalidEntityExeption{
    Long userId = userIdMessageMapper.convertMessgeToLong(message);
    UserAccount userAccount = userAccountService.findById(userId);
    iAnalyticService.initializeMovieRecommendation(userAccount);
    iAnalyticService.initializeTvRecommendation(userAccount);
}


@JmsListener(destination = "Q_ADD_Watch_MOVIE")
    public void consumeAddWatchMovieMessage(String message) throws InvalidEntityExeption {
    //WatchMovie watchMovie = watchMovieMapper.convertMessageToEntity(message);
    Movie movie = movieMessageMapper.convertMessageToMovie(message);
    UserAccount userAccount = tvMessageMapper.convertMessageToUserAccount(message);
    iAnalyticService.addWatchMovieAnalytic(movie , userAccount);
}


@JmsListener(destination = "Q_ADD_Watch_EPISODE")
public void consumeAddWatchEpisodeMessage(String message) throws InvalidEntityExeption {
    Tv tv = tvMessageMapper.convertMessageToTv(message);
    UserAccount userAccount = tvMessageMapper.convertMessageToUserAccount(message);
    iAnalyticService.addWatchTvAnalytic(tv , userAccount);
}


@JmsListener(destination = "Q_ADD_Wish_MOVIE")
public void consumeAddWishMovieMessage(String message) throws InvalidEntityExeption {
    //WatchMovie watchMovie = watchMovieMapper.convertMessageToEntity(message);
    Movie movie = movieMessageMapper.convertMessageToMovie(message);
    UserAccount userAccount = tvMessageMapper.convertMessageToUserAccount(message);
    iAnalyticService.addWishMovieAnalytic(movie , userAccount);
}



@JmsListener(destination = "Q_ADD_Wish_EPISODE")
public void consumeAddWishEpisodeMessage(String message) throws InvalidEntityExeption {
    Tv tv = tvMessageMapper.convertMessageToTv(message);
    UserAccount userAccount = tvMessageMapper.convertMessageToUserAccount(message);
    iAnalyticService.addWishTvAnalytic(tv , userAccount);
}


}
