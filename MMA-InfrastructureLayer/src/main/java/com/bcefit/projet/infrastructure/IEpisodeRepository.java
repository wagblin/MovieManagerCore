package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.moviedb.Episode;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEpisodeRepository extends CrudRepository<Episode , Long> {
    @Query("select e from Episode e where e.seriesId = ?1 order by e.seasonNumber")
    List<Episode> findBySeriesIdOrderBySeasonNumberAsc(int seriesId);

    @Query("select e from Episode e where e.idEpisode = ?1")
    Episode findByIdEpisode(Long idEpisode);

    @Query("select e from Episode e where e.seriesId = ?1 and e.season.idSeason = ?2")
    List<Episode> findBySeriesIdAndSeasonId(int idTv, Long idSeason);
}
