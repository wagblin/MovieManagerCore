package com.bcefit.projet.service.analytic;

import com.bcefit.projet.domain.analytic.MovieRecommendationBlackListed;
import com.bcefit.projet.domain.analytic.TvRecommendationBlackListed;
import com.bcefit.projet.domain.moviedb.GenreMovie;
import com.bcefit.projet.domain.moviedb.GenreTv;
import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.domain.watch.WatchMovie;
import com.bcefit.projet.domain.wish.WishMovie;
import com.bcefit.projet.infrastructure.IMovieRecommendationBlackListedRepository;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.moviedb.api.ITmdbApiServiceImpl;
import com.bcefit.projet.service.watch.IWatchEpisodeService;
import com.bcefit.projet.service.watch.IWatchMovieService;
import com.bcefit.projet.service.watch.IWatchTvService;
import com.bcefit.projet.service.wish.IWishEpisodeService;
import com.bcefit.projet.service.wish.IWishMovieService;
import com.bcefit.projet.service.wish.IWishTvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AnalyticServiceImpl implements IAnalyticService{

    @Autowired
    ITmdbApiServiceImpl iTmdbApiService;
    @Autowired
    IMovieRecommendationService iMovieRecommendationService;
    @Autowired
    ITvRecommendationService iTvRecommendationService;
    @Autowired
    IWatchMovieService iWatchMovieService;
    @Autowired
    IWishMovieService iWishMovieService;
    @Autowired
    IWatchEpisodeService iWatchEpisodeService;
    @Autowired
    IWishEpisodeService iWishEpisodeService;
    @Autowired
    IWatchTvService iWatchTvService;
    @Autowired
    IWishTvService iWishTvService;
    @Autowired
    ITvRecommendationBlackListedService iTvRecommendationBlackListedService;

    @Autowired
    IMovieRecommendationBlackListedService iMovieRecommendationBlackListedService;


    @Override
    public void addWatchMovieAnalytic(Movie movie, UserAccount userAccount) throws InvalidEntityExeption {
        //Recherche d'une éventuelle recommandation pour ce film et suppression si c'est le cas
        if (iMovieRecommendationService.findMovieRecommendationByIdMovieAndUserAccount(movie, userAccount) != null) {
            iMovieRecommendationService.deleteMovieRecommendation(movie, userAccount);
        }
        //Recherche des recommendation via l'API TMDB
        List<Movie> movieRecommendationList = iTmdbApiService.getAllMovieRecommendation(movie.getIdMovie());

        //On contrôle dans la table des MovieRecommendationBlackListed si le Movie à été blackListé par l'utilisateur
        MovieRecommendationBlackListed movieRecommendationBlackListed = iMovieRecommendationBlackListedService.isBlackListedByMovie(movie);
        if (movieRecommendationBlackListed==null) {


            //Filtrage des movies éventuellement présents en Watchlsit ou WishList
            Iterable<WatchMovie> watchMovieIterable = iWatchMovieService.findAllByUserAccountId(userAccount);
            Iterable<WishMovie> wishMovieIterable = iWishMovieService.findAllByUserAccountId(userAccount);
            // création d'un Set de movieRecommandation qui seront retirer si présents dans Wish ou Watch Movies
            Set<Long> setIdMovieARetirer = new HashSet<>();
            // parcours du résultat pour identifier les Movies à filtrer
            for (Movie movie1 : movieRecommendationList) {
                Long idMovie = movie1.getIdMovie();
                for (WatchMovie watchMovie : watchMovieIterable) {
                    if (watchMovie.getMovie().getIdMovie().equals(idMovie)) {
                        setIdMovieARetirer.add(idMovie);
                        break;
                    } else {
                        for (WishMovie wishMovie : wishMovieIterable) {
                            if (wishMovie.getMovie().getIdMovie().equals(idMovie)) {
                                setIdMovieARetirer.add(idMovie);
                                break;
                            }
                        }
                    }
                }
            }
            // création d'un nouvelle liste de movieRecommendation en supprimant les recommandations de Movies identifiés dans les Wish et Watch Movies
            List<Movie> movieListRecommendationFiltree = new ArrayList<>();
            if (!setIdMovieARetirer.isEmpty()) {
                for (Long idMovieASupprimer : setIdMovieARetirer) {
                    for (Movie movieATester : movieRecommendationList) {
                        if (!movieATester.getIdMovie().equals(idMovieASupprimer)) {
                            movieListRecommendationFiltree.add(movieATester);
                        }
                    }
                }
            } else {
                movieListRecommendationFiltree = movieRecommendationList;
            }

            //Enregistrement des Movies issus de liste de recommendation en local s'ils n'éxistent pas
            for (Movie movieRecommendation : movieListRecommendationFiltree) {
                iTmdbApiService.synchronizeMovieDetailFromApi(movieRecommendation.getIdMovie());
            }
            //Enregistrement des recommendations qui ne seraient pas déjà présentes
            for (Movie movieRecommendation : movieListRecommendationFiltree) {
                if (iMovieRecommendationService.findMovieRecommendationByIdMovieAndUserAccount(movieRecommendation, userAccount) == null) {
                    iMovieRecommendationService.createMovieRecommendation(movieRecommendation, userAccount);
                }
            }
        }
    }

    @Override
    public void deleteWatchMovieAnalytic(Movie movie, UserAccount userAccount) {

    }

    @Override
    public void addWatchTvAnalytic(Tv tv, UserAccount userAccount) throws InvalidEntityExeption{
        //Recherche d'une éventuelle recommandation pour cette Série Tv et suppression si c'est le cas
        if (iTvRecommendationService.findTvRecommendationByIdTvAndUserAccount(tv, userAccount) != null) {
            iTvRecommendationService.deleteTvRecommendation(tv, userAccount);
        }
        //Recherche des recommendation via l'API TMDB
        List<Tv> tvRecommendationList = iTmdbApiService.getAllTvRecommendation(tv.getIdTv());

        //On contrôle dans la table des TvRecommendationBlackListed si la série Tv à déjà donné lieu à une recommendation
        // en effet les évènements (add Wish ou add watch liste) sont postés par épidode et il ne faut traiter qu'un seul message d'une série Tv
        // Donc si cette série a déjà donné lieu à une recommendation on ne fait rien
        TvRecommendationBlackListed tvRecommendationBlackListed = iTvRecommendationBlackListedService.isBlackListedByTv(tv);
        if (tvRecommendationBlackListed==null) {

            // On ajoute la série Tv dans TvRecommendationBlackListed pour qu'elle ne soit plus traitée
            TvRecommendationBlackListed newTvRecommendationBlackListed = new TvRecommendationBlackListed();
            newTvRecommendationBlackListed.setUserAccount(userAccount);
            newTvRecommendationBlackListed.setTv(tv);
            iTvRecommendationBlackListedService.createTvRecommandationBlackListed(newTvRecommendationBlackListed);

            //Filtrage des TV éventuellement présentes en Watchlsit ou WishList
            Set<Long> idTvWatchList = iWatchTvService.getIdTvWatchByUserAccount(userAccount);
            Set<Long> idTvWishList = iWishTvService.getIdTvByUserAccount(userAccount);
            // création d'un Set de TvRecommandation qui seront retirer si présents dans Wish ou Watch Movies
            Set<Long> setIdTvARetirer = new HashSet<>();
            // parcours du résultat pour identifier les Tv à filtrer
            for (Tv tv1 : tvRecommendationList) {
                Long idTv = tv1.getIdTv();
                for (Long idTvWatch : idTvWatchList) {
                    if (idTvWatch == idTv) {
                        setIdTvARetirer.add(idTv);
                        break;
                    } else {
                        for (Long idTvWish : idTvWishList) {
                            if (idTvWish == idTv) {
                                setIdTvARetirer.add(idTv);
                                break;
                            }
                        }
                    }
                }
            }
            // création d'un nouvelle liste de tvRecommendation en supprimant les recommandations de TV identifiés dans les Wish et Watch tv
            List<Tv> tvListRecommendationFiltree = new ArrayList<>();
            if (!setIdTvARetirer.isEmpty()) {
                for (Long idTvASupprimer : setIdTvARetirer) {
                    for (Tv tvATester : tvRecommendationList) {
                        if (!tvATester.getIdTv().equals(idTvASupprimer)) {
                            tvListRecommendationFiltree.add(tvATester);
                        }
                    }
                }
            } else {
                tvListRecommendationFiltree = tvRecommendationList;
            }

            //Enregistrement des TV issus de liste de recommendation en local s'ils n'éxistent pas
            for (Tv tvRecommendation : tvListRecommendationFiltree) {
                iTmdbApiService.synchronizeTvDetailFromApiFromApi(tvRecommendation.getIdTv());
            }
            //Enregistrement des recommendations qui ne seraient pas déjà présentes
            for (Tv tvRecommendation : tvListRecommendationFiltree) {
                if (iTvRecommendationService.findTvRecommendationByIdTvAndUserAccount(tvRecommendation, userAccount) == null) {
                    iTvRecommendationService.createTvRecommendation(tvRecommendation, userAccount);
                }
            }
        }
    }

    @Override
    public void deleteWatchTvAnalytic(Tv tv, UserAccount userAccount) {

    }

    @Override
    public void addWishMovieAnalytic(Movie movie, UserAccount userAccount) throws InvalidEntityExeption {
        addWatchMovieAnalytic(movie,userAccount);
    }

    @Override
    public void deleteWishMovieAnalytic(Movie movie, UserAccount userAccount) {

    }

    @Override
    public void addWishTvAnalytic(Tv tv, UserAccount userAccount)throws InvalidEntityExeption {
        addWatchTvAnalytic(tv,userAccount);
    }

    @Override
    public void deleteWishTvAnalytic(Tv tv, UserAccount userAccount) {

    }

    @Override
    public void initializeMovieRecommendation(UserAccount userAccount) throws InvalidEntityExeption {
        // Création de la liste des GenreMovie sélectionnés par l'utilisateur à partir du Set de userAccount
        List<GenreMovie> genreMovieListPreference = new ArrayList<>();
        for(GenreMovie genreMovie : userAccount.getGenreMovieSet()){
            genreMovieListPreference.add(genreMovie);
        }
        List<Movie> movieList = iTmdbApiService.getAllMovieTrendByGenreMovieFromApi(genreMovieListPreference);
        List<Movie> movieCeatedList = new ArrayList<>();
        for (Movie movie : movieList){
            if (!movieCeatedList.contains(movie)){
                iMovieRecommendationService.createMovieRecommendation(movie,userAccount);
                movieCeatedList.add(movie);
            }
        }
    }

    @Override
    public void initializeTvRecommendation(UserAccount userAccount) throws InvalidEntityExeption {
        // Création de la liste des GenreTv sélectionnés par l'utilisateur à partir du Set de userAccount
        List<GenreTv> genreTvListPreference = new ArrayList<>();
        for (GenreTv genreTv : userAccount.getGenreTvSet()){
            genreTvListPreference.add(genreTv);
        }
        List<Tv> tvList = iTmdbApiService.getAllTvTrendsByGenreTvFromApi(genreTvListPreference);
        List<Tv> tvCreatedList = new ArrayList<>();
        for (Tv tv : tvList){
            if (!tvCreatedList.contains(tv)){
                iTvRecommendationService.createTvRecommendation(tv,userAccount);
                tvCreatedList.add(tv);
            }
        }
    }
}
