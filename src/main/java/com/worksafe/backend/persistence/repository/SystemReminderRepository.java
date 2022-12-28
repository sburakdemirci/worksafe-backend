package com.worksafe.backend.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worksafe.backend.persistence.entity.UserReminder;

public interface SystemReminderRepository extends JpaRepository<UserReminder, Long> {

}
