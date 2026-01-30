package com.bcefit.projet.domain.moviedb;

import com.bcefit.projet.domain.user.UserAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GenreTv {
    @Id
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "genreTvSet")
    @JsonIgnore
    private Set<UserAccount> userAccountSet = new HashSet<>();


    public GenreTv() {
    }

    public GenreTv(Long id, String name) {
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
