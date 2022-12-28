package com.worksafe.backend.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worksafe.backend.enumarator.BodyPart;
import com.worksafe.backend.persistence.entity.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> getAllByBodyPart(BodyPart bodyPart);

}
