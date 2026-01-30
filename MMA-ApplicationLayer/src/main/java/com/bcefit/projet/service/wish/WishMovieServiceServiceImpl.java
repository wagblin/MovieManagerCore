package com.bcefit.projet.service.wish;


import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.wish.WishEpisode;
import com.bcefit.projet.domain.wish.WishMovie;
import com.bcefit.projet.infrastructure.IWishMovieRepository;
import com.bcefit.projet.infrastructure.IWishMoviesByUserAccountRepository;
import com.bcefit.projet.service.analytic.IMovieRecommendationService;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.mapper.MovieMessageMapper;
import com.bcefit.projet.service.message.MessageString;
import com.bcefit.projet.service.moviedb.IMovieService;
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
public class WishMovieServiceServiceImpl implements IWishMovieService{

    Logger logger = LoggerFactory.getLogger(WishMovieServiceServiceImpl.class);

    @Autowired
    IWishMovieRepository repository;

    @Autowired
    IWishMoviesByUserAccountRepository iWishMoviesByUserAccountRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    MovieMessageMapper movieMessageMapper;

    @Autowired
    IMovieRecommendationService iMovieRecommendationService;


    @Override
    public Iterable<WishMovie> findAllByUserAccountId(UserAccount userAccount) {
        Optional<List<WishMovie>> wishMovieList = iWishMoviesByUserAccountRepository.findWishMoviesByUserAccount(userAccount);
        logger.debug("service findbyId {}", userAccount.getIdUser());
        if (wishMovieList.isPresent()) {
            return wishMovieList.get();
        } else {
            logger.error("pas de wish movie avec l'id user {}", userAccount.getIdUser());
            throw new EntityNotFoundException("Pas de wish movie en base");
        }
    }

    @Override
    public WishMovie findById(Long id) {
        Optional<WishMovie> optionalWishMovie = repository.findById(id);
        logger.debug("service findbyId {}", id);
        if (optionalWishMovie.isPresent()) {
            return optionalWishMovie.get();
        } else {
            logger.error("pas de wish movie avec l'id  {}", id);
            throw new EntityNotFoundException("Le wish movie n'existe pas");
        }
    }

    @Override
    public WishMovie createWishMovie(WishMovie wishMovie) throws InvalidEntityExeption {
        // Contrôle de la présence d'un wish pour ce contexte UserAccount / Movie
        WishMovie wishMovieExisting = repository.findByIdMovieAndUserAccount(wishMovie.getMovie().getIdMovie(),wishMovie.getUserAccount());
        if (wishMovieExisting != null){throw new InvalidEntityExeption("wish déjà présent pour ce contexte");}

        LocalDate actualDate = LocalDate.now();
        wishMovie.setDateWsih(actualDate);
        WishMovie wishMovieAdd = repository.save(wishMovie);

        // Suppression de l'éventuelle recommandation associée à ce contenu
        Movie movie = wishMovieAdd.getMovie();
        UserAccount userAccount = wishMovieAdd.getUserAccount();
        iMovieRecommendationService.deleteMovieRecommendation(movie,userAccount);


        // Envoie d'un message pour informer de l'ajout d'un film dans la wishList
        String message = movieMessageMapper.convertMovieAndUserAccountToMessage(wishMovieAdd.getMovie().getIdMovie().intValue(),wishMovieAdd.getUserAccount().getIdUser());
        jmsTemplate.send("Q_ADD_Wish_MOVIE", new MessageString(message));

        return repository.save(wishMovie);
    }

    @Override
    public void deleteWishMovie(WishMovie wishMovie) {
        repository.delete(wishMovie);
    }


    @Override
    public WishMovie findByIdMovieAndUserAccount(Long idMovie, UserAccount userAccount) {
        return repository.findByIdMovieAndUserAccount(idMovie, userAccount);
    }
}
