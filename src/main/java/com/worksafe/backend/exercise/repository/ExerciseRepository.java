package com.worksafe.backend.exercise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worksafe.backend.exercise.enumarator.BodyPart;
import com.worksafe.backend.exercise.entity.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> getAllByBodyPart(BodyPart bodyPart);

}
