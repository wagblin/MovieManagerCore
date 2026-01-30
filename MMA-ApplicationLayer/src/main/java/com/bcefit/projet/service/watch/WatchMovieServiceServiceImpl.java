package com.bcefit.projet.service.watch;


import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchMovie;
import com.bcefit.projet.domain.wish.WishMovie;
import com.bcefit.projet.infrastructure.IWatchMovieRepository;
import com.bcefit.projet.infrastructure.IWatchMoviesByUserAccountRepository;
import com.bcefit.projet.infrastructure.IWishMovieRepository;

import com.bcefit.projet.service.analytic.IMovieRecommendationService;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.mapper.MovieMessageMapper;
import com.bcefit.projet.service.message.MessageString;
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
public class WatchMovieServiceServiceImpl implements IWatchMovieService {

    Logger logger = LoggerFactory.getLogger(WatchMovieServiceServiceImpl.class);

    @Autowired
    IWatchMovieRepository repository;

    @Autowired
    IWishMovieRepository iWishMovieRepository;

    @Autowired
    IWatchMoviesByUserAccountRepository iWatchMoviesByUserAccountRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    MovieMessageMapper movieMessageMapper;
    @Autowired
    IMovieRecommendationService iMovieRecommendationService;


    @Override
    public Iterable<WatchMovie> findAllByUserAccountId(UserAccount userAccount) {
        Optional<List<WatchMovie>> WatchMovieList = iWatchMoviesByUserAccountRepository.findWatchMoviesByUserAccount(userAccount);
        logger.debug("service findbyId {}", userAccount.getIdUser());
        if (WatchMovieList.isPresent()) {
            return WatchMovieList.get();
        } else {
            logger.error("pas de watch movie avec l'id user {}", userAccount.getIdUser());
            throw new EntityNotFoundException("Pas de watch movie en base");
        }
    }

    @Override
    public WatchMovie findById(Long id) {
        Optional<WatchMovie> optionalWatchMovie = repository.findById(id);
        logger.debug("service findbyId {}", id);
        if (optionalWatchMovie.isPresent()) {
            return optionalWatchMovie.get();
        } else {
            logger.error("pas de watch movie avec l'id  {}", id);
            throw new EntityNotFoundException("Le watch movie n'existe pas");
        }
    }

    @Override
    public WatchMovie createWatchMovie(WatchMovie watchMovie)throws InvalidEntityExeption {
        // Contrôle de la présence d'un watch pour ce contexte UserAccount / Movie
        WatchMovie watchMovieExisting = repository.findByIdMovieAndUserAccount(watchMovie.getMovie().getIdMovie(),watchMovie.getUserAccount());
        if (watchMovieExisting != null){throw new InvalidEntityExeption("watch déjà présent pour ce contexte");}

        // Enregistrement du watch Movie
        WatchMovie watchMovieAdd = repository.save(watchMovie);
        // Suppression de l'éventuel wish Movie associé

        WishMovie wishMovieToDelete = iWishMovieRepository.findByIdMovieAndUserAccount(watchMovieAdd.getMovie().getIdMovie(),watchMovieAdd.getUserAccount());
        if(wishMovieToDelete!=null){
            iWishMovieRepository.delete(wishMovieToDelete);
        }

        // Suppression de l'éventuelle recommandation associée à ce contenu
        Movie movie = watchMovieAdd.getMovie();
        UserAccount userAccount = watchMovieAdd.getUserAccount();
        iMovieRecommendationService.deleteMovieRecommendation(movie,userAccount);

        // Envoie d'un message pour informer de l'ajout d'un film dans la watchList
        String message = movieMessageMapper.convertMovieAndUserAccountToMessage(watchMovieAdd.getMovie().getIdMovie().intValue(),watchMovieAdd.getUserAccount().getIdUser());
        jmsTemplate.send("Q_ADD_Watch_MOVIE", new MessageString(message));

        return watchMovieAdd;
    }

    @Override
    public void deleteWatchMovie(WatchMovie watchMovie) {
        repository.delete(watchMovie);
    }



    @Override
    public WatchMovie findByIdMovieAndUserAccount(Long idMovie, UserAccount userAccount) {
        return repository.findByIdMovieAndUserAccount(idMovie, userAccount);
    }
}
