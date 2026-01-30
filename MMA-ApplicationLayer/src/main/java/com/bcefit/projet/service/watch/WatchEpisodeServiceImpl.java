package com.bcefit.projet.service.watch;

import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchEpisode;
import com.bcefit.projet.domain.watch.WatchMovie;
import com.bcefit.projet.domain.wish.WishEpisode;
import com.bcefit.projet.infrastructure.*;
import com.bcefit.projet.service.analytic.ITvRecommendationService;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.mapper.TvMessageMapper;
import com.bcefit.projet.service.message.MessageString;
import com.bcefit.projet.service.moviedb.ITvService;
import com.bcefit.projet.service.user.IUserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class WatchEpisodeServiceImpl implements IWatchEpisodeService {

    Logger logger = LoggerFactory.getLogger(WatchEpisodeServiceImpl.class);

    @Autowired
    IWatchEpisodeRepository repository;

    @Autowired
    IWishEpisodeRepository iWishEpisodeRepository;

    @Autowired
    IWatchEpisodesByUserAccountRepository iWatchEpisodesByUserAccountRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    TvMessageMapper tvMessageMapper;

    @Autowired
    ITvRecommendationService iTvRecommendationService;
    @Autowired
    ITvService iTvService;


    @Override
    public Iterable<WatchEpisode> findAllByUserAccountId(UserAccount userAccount) {
        logger.debug("service findbyId {}", userAccount.getIdUser());
        Optional<List<WatchEpisode>> watchEpisodeList = iWatchEpisodesByUserAccountRepository.findWatchEpisodesByUserAccount(userAccount);
        if (watchEpisodeList.isPresent()) {
            return watchEpisodeList.get();
        } else {
            logger.error("pas de watch episode avec l'id user {}", userAccount.getIdUser());
            throw new EntityNotFoundException("Pas de wish episode en base");
        }
    }

    @Override
    public WatchEpisode findById(Long id) {
        Optional<WatchEpisode> optionalWatchEpisode = repository.findById(id);
        logger.debug("service findbyId {}", id);
        if (optionalWatchEpisode.isPresent()) {
            return optionalWatchEpisode.get();
        } else {
            logger.error("pas de watch episode avec l'id  {}", id);
            throw new EntityNotFoundException("Le watch episode n'existe pas");
        }
    }

    @Override
    public WatchEpisode createWatchEpisode(WatchEpisode watchEpisode) throws InvalidEntityExeption {
        // Contrôle de la présence d'un watch pour ce contexte UserAccount / Tv
        WatchEpisode watchEpisodeExisting = repository.findByIdEpisodeAndUserAccount(watchEpisode.getEpisode().getIdEpisode(),watchEpisode.getUserAccount());
        if (watchEpisodeExisting != null){throw new InvalidEntityExeption("watch déjà présent pour ce contexte");}


        // Enregistrement du watch episode
        WatchEpisode watchEpisodeAdd = repository.save(watchEpisode);
        // Suppression de l'éventuel wish Movie associé

        WishEpisode wishEpisodeToDelete = iWishEpisodeRepository.findByIdEpisodeAndUserAccount(watchEpisode.getEpisode().getIdEpisode(),watchEpisodeAdd.getUserAccount());
        if(wishEpisodeToDelete!=null){
            iWishEpisodeRepository.delete(wishEpisodeToDelete);
        }

        // Suppression de l'éventuelle recommandation associée à ce contenu
        Tv tv = iTvService.getDetailByIdTv(Long.valueOf(watchEpisodeAdd.getEpisode().getSeriesId()));
        UserAccount userAccount = watchEpisodeAdd.getUserAccount();
        iTvRecommendationService.deleteTvRecommendation(tv,userAccount);


        // Envoie d'un message pour informer de l'ajout d'un episode dans la watchList
        String message = tvMessageMapper.convertTvAndUserAccountToMessage(watchEpisodeAdd.getEpisode().getSeriesId(),watchEpisodeAdd.getUserAccount().getIdUser());
        jmsTemplate.send("Q_ADD_Watch_EPISODE", new MessageString(message));
        return watchEpisodeAdd;
    }

    @Override
    public WatchEpisode getIdWatchEpisodeByIdSerieAndUserAccount(Long idEpisode, UserAccount userAccount) {
        return repository.findByIdEpisodeAndUserAccount(idEpisode,userAccount);
    }

    @Override
    public WatchEpisode findByIdEpisodeAndUserAccount(Long idEpisode, UserAccount userAccount) {
        return repository.findByIdEpisodeAndUserAccount(idEpisode, userAccount);
    }


    @Override
    public void deleteWatchEpisode(WatchEpisode watchEpisode) {
        repository.delete(watchEpisode);
    }
}
