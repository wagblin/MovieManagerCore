package com.bcefit.projet.service.analytic;

import com.bcefit.projet.domain.analytic.MovieRecommendation;
import com.bcefit.projet.domain.analytic.TvRecommendation;
import com.bcefit.projet.domain.moviedb.Movie;
import com.bcefit.projet.domain.moviedb.Tv;
import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.infrastructure.ITvRecommendationRepository;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class TvRecommendationServiceImpl implements ITvRecommendationService{

    @Autowired
    ITvRecommendationRepository iTvRecommendationRepository;

    @Override
    @Transactional
    public void deleteTvRecommendation(Tv tv, UserAccount userAccount) {
        iTvRecommendationRepository.deleteByUserAccountAndTv(userAccount,tv);

    }

    @Override
    public TvRecommendation findTvRecommendationByIdTvAndUserAccount(Tv tv, UserAccount userAccount) {
        return iTvRecommendationRepository.findByUserAccountAndTv(userAccount,tv);
    }

    @Override
    public void createTvRecommendation(Tv tv, UserAccount userAccount) throws InvalidEntityExeption {
        TvRecommendation tvRecommendation = new TvRecommendation();
        tvRecommendation.setUserAccount(userAccount);
        tvRecommendation.setTv(tv);
        tvRecommendation.setDate(LocalDate.now());
        iTvRecommendationRepository.save(tvRecommendation);
    }

    @Override
    public List<Tv> getTvRecommendationByUserAccount(UserAccount userAccount) {
        List<TvRecommendation> tvRecommendationList = iTvRecommendationRepository.findByUserAccount(userAccount);
        List<Tv> tvList = new ArrayList<>();
        if (! tvRecommendationList.isEmpty()){
            for(TvRecommendation tvRecommendation : tvRecommendationList){
                Tv tv = tvRecommendation.getTv();
                tvList.add(tv);
            }
        }
        //On trie la liste en fonction de la popularité de la série Tv
        Collections.sort(tvList, new Comparator<Tv>(){
            public int compare(Tv tv1, Tv tv2){return Float.compare(tv2.getPopularity(),tv1.getPopularity());}
        });
        return tvList;
    }
}
