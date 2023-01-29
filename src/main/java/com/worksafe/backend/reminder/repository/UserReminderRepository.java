package com.worksafe.backend.reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worksafe.backend.reminder.entity.UserReminder;

public interface UserReminderRepository extends JpaRepository<UserReminder, Long> {

}
