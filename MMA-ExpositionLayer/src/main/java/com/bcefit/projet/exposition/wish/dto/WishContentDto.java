package com.bcefit.projet.exposition.wish.dto;

public class WishContentDto {

    private Long wishIdToDelete;

    public WishContentDto() {
    }

    public WishContentDto(Long wishIdToDelete) {
        this.wishIdToDelete = wishIdToDelete;
    }

    public Long getWishIdToDelete() {
        return wishIdToDelete;
    }

    public void setWishIdToDelete(Long wishIdToDelete) {
        this.wishIdToDelete = wishIdToDelete;
    }
}
