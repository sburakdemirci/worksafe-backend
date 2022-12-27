package com.worksafe.backend.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worksafe.backend.persistence.entity.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
