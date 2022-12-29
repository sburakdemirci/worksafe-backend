package com.worksafe.backend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worksafe.backend.persistence.entity.SystemReminderSubscription;

import jakarta.transaction.Transactional;

public interface SystemReminderSubscriptionRepository extends
        JpaRepository<SystemReminderSubscription, Long> {


    List<SystemReminderSubscription> findAllByUserId(Long userId);

    Optional<SystemReminderSubscription> findByReminderIdAndUserId(Long reminderId, Long userId);

    @Transactional
    void deleteByReminderIdAndUserId(Long reminderId, Long userId);


}
