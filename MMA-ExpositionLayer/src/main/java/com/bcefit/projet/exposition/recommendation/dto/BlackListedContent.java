package com.bcefit.projet.exposition.recommendation.dto;

public class BlackListedContent {

    private Long idContent;

    public BlackListedContent() {
    }

    public BlackListedContent(Long idContent) {
        this.idContent = idContent;
    }

    public Long getIdContent() {
        return idContent;
    }

    public void setIdContent(Long idContent) {
        this.idContent = idContent;
    }
}
