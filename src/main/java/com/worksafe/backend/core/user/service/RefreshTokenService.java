package com.worksafe.backend.core.user.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.worksafe.backend.core.user.entity.RefreshToken;
import com.worksafe.backend.core.user.entity.User;
import com.worksafe.backend.core.user.repository.RefreshTokenRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private static final Long ONE_YEAR_IN_MILLISECONDS = 31556952000L;


    private final RefreshTokenRepository refreshTokenRepository;


    public Optional<RefreshToken> getByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Transactional
    public RefreshToken create(User user) {

        return refreshTokenRepository.save(new RefreshToken(user, UUID.randomUUID().toString(),
                Instant.now().plusMillis(ONE_YEAR_IN_MILLISECONDS)));
    }


}
