package com.bcefit.projet.exposition.user.dto;

import java.util.Date;

/**
 * Cette classe permet d'avoir des informations supplémentaires pour des exeptions
 * Elle n'est pas obligatoire
 */
public class MessageExceptionDto {

    private String errorCode;
    private String errorMessage;

    private Date errorDate;

    public MessageExceptionDto(String errorCode, String errorMessage, Date errorDate) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDate = errorDate;
    }

    public MessageExceptionDto() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }
}
