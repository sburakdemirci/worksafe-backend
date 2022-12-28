package com.worksafe.backend.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worksafe.backend.persistence.entity.SystemReminderSubscription;

public interface SystemReminderSubscriptionRepository extends
        JpaRepository<SystemReminderSubscription, Long> {

}
