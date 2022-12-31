package com.worksafe.backend.persistence.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    private User user;

    private String token;
    private Instant expirationTime;

    @Setter
    private boolean used;

    public VerificationToken(User user, String token, Instant expirationTime) {
        this.user = user;
        this.token = token;
        this.expirationTime = expirationTime;
        this.used = false;
    }
}
