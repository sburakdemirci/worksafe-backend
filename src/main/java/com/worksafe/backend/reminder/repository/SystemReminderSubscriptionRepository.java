package com.worksafe.backend.reminder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worksafe.backend.reminder.entity.SystemReminderSubscription;

import jakarta.transaction.Transactional;

public interface SystemReminderSubscriptionRepository extends
        JpaRepository<SystemReminderSubscription, Long> {


    List<SystemReminderSubscription> findAllByUserId(String userId);

    Optional<SystemReminderSubscription> findByReminderIdAndUserId(Long reminderId, String userId);

    @Transactional
    void deleteByReminderIdAndUserId(Long reminderId, String userId);


}
