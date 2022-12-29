package com.worksafe.backend.persistence.entity;


import java.util.Arrays;
import java.util.List;

import com.worksafe.backend.enumarator.AuthProvider;
import com.worksafe.backend.enumarator.Role;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String avatarUrl;
    //todo hashlenmis password ile geldiginde izin vermediginden emin ol
    @NotNull
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;
    private String providerId;
    private boolean enabled;
    private boolean locked;
    private int failedLoginAttempts;


    @Builder
    public User(String email, String firstName, String lastName, String password,
            AuthProvider authProvider, String providerId) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.authProvider = authProvider;
        this.providerId = providerId;
        this.authorities = List.of(Role.USER);
    }
}
