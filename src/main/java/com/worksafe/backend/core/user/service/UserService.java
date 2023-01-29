package com.worksafe.backend.core.user.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.worksafe.backend.core.security.configuration.AppServerProperties;
import com.worksafe.backend.core.user.entity.PasswordResetToken;
import com.worksafe.backend.core.user.repository.PasswordResetTokenRepository;
import com.worksafe.backend.core.user.entity.User;
import com.worksafe.backend.core.user.repository.UserRepository;
import com.worksafe.backend.core.user.entity.VerificationToken;
import com.worksafe.backend.core.user.repository.VerificationTokenRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final AppServerProperties appServerProperties;

    private static final Long ONE_YEAR_IN_MILLISECONDS = 31556952000L;
    private static final Long THREE_DAYS_IN_MILLISECONDS = 259200000L;


    public User save(User user) {
        return userRepository.save(user);
    }

   /* public boolean existsById(Long id) {

    }*/

    public User update(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    public boolean checkExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Transactional
    public VerificationToken createVerificationTokenForUser(User user) {
        //todo set verification expiration to 1 year maybe ?
        //todo use the expiration time ?
        return verificationTokenRepository.save(
                new VerificationToken(user, UUID.randomUUID().toString(),
                        Instant.now().plusMillis(ONE_YEAR_IN_MILLISECONDS)));
    }

    @Transactional
    public PasswordResetToken createPasswordResetTokenForUser(User user) {

        return passwordResetTokenRepository.save(
                new PasswordResetToken(user, UUID.randomUUID().toString(),
                        Instant.now().plusMillis(THREE_DAYS_IN_MILLISECONDS)));
    }

    @Transactional
    public User verifyUser(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(
                token).orElseThrow(() -> new RuntimeException());
        /*        verificationToken.setUsed(true);*/

        userRepository.setUserEnabled(verificationToken.getUser().getId(), true);
        return verificationToken.getUser();

    }

}
