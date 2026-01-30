package com.bcefit.projet.service.moviedb;

import com.bcefit.projet.domain.moviedb.Episode;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

import java.util.List;

public interface IEpisodeService {

    Episode getEpisodeDetail(Long idTv, Long idEpisode) throws InvalidEntityExeption;

    List<Episode> getAllEpisodeByIdTv (Long idTv);

    List<Episode> getAllEpisodeByIdTvAndIdSeason(Long idTv,Long idSeason);

    Episode getEpisodeDetailByEpisodeId(Long idEpisode);
}
