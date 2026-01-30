package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.moviedb.Tv;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ITvRepository extends CrudRepository<Tv, Long> {


    @Query("select m from Tv m where m.idTv = ?1")
    Tv findByIdTv(Long idMovie);


    @Transactional
    @Query("select t from Tv t where t.idTv = ?1")
    Tv findAllDetailByIdTv(Long idMovie);
}
