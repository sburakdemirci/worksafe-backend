package com.worksafe.backend.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worksafe.backend.persistence.entity.ExerciseCompletion;

public interface ExerciseCompletionRepository extends JpaRepository<ExerciseCompletion, Long> {

}
