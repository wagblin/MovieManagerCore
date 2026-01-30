package com.bcefit.projet.service.mapper;

import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.user.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class MovieMessageMapper {

    public Movie convertMessageToMovie(String message){
        Movie movie = new Movie();
        String[] tabString = message.split("/");
        movie.setIdMovie(Long.valueOf(tabString[0]));
        return movie;
    }

    public UserAccount convertMessageToUserAccount(String message){
        UserAccount userAccount = new UserAccount();
        String[] tabString = message.split("/");
        userAccount.setIdUser(Long.valueOf(tabString[1]));
        return userAccount;
    }

    public String convertMovieAndUserAccountToMessage(int idMovie, Long idUser){
        String message = new String();
        message = idMovie+"/"+ idUser;
        return message;
    }
}