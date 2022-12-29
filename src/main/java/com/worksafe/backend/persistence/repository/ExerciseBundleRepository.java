package com.worksafe.backend.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worksafe.backend.persistence.entity.ExerciseBundle;

public interface ExerciseBundleRepository extends JpaRepository<ExerciseBundle, Long> {

}
