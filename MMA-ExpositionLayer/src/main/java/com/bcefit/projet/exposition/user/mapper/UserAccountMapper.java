package com.bcefit.projet.exposition.user.mapper;


import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.exposition.user.dto.UserAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserAccountMapper {

    @Autowired
    GenreMovieMapper movieMapper;
    @Autowired
    GenreTvMapper tvMapper;

    @Autowired
    StreamingSubscriptionMapper streamingSubscriptionMapper;



    public UserAccountDto convertEntityToDto(UserAccount entity){
        UserAccountDto dto = new UserAccountDto();
        dto.setUserName(entity.getUserName());
        dto.setAdultContent(entity.isAdultContent());
        dto.setEmail(entity.getEmail());
        dto.setBirthYear(entity.getBirthYear());
        dto.setGenreMovieDtoSet(movieMapper.convertSetEntityToDto(entity.getGenreMovieSet()));
        dto.setGenreTvDtoSet(tvMapper.convertSetEntityToDto(entity.getGenreTvSet()));
        dto.setStreamingSubscriptionDtoSet(streamingSubscriptionMapper.convertSetEntityToDto(entity.getStreamingSubscriptionSet()));
        return dto;
    }

    public UserAccount convertDtoToEntity(UserAccountDto dto){
        UserAccount entity = new UserAccount();
        entity.setUserName(dto.getUserName());
        entity.setAdultContent(dto.isAdultContent());
        entity.setEmail(dto.getEmail());
        entity.setBirthYear(dto.getBirthYear());
        entity.setGenreMovieSet(movieMapper.convertSetDtoToEntity(dto.getGenreMovieDtoSet()));
        entity.setGenreTvSet(tvMapper.convertSetDtoToEntity(dto.getGenreTvDtoSet()));
        entity.setStreamingSubscriptionSet(streamingSubscriptionMapper.convertSetDtoToEntity(dto.getStreamingSubscriptionDtoSet()));
        return entity;
    }


}
