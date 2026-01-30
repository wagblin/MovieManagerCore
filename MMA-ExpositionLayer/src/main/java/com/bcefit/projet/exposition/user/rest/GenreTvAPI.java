package com.bcefit.projet.exposition.user.rest;

import com.bcefit.projet.domain.moviedb.GenreTv;
import com.bcefit.projet.exposition.user.dto.GenreTvDto;
import com.bcefit.projet.exposition.user.mapper.GenreTvMapper;
import com.bcefit.projet.service.moviedb.IGenreTvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/genre/tv")
public class GenreTvAPI {

    @Autowired
    IGenreTvService service;

    @Autowired
    GenreTvMapper mapper;

    Logger logger = LoggerFactory.getLogger(GenreTvAPI.class);

    @GetMapping("/all")
    public List<GenreTvDto> getAllGenreTv(){
        logger.info("Nouvelle demande de la liste de genre TV");
        Iterable<GenreTv> iterable= service.findAll();
        List<GenreTvDto> genreMovieDtoList=new ArrayList<>();

        iterable.forEach((pEntity)-> genreMovieDtoList.add(mapper.convertEntityToDto(pEntity)));

        return genreMovieDtoList;
    }
}

