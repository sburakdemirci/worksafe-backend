package com.worksafe.backend.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worksafe.backend.exercise.entity.ExerciseCompletion;

public interface ExerciseCompletionRepository extends JpaRepository<ExerciseCompletion, Long> {

}
