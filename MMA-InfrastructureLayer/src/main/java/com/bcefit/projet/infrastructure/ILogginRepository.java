package com.bcefit.projet.infrastructure;

import com.bcefit.projet.domain.user.UserAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ILogginRepository extends CrudRepository<UserAccount , String> {
    @Query("select u from UserAccount u where u.email = ?1")
    Optional<UserAccount> findUserAccountByEmail(String email);


}
