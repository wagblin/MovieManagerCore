package com.bcefit.projet.service.moviedb;


import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.infrastructure.ITvRepository;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.moviedb.api.ITmdbApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ITvServiceImpl implements ITvService {

    @Autowired
    ITvRepository iTvRepository;

    @Autowired
    ITmdbApiService iTmdbApiService;


    @Override
    public Tv getDetailByIdTv(Long idTv) throws InvalidEntityExeption {
        Tv tv = iTvRepository.findByIdTv(idTv);
        if (tv == null) {
            return iTmdbApiService.synchronizeTvDetailFromApiFromApi(idTv);
        }
        return tv;
    }


        @Override
        public Tv getAllDetailByIdTv(Long idTv) throws InvalidEntityExeption {
            Tv tv = iTvRepository.findAllDetailByIdTv(idTv);
            if (tv == null) {
                return iTmdbApiService.synchronizeTvDetailFromApiFromApi(idTv);
            }
            return tv;
        }





}
