package com.worksafe.backend.core.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.worksafe.backend.core.user.entity.PasswordResetToken;
import com.worksafe.backend.core.user.entity.VerificationToken;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<VerificationToken> findByUserIdAndToken(Long userId, String token);

    Optional<VerificationToken> findByToken(String token);

}
