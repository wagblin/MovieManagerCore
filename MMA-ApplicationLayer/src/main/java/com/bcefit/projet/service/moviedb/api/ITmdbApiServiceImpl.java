package com.bcefit.projet.service.moviedb.api;


import com.bcefit.projet.domain.moviedb.*;
import com.bcefit.projet.infrastructure.IEpisodeRepository;
import com.bcefit.projet.infrastructure.IMovieRepository;
import com.bcefit.projet.infrastructure.ISeasonRepository;
import com.bcefit.projet.infrastructure.ITvRepository;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.mapper.*;
import com.bcefit.projet.service.moviedb.GenreMovieServiceImpl;
import com.bcefit.projet.service.moviedb.GenreTvServiceImpl;
import com.bcefit.projet.service.moviedb.StreamingSubscriptionServiceImpl;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbTV;
import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.tv.Network;
import info.movito.themoviedbapi.model.tv.TvEpisode;
import info.movito.themoviedbapi.model.tv.TvSeason;
import info.movito.themoviedbapi.model.tv.TvSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ITmdbApiServiceImpl implements ITmdbApiService {

    @Autowired
    MovieApiMapper movieApiMapper;
    @Autowired
    TvApiMapper tvApiMapper;
    @Autowired
    SeasonApiMapper seasonApiMapper;
    @Autowired
    EpisodeApiMapper episodeApiMapper;
    @Autowired
    GenreMovieApiMapper genreMovieApiMapper;
    @Autowired
    GenreMovieServiceImpl genreMovieService;
    @Autowired
    GenreTvApiMapper genreTvApiMapper;
    @Autowired
    GenreTvServiceImpl genreTvService;
    @Autowired
    StreamingSubscriptionApiMapper streamingSubscriptionApiMapper;
    @Autowired
    StreamingSubscriptionServiceImpl streamingSubscriptionService;

    @Autowired
    IMovieRepository iMovieRepository;
    @Autowired
    ITvRepository iTvRepository;
    @Autowired
    ISeasonRepository iSeasonRepository;
    @Autowired
    IEpisodeRepository iEpisodeRepository;

    Logger logger = LoggerFactory.getLogger(ITmdbApiServiceImpl.class);

    private TmdbApi getSessionApi() {
        TmdbApi tmdb = new TmdbApi("45b46c1e742cef1725535eafeb1fba52");
        return tmdb;
    }

    @Override
    public Movie synchronizeMovieDetailFromApi(Long idMovie) throws InvalidEntityExeption {

        // Contrôle dans la base locale si le Movie est présent
        Movie movieLocal = iMovieRepository.findByIdMovie(idMovie);
        // si le movie est présent en locale on renvoie le résultat sinon on va le chercher sur TMDB
        if ((movieLocal == null)) {

            //Création des variables qui seront utilisées pour la synchronisation
            Movie tmptv = new Movie();
            TmdbApi tmdb = getSessionApi();

            //Synchonisation de la base local des Genres Movie
            //synchronizeGenreMovieFromApi(tmdb);
            // récupération du Movie et synchronisation en base
            MovieDb movieDb = tmdb.getMovies().getMovie(idMovie.intValue(), "fr", TmdbMovies.MovieMethod.credits);
            tmptv = movieApiMapper.convertMovieApiToMovie(movieDb);
            iMovieRepository.save(tmptv);
            return tmptv;

        }else {
            return movieLocal;
        }
    }

    @Override
    public Tv synchronizeTvDetailFromApiFromApi(Long idTv) throws InvalidEntityExeption{
        //Création des variables qui seront utilisées pour la synchronisation
        Tv tmptv = new Tv();
        List<Season> seasonList = new ArrayList<>();
        List<Episode> episodeList = new ArrayList<>();
        TmdbApi tmdb = getSessionApi();

        // récupération de la série Tv et synchronisation en base
        TvSeries tvSeries = tmdb.getTvSeries().getSeries(idTv.intValue(), "fr");
        tmptv = tvApiMapper.convertTvApiToTv(tvSeries);

        // Contrôle dans la base locale si la série est présente et si le nombre d'épisodes à évolué
        Tv tvLocal = iTvRepository.findByIdTv(idTv);
        if ((tvLocal == null) || (tvLocal.getNumberOfEpisodes()!=tmptv.getNumberOfEpisodes())){

            //Synchonisation de la base locale des Genres Tv et StremaingSubscription
            //synchroniseGenreTvFromApi(tmdb);

            iTvRepository.save(tmptv);

            List<TvSeason> tvSeasonList = tvSeries.getSeasons();

            for (TvSeason tvSeason : tvSeasonList) {
                // récupération des saisons et synchronisation en base

                TvSeason tmpTvSeason = tmdb.getTvSeasons().getSeason(tvSeries.getId(), tvSeason.getSeasonNumber(), "fr");

                if(tmpTvSeason!=null){
                    Season tmpSeason = new Season();
                    tmpSeason = seasonApiMapper.convertSeasonApiToSeason(tmpTvSeason);
                    tmpSeason.setTv(tmptv);
                    iSeasonRepository.save(tmpSeason);

                    // récupération des episodes et synchronisation en base
                    List<TvEpisode> tvEpisodeList = tmpTvSeason.getEpisodes().stream().toList();
                    if (tvEpisodeList != null) {
                        for (TvEpisode tvEpisode : tvEpisodeList) {
                            Episode tmpEpisode = episodeApiMapper.convertEpisodeApiToEpisode(tvEpisode);
                            tmpEpisode.setSeason(seasonApiMapper.convertSeasonApiToSeason(tvSeason));
                            tmpEpisode.setSeason(tmpSeason);
                            episodeList.add(tmpEpisode);

                            iEpisodeRepository.save(tmpEpisode);
                        }
                    }
                    seasonList.add(tmpSeason);
                }
            }
            tmptv.setSeasons(seasonList);
            return tmptv;
        }
    return tvLocal;
    }

    @Override
    public List<Movie> getAllMovieRecommendation(Long idMovie) {
        //Création des variables qui seront utilisées pour la récupération des recommendations
        List<Movie> movieList = new ArrayList<>();
        TmdbApi tmdb = getSessionApi();

        // récupération des recommendations associées au Movie
        MovieDb movieDb = tmdb.getMovies().getMovie(idMovie.intValue(), "fr", TmdbMovies.MovieMethod.recommendations);
        movieList = movieApiMapper.convertListMovieRecommendationApiToMovie(movieDb.getRecommendations());
        return movieList;
    }

    @Override
    public List<Tv> getAllTvRecommendation(Long idTv) {
        //Création des variables qui seront utilisées pour la récupération des recommendations
        List<Tv> tvList = new ArrayList<>();
        TmdbApi tmdb = getSessionApi();

        // récupération des recommendations associées au Tv
        TvSeries tvSeries = tmdb.getTvSeries().getSeries(idTv.intValue(),"fr", TmdbTV.TvMethod.recommendations);
        tvList = tvApiMapper.convertTvRecommendationApiToTv(tvSeries.getRecommendations().getResults());
        return tvList;
    }

    @Override
    public void synchronizeGenreMovieFromApi(TmdbApi tmdbApi) {

        // récupératrion des GenreMovie de TMDB
        List<Genre> genreList = tmdbApi.getGenre().getGenreList("fr");
        for(Genre genre : genreList){
            GenreMovie genreMovie = genreMovieApiMapper.convertGenreMovieApiToGenreMovie(genre);
            genreMovieService.createGenreMovie(genreMovie);
        }

    }

    @Override
    public void synchroniseGenreTvFromApi(TmdbApi tmdbApi) {

        // récupération des GenreTv de TMDB
        List<Genre> genreList = tmdbApi.getGenre().getGenreList("fr");
        for (Genre genre : genreList){
            GenreTv genreTv = genreTvApiMapper.convertGenreTvApiToGenreTv(genre);
            genreTvService.createGenreTv(genreTv);
        }
    }

    @Override
    public void synchroniseStreamingSubscriptionFromApi(TmdbApi tmdbApi) {

    }

    @Override
    public List<Movie> getAllMovieTrendByGenreMovieFromApi(List<GenreMovie> genreMovieList) throws InvalidEntityExeption {
        //Création des variables qui seront utilisées pour la synchronisation
        List<Movie> movieResultList = new ArrayList<>();
        TmdbApi tmdb = getSessionApi();

        // récupération de la liste des Trends Movie en fonction de la liste des genres
        MovieResultsPage movieResultsPage = tmdb.getMovies().getPopularMovies("fr",1);
        List<MovieDb> movieDbList = movieResultsPage.getResults();
        for (MovieDb movieDb : movieDbList){
            MovieDb movieDbExtend = tmdb.getMovies().getMovie(movieDb.getId(),"fr", TmdbMovies.MovieMethod.credits);
            Movie movie = movieApiMapper.convertMovieApiToMovie(movieDbExtend);
            // si l'utilisateur n'a pas émis de préférence, on recommande toute la liste de résultats
            if(genreMovieList.isEmpty()){
                synchronizeMovieDetailFromApi(movie.getIdMovie());
                movieResultList.add(movie);
            }
            // sinon on filtre la liste des Movies récupérés par genre Movie en gérant la synchronisation avec la BDD local
            List<Genre> genreList = movieDbExtend.getGenres();
            List<GenreMovie> genreMovieListMovieDb = genreMovieApiMapper.convertListApiToEntity(genreList);
            //On identifie les Movie dont le genre match avec les préférences utilisateur et on synchronise la base locale
            for (GenreMovie genreMovie : genreMovieList){
                for (GenreMovie genreMovie1 : genreMovieListMovieDb){
                    if(genreMovie1.getId()==genreMovie.getId()){
                        synchronizeMovieDetailFromApi(movie.getIdMovie());
                        movieResultList.add(movie);
                    }
                }
            }
        }
        return movieResultList;
    }

    @Override
    public List<Tv> getAllTvTrendsByGenreTvFromApi(List<GenreTv> genreTvList) throws InvalidEntityExeption {
        //Création des variables qui seront utilisées pour la synchronisation
        List<Tv> tvResultList = new ArrayList<>();
        TmdbApi tmdb = getSessionApi();
        // récupération de la liste des Trends Tv en fonction de la liste des genres
        TvResultsPage tvResultsPage = tmdb.getTvSeries().getPopular("fr",1);
        List<TvSeries> tvSeriesList = tvResultsPage.getResults();
        for (TvSeries tvSeries:tvSeriesList){
            TvSeries tvSeriesExtend = tmdb.getTvSeries().getSeries(tvSeries.getId(),"fr", TmdbTV.TvMethod.credits);
            Tv tv = tvApiMapper.convertTvApiToTv(tvSeriesExtend);
            // si l'utilisateur n'a pas émis de préférence, on recommande toute la liste de résultats
            if (genreTvList.isEmpty()){
                synchronizeTvDetailFromApiFromApi(tv.getIdTv());
                tvResultList.add(tv);
            }
            // sinon on filtre la liste des Tv récupérées par genre Tv en gérant la synchronisation avec la BDD local
            List<Genre> genreList = tvSeriesExtend.getGenres();
            List<GenreTv> genreTvListMovieDB = genreTvApiMapper.convertListApiToEntity(genreList);
            //On identifie les Tv dont le genre match avec les préférences utilisateur et on synchronise la base locale
            for (GenreTv genreTv : genreTvList){
                for (GenreTv genreTv1 : genreTvListMovieDB){
                    if (genreTv1.getId()==genreTv.getId()){
                        synchronizeTvDetailFromApiFromApi(tv.getIdTv());
                        tvResultList.add(tv);
                    }
                }
            }
        }
        return tvResultList;
    }

}
