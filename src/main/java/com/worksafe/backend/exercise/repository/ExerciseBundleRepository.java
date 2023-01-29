package com.worksafe.backend.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worksafe.backend.exercise.entity.ExerciseBundle;

public interface ExerciseBundleRepository extends JpaRepository<ExerciseBundle, Long> {

}
