package com.bcefit.projet.service.user;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.infrastructure.ILogginRepository;
import com.bcefit.projet.infrastructure.IUserAccountRepository;
import com.bcefit.projet.service.analytic.IAnalyticService;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.mapper.UserIdMessageMapper;
import com.bcefit.projet.service.message.MessageString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements IUserAccountService{

    Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    @Autowired
    IUserAccountRepository repository;
    @Autowired
    IAnalyticService iAnalyticService;

    @Autowired
    ILogginRepository iLogginRepository;

    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    UserIdMessageMapper userIdMessageMapper;


    @Override
    public UserAccount findById(Long idUser) {
        Optional<UserAccount> optionalUserAccount = repository.findById(idUser);
        logger.debug("service finbyid {}", idUser);
        if(optionalUserAccount.isPresent()){
            return optionalUserAccount.get();
        }else {
            logger.error("pas de userAccount avec l'id {}", idUser);
            throw new EntityNotFoundException("Le UserAccount n'éxiste pas");
        }
    }

    @Override
    public UserAccount createUserAccount(UserAccount userAccount)throws InvalidEntityExeption {

        UserAccount userAccountCreated = repository.save(userAccount);
        // Envoie d'un message pour informer de l'ajout d'un UserAccount
        // les services asynchrones Analytic se chargeront de créér des recommendation adaptée au profilage de l'utilisateur
        String message = userIdMessageMapper.convertUserAccountToMessage(userAccount);
        jmsTemplate.send("Q_ADD_UserAccount", new MessageString(message));
        return userAccountCreated;
    }

    @Override
    public void deleteUserAccount(UserAccount userAccount) {

    }

    @Override
    public UserAccount updateUserAccount(UserAccount userAccount) throws InvalidEntityExeption{

     Integer i = repository.userAccountUpdate(userAccount.getUserName(), userAccount.getBirthYear(), userAccount.isAdultContent(), userAccount.getIdUser());
     System.out.println("i = "+i);
     return userAccount;
    }

    @Override
    public Iterable<UserAccount> findAll() {
        return repository.findAll();
    }


    @Override
    public Long getIdUserByUserEmail(String email) {
        logger.debug("service getUserIdByEmail {}", email);
        Optional<UserAccount> optionalUserAccount = iLogginRepository.findUserAccountByEmail(email);
        if(optionalUserAccount.isPresent()){
            return optionalUserAccount.get().getIdUser();
        }else{
            logger.error("pas d'idUser pour l'email {}", email);
            throw new EntityNotFoundException("L'email n'éxiste pas");
        }

    }

    @Override
    public UserAccount logToUserAccount(String email) {
        logger.debug("service log to UserAccount by loggin {}", email);
        Long idUser = getIdUserByUserEmail(email);
        UserAccount userAccount = findById(idUser);
        return userAccount;
    }
}
