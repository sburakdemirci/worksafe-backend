package com.worksafe.backend.reminder.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.worksafe.backend.reminder.entity.UserReminder;
import com.worksafe.backend.reminder.repository.UserReminderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserReminderService {

    private final UserReminderRepository userReminderRepository;

    @Transactional
    public UserReminder create(UserReminder userReminder) {
        return userReminderRepository.save(userReminder);
    }

    @Transactional
    public void deleteById(Long id) {
        userReminderRepository.deleteById(id);
    }

    public List<UserReminder> getAll() {
        return userReminderRepository.findAll();
    }

    public Optional<UserReminder> getById(Long id) {
        return userReminderRepository.findById(id);
    }
}
