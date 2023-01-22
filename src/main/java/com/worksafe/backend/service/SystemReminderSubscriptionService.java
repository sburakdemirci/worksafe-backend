package com.worksafe.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.worksafe.backend.persistence.entity.SystemReminderSubscription;
import com.worksafe.backend.persistence.repository.SystemReminderSubscriptionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SystemReminderSubscriptionService {

    private final SystemReminderSubscriptionRepository systemReminderSubscriptionRepository;


    @Transactional
    public SystemReminderSubscription create(
            SystemReminderSubscription systemReminderSubscription) {
        return systemReminderSubscriptionRepository.save(systemReminderSubscription);
    }

    @Transactional
    public void deleteById(Long id) {
        systemReminderSubscriptionRepository.deleteById(id);
    }

    @Transactional
    public void deleteByReminderIdAndUserId(Long reminderId, String userId) {
        systemReminderSubscriptionRepository.deleteByReminderIdAndUserId(reminderId, userId);
    }


    public List<SystemReminderSubscription> getAll() {
        return systemReminderSubscriptionRepository.findAll();
    }

    public Optional<SystemReminderSubscription> getById(Long id) {
        return systemReminderSubscriptionRepository.findById(id);
    }

    public List<SystemReminderSubscription> findAllByUserId(String userId) {
        return systemReminderSubscriptionRepository.findAllByUserId(userId);
    }

    public Optional<SystemReminderSubscription> findByReminderIdAndUserId(Long reminderId,
            String userId) {
        return systemReminderSubscriptionRepository.findByReminderIdAndUserId(reminderId, userId);
    }

}
