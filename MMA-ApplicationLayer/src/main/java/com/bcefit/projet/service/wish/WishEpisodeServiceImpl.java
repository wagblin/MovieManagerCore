package com.bcefit.projet.service.wish;

import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchEpisode;
import com.bcefit.projet.domain.wish.WishEpisode;
import com.bcefit.projet.infrastructure.IWishEpisodeRepository;
import com.bcefit.projet.infrastructure.IWishEpisodesByUserAccountRepository;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WishEpisodeServiceImpl implements IWishEpisodeService{

    Logger logger = LoggerFactory.getLogger(WishEpisodeServiceImpl.class);

    @Autowired
    IWishEpisodeRepository repository;

    @Autowired
    IWishEpisodesByUserAccountRepository iWishEpisodesByUserAccountRepository;

    @Autowired
    TvMessageMapper tvMessageMapper;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    ITvRecommendationService iTvRecommendationService;
    @Autowired
    ITvService iTvService;

    @Override
    public Iterable<WishEpisode> findAllByUserAccountId(UserAccount userAccount) {
        Optional<List<WishEpisode>> wishEpisodeList = iWishEpisodesByUserAccountRepository.findWishEpisodesByUserAccount(userAccount);
        logger.debug("service findbyId {}", userAccount.getIdUser());
        if (wishEpisodeList.isPresent()) {
            return wishEpisodeList.get();
        } else {
            logger.error("pas de wish episode avec l'id user {}", userAccount.getIdUser());
            throw new EntityNotFoundException("Pas de wish episode en base");
        }
    }

    @Override
    public WishEpisode findById(Long id) {
        Optional<WishEpisode> optionalWishEpisode = repository.findById(id);
        logger.debug("service findbyId {}", id);
        if (optionalWishEpisode.isPresent()) {
            return optionalWishEpisode.get();
        } else {
            logger.error("pas de wish episode avec l'id  {}", id);
            throw new EntityNotFoundException("Le wish episode n'existe pas");
        }
    }

    @Override
    public WishEpisode createWishEpisode(WishEpisode wishEpisode) throws InvalidEntityExeption {
        // Contrôle de la présence d'un wish pour ce contexte UserAccount / Tv
        WishEpisode wishEpisodeExisting = repository.findByIdEpisodeAndUserAccount(wishEpisode.getEpisode().getIdEpisode(),wishEpisode.getUserAccount());
        if (wishEpisodeExisting != null){throw new InvalidEntityExeption("wish déjà présent pour ce contexte");}

        LocalDate actualDate = LocalDate.now();
        wishEpisode.setDateWsih(actualDate);
        // Enregistrement du watch episode
        WishEpisode wishEpisodeAdd = repository.save(wishEpisode);

        // Suppression de l'éventuelle recommandation associée à ce contenu
        Tv tv = iTvService.getDetailByIdTv(Long.valueOf(wishEpisodeAdd.getEpisode().getSeriesId()));
        UserAccount userAccount = wishEpisodeAdd.getUserAccount();
        iTvRecommendationService.deleteTvRecommendation(tv,userAccount);

        // Envoie d'un message pour informer de l'ajout d'un episode dans la wishList
        String message = tvMessageMapper.convertTvAndUserAccountToMessage(wishEpisodeAdd.getEpisode().getSeriesId(),wishEpisodeAdd.getUserAccount().getIdUser());
        jmsTemplate.send("Q_ADD_Wish_EPISODE", new MessageString(message));

        return wishEpisodeAdd;
    }


    @Override
    public WishEpisode getIdWishEpisodeByIdSerieAndUserAccount(Long idEpisode, UserAccount userAccount) {
        return repository.findByIdEpisodeAndUserAccount(idEpisode,userAccount);
    }

    @Override
    public WishEpisode findByIdEpisodeAndUserAccount(Long idEpisode, UserAccount userAccount) {
        return repository.findByIdEpisodeAndUserAccount(idEpisode, userAccount);
    }


    @Override
    public void deleteWishEpisode(WishEpisode wishEpisode) {
        repository.delete(wishEpisode);
    }
}
