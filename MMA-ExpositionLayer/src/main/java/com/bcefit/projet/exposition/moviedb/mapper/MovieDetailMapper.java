package com.bcefit.projet.exposition.moviedb.mapper;

import com.bcefit.projet.domain.watch.WatchMovie;
import com.bcefit.projet.domain.wish.WishMovie;
import com.bcefit.projet.exposition.moviedb.dto.MovieDetailsDto;
import com.bcefit.projet.exposition.user.mapper.GenreMovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieDetailMapper {

    @Autowired
    GenreMovieMapper genreMovieMapper;

    public MovieDetailsDto convertWatchEntityToDto(WatchMovie entity){

        MovieDetailsDto dto = new MovieDetailsDto();
        dto.setIdMovie(entity.getMovie().getIdMovie());
        dto.setTitle(entity.getMovie().getTitle());
        dto.setPopularity(entity.getMovie().getPopularity());
        dto.setBackdropPath(entity.getMovie().getBackdropPath());
        dto.setPosterPath(entity.getMovie().getPosterPath());
        dto.setReleaseDate(entity.getMovie().getReleaseDate());
        dto.setAdult(entity.getMovie().isAdult());
        dto.setBudget(entity.getMovie().getBudget());
        dto.setHomepage(entity.getMovie().getHomepage());
        dto.setOverview(entity.getMovie().getOverview());
        dto.setImdbID(entity.getMovie().getImdbID());
        dto.setOriginalLanguage(entity.getMovie().getOriginalLanguage());
        dto.setRuntime(entity.getMovie().getRuntime());
        dto.setTagline(entity.getMovie().getTagline());
        dto.setVoteAverage(entity.getMovie().getVoteAverage());
        dto.setVoteCount(entity.getMovie().getVoteCount());
        dto.setStatus(entity.getMovie().getStatus());
        dto.setGenreMovieDtoList(genreMovieMapper.convertListEntityToDto(entity.getMovie().getGenreMovieList()));
        dto.setIdWatch(entity.getIdWatch());
        dto.setViewingPlace(entity.getViewingPlace());
        dto.setViewingRate(entity.getViewingRate());
        dto.setViewingMood(entity.getViewingMood());
        dto.setDateWatch(entity.getDateWatch());

        return dto;
    }

    public MovieDetailsDto convertWishEntityToDto(WishMovie entity){

        MovieDetailsDto dto = new MovieDetailsDto();
        dto.setIdMovie(entity.getMovie().getIdMovie());
        dto.setTitle(entity.getMovie().getTitle());
        dto.setPopularity(entity.getMovie().getPopularity());
        dto.setBackdropPath(entity.getMovie().getBackdropPath());
        dto.setPosterPath(entity.getMovie().getPosterPath());
        dto.setReleaseDate(entity.getMovie().getReleaseDate());
        dto.setAdult(entity.getMovie().isAdult());
        dto.setBudget(entity.getMovie().getBudget());
        dto.setHomepage(entity.getMovie().getHomepage());
        dto.setOverview(entity.getMovie().getOverview());
        dto.setImdbID(entity.getMovie().getImdbID());
        dto.setOriginalLanguage(entity.getMovie().getOriginalLanguage());
        dto.setRuntime(entity.getMovie().getRuntime());
        dto.setTagline(entity.getMovie().getTagline());
        dto.setVoteAverage(entity.getMovie().getVoteAverage());
        dto.setVoteCount(entity.getMovie().getVoteCount());
        dto.setStatus(entity.getMovie().getStatus());
        dto.setGenreMovieDtoList(genreMovieMapper.convertListEntityToDto(entity.getMovie().getGenreMovieList()));
        dto.setIdWish(entity.getIdWish());
        dto.setDateWish(entity.getDateWsih());

        return dto;
    }


}
