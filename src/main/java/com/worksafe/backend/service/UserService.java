package com.worksafe.backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.worksafe.backend.persistence.entity.User;
import com.worksafe.backend.persistence.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean existsById(Long id) {

    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public boolean checkExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
