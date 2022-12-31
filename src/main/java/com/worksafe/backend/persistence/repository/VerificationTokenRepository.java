package com.worksafe.backend.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.worksafe.backend.persistence.entity.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByUserIdAndToken(Long userId, String token);

    Optional<VerificationToken> findByToken(String token);

}
