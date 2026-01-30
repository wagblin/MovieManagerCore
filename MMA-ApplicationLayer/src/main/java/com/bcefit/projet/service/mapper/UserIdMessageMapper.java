package com.bcefit.projet.service.mapper;

import com.bcefit.projet.domain.user.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class UserIdMessageMapper {

    public Long convertMessgeToLong(String message){
        String[] tabString = message.split("/");
        Long idUser = Long.valueOf(tabString[0]);
        return idUser;
    }

    public String convertUserAccountToMessage(UserAccount userAccount){
        String message = new String();
        message = userAccount.getIdUser()+"/";
        return message;
    }

}
