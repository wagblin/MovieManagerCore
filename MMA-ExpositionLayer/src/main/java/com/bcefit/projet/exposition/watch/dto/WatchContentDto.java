package com.bcefit.projet.exposition.watch.dto;

public class WatchContentDto {

    private Long watchIdToDelete;


    public WatchContentDto() {
    }

    public WatchContentDto(Long watchIdToDelete) {
        this.watchIdToDelete = watchIdToDelete;
    }

    public Long getWatchIdToDelete() {
        return watchIdToDelete;
    }

    public void setWatchIdToDelete(Long watchIdToDelete) {
        this.watchIdToDelete = watchIdToDelete;
    }
}
