package com.bcefit.projet.service.mapper;


import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.domain.user.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class TvMessageMapper {

    public Tv  convertMessageToTv(String message){
        Tv tv = new Tv();
        String[] tabString = message.split("/");
        tv.setIdTv(Long.valueOf(tabString[0]));
        return tv;
    }

    public UserAccount convertMessageToUserAccount(String message){
        UserAccount userAccount = new UserAccount();
        String[] tabString = message.split("/");
        userAccount.setIdUser(Long.valueOf(tabString[1]));
        return userAccount;
    }

    public String convertTvAndUserAccountToMessage(int idTv, Long idUser){
        String message = new String();
        message = idTv+"/"+ idUser;
        return message;
    }
}
