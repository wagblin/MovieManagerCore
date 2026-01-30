package com.bcefit.projet.service.mapper;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchEpisode;
import com.bcefit.projet.service.user.UserAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EpisodeMsgMapper {

    @Autowired
    UserAccountServiceImpl userAccountService;

    public String convertEntityToMessage(WatchEpisode entity){
        String message = new String();
        message += entity.getIdWatch().toString()+"/";
        //message += entity.getIdEpisode().toString()+"/";
        //message += entity.getIdSeason().toString()+"/";
        //message += entity.getIdTv().toString()+"/";
        message += entity.getUserAccount().getIdUser()+"/";
        message += entity.getViewingPlace()+"/";
        message += entity.getViewingMood()+"/";
        message += entity.getViewingRate();

        return message;
    }

    public WatchEpisode convertMessageToEntity(String message){
        WatchEpisode watchEpisode = new WatchEpisode();
        String[] tabString = message.split("/");
        watchEpisode.setIdWatch(Long.valueOf(tabString[0]));
        //watchEpisode.setIdEpisode(Long.valueOf(tabString[1]));
        //watchEpisode.setIdSeason(Long.valueOf(tabString[2]));
        //watchEpisode.setIdTv(Long.valueOf(tabString[3]));
        UserAccount userAccount = userAccountService.findById(Long.valueOf(tabString[4]));
        watchEpisode.setUserAccount(userAccount);
        //if (tabString[5].equalsIgnoreCase("null")){watchEpisode.setViewingPlace(tabString[5]);}
        //if (tabString[6].equalsIgnoreCase("null")){watchEpisode.setViewingMood(Integer.valueOf(tabString[6]));}
        //if (tabString[7].equalsIgnoreCase("null")){watchEpisode.setViewingRate(Integer.valueOf(tabString[7]));}

        return watchEpisode;
    }
}