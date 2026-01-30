package com.bcefit.projet.exposition.exceptionhandler;

import com.bcefit.projet.exposition.user.dto.MessageExceptionDto;
import com.bcefit.projet.service.exception.InvalidEntityExeption;
import com.bcefit.projet.service.message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MessageExceptionDto> handleProduitNotFoundExeption (EntityNotFoundException ex) {
        MessageExceptionDto errorMessage=new MessageExceptionDto("NOT_FOUND",ex.getMessage(),new Date());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(InvalidEntityExeption.class)
    public ResponseEntity<ErrorMessage>  handleInvalidEntityExeption (InvalidEntityExeption ex) {
        ErrorMessage error=new ErrorMessage("BAD_REQUEST",ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ErrorMessage>  handleNoResultFoundExeption (NoResultException ex) {
        ErrorMessage error=new ErrorMessage("NOT_FOUND",ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }


}
