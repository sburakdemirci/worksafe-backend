package com.worksafe.backend.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worksafe.backend.persistence.entity.ExerciseBundle;
import com.worksafe.backend.persistence.entity.UserReminder;

public interface UserReminderRepository extends JpaRepository<UserReminder, Long> {

}
