package com.bcefit.projet.service.user;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.service.exception.InvalidEntityExeption;

public interface IUserAccountService {

    UserAccount findById(Long idUser);

    UserAccount createUserAccount(UserAccount userAccount)throws InvalidEntityExeption;


    void deleteUserAccount(UserAccount userAccount);

    UserAccount updateUserAccount(UserAccount userAccount) throws InvalidEntityExeption;

    Iterable<UserAccount> findAll();

    Long getIdUserByUserEmail(String email);

    UserAccount logToUserAccount(String email);


}
