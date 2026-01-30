package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.moviedb.Season;
import org.springframework.data.repository.CrudRepository;

public interface ISeasonRepository extends CrudRepository<Season , Long> {
}
