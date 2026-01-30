package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.user.UserAccount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IUserAccountRepository extends CrudRepository<UserAccount , Long> {
    Optional<UserAccount> getByIdUser(Long idUser);
    @Transactional
    @Modifying
    @Query("update UserAccount u set u.userName = ?1, u.birthYear = ?2, u.adultContent = ?3 where u.idUser = ?4")
    int userAccountUpdate(String userName, Integer birthYear, boolean adultContent, Long idUser);




    List<UserAccount> findUserAccountsByGenreMovieSet(Long id);

    List<UserAccount> findUserAccountsByGenreTvSet(Long id);

}
