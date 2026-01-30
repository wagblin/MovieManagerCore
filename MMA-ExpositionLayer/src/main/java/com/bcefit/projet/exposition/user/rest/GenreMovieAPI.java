package com.bcefit.projet.exposition.user.rest;

import com.bcefit.projet.domain.moviedb.GenreMovie;
import com.bcefit.projet.exposition.user.dto.GenreMovieDto;
import com.bcefit.projet.exposition.user.mapper.GenreMovieMapper;
import com.bcefit.projet.service.moviedb.IGenreMovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/genre/movie")
public class GenreMovieAPI {

    @Autowired
    IGenreMovieService service;

    @Autowired
    GenreMovieMapper mapper;

    Logger logger = LoggerFactory.getLogger(GenreMovieAPI.class);

    @GetMapping("/all")
    public List<GenreMovieDto> getAllGenreMovie(){
        logger.info("Nouvelle demande de la liste de genre movie");
        Iterable<GenreMovie> iterable= service.findAll();
        List<GenreMovieDto> genreMovieDtoList=new ArrayList<>();

        iterable.forEach((pEntity)-> genreMovieDtoList.add(mapper.convertEntityToDto(pEntity)));

        return genreMovieDtoList;
    }
}
