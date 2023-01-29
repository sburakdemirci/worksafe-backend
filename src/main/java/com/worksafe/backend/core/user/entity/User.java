package com.worksafe.backend.core.user.entity;


import java.util.List;

import com.worksafe.backend.core.configuration.EntityAuditBase;
import com.worksafe.backend.core.user.enumarator.UserAuthProvider;
import com.worksafe.backend.core.user.enumarator.Role;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class User extends EntityAuditBase {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Authorities")
    @Enumerated(EnumType.STRING)
    List<Role> authorities;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotNull
    private String email;
    @NotNull
    private String name;
    private String avatarUrl;
    //todo hashlenmis password ile geldiginde izin vermediginden emin ol
    @NotNull
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserAuthProvider userAuthProvider;
    private String providerId;
    private boolean enabled;
    private boolean locked;
    private int failedLoginAttempts;


    @Builder
    public User(String email, String name, String password,
            UserAuthProvider userAuthProvider, String providerId) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.userAuthProvider = userAuthProvider;
        this.providerId = providerId;
        this.authorities = List.of(Role.ROLE_USER);
    }
}
