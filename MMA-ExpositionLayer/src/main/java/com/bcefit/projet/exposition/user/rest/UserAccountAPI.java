package com.bcefit.projet.exposition.user.rest;

import com.bcefit.projet.domain.user.UserAccount;
import com.bcefit.projet.exposition.user.dto.UserAccountDto;
import com.bcefit.projet.exposition.user.mapper.UserAccountMapper;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.user.IUserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/userAccount")
public class UserAccountAPI {



    @Autowired
    IUserAccountService service;

    @Autowired
    UserAccountMapper mapper;

    Logger logger = LoggerFactory.getLogger(UserAccountAPI.class);

    @GetMapping("")
    public ResponseEntity<UserAccountDto> getUserAccountById(@RequestAttribute("userEmail") String userEmail){
        logger.info("Nouvelle demande pour le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccount = service.logToUserAccount(userEmail);
        logger.debug("DEBUG---ID UserAccount = {}", userAccount.getIdUser());

        UserAccountDto dto = mapper.convertEntityToDto(userAccount);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping("/create")
    public ResponseEntity<UserAccountDto> create(@RequestBody UserAccountDto userAccountDto) throws InvalidEntityExeption {

        logger.info("enregistrement d'un nouveau user account {}",userAccountDto.getUserName());

        UserAccount userAccount=mapper.convertDtoToEntity(userAccountDto);

        UserAccountDto dto=mapper.convertEntityToDto(service.createUserAccount(userAccount));

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PostMapping("/update")
    public ResponseEntity<UserAccountDto> update(@RequestBody UserAccountDto userAccountDto, @RequestAttribute("userEmail") String userEmail) throws InvalidEntityExeption {
        logger.info("Nouvelle demande pour le UserAccount (Email) {}", userEmail);
        // Contrôle d'identification de l'utilisateur avec l'email issu du Token
        // Chargement du UserAccount
        UserAccount userAccountBeforeUpdate = service.logToUserAccount(userEmail);
        logger.info("modification du user account {}",userAccountDto.getUserName());

        UserAccount userAccountUpdated=mapper.convertDtoToEntity(userAccountDto);

        UserAccountDto dto=mapper.convertEntityToDto(service.updateUserAccount(userAccountUpdated));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }


    @GetMapping("/all")
    public ResponseEntity<List<UserAccountDto>> getAllUserAccounts(){
        logger.info("nouvelle demande de liste de user Account");
        Iterable<UserAccount> iterable=service.findAll();
        List<UserAccountDto> userAccountDtoList=new ArrayList<>();

        iterable.forEach((pEntity)-> userAccountDtoList.add(mapper.convertEntityToDto(pEntity)));

        return ResponseEntity.status(HttpStatus.OK).body(userAccountDtoList);
    }
}
