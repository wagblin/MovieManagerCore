package com.bcefit.projet.domain.moviedb;

import com.bcefit.projet.domain.user.UserAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class StreamingSubscription {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "streamingSubscriptionSet")
    @JsonIgnore
    private Set<UserAccount> userAccountSet = new HashSet<>();


    public void StreamingSubscriptionEntity() {
    }

    public void StreamingSubscriptionEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserAccount> getUserAccounts() {
        return userAccountSet;
    }

    public void setUserAccounts(Set<UserAccount> userAccountSet) {
        this.userAccountSet = userAccountSet;
    }

}
