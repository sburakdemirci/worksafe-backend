package com.worksafe.backend.reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worksafe.backend.reminder.entity.SystemReminder;

public interface SystemReminderRepository extends JpaRepository<SystemReminder, Long> {

}
